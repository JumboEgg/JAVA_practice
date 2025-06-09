import java.util.*;
import java.io.*;

public class Main {
    // 위치 비교
    static class Position implements Comparable<Position> {
        int x, y, dist;

        public Position(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        // 거리 -> 행 -> 열 순으로 비교 정렬
        @Override
        public int compareTo(Position o) {
            if (this.dist == o.dist) {
                if (this.x == o.x) return this.y - o.y; // 열 비교
                else return this.x - o.x; // 행 비교
            }
            return this.dist - o.dist; // 거리 비교
        }
    }

    static int[] dx = {-1, 1, 0, 0}; // 상하좌우
    static int[] dy = {0, 0, -1, 1};
    static int curX, curY, curCustomer; // 현재 위치, 현재 승객
    static Position[] destinations; // 목적지

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int fuel = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][N];
        destinations = new Position[M+2];

        // 지도 정보 저장
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 택시 시작 위치
        st = new StringTokenizer(br.readLine());
        curX = Integer.parseInt(st.nextToken()) - 1;
        curY = Integer.parseInt(st.nextToken()) - 1;

        // 고객 출발지/목적지 저장
        // 지도와 중복 방지를 위해 2부터 시작
        for (int i = 2; i < M + 2; i++) {
            st = new StringTokenizer(br.readLine());
            int sx = Integer.parseInt(st.nextToken()) - 1;
            int sy = Integer.parseInt(st.nextToken()) - 1;
            int ex = Integer.parseInt(st.nextToken()) - 1;
            int ey = Integer.parseInt(st.nextToken()) - 1;

            map[sx][sy] = i; // 승객 위치 표기
            destinations[i] = new Position(ex, ey, 0); // 목적지 등록
        }

        // 탑승한 승객 수
        int completed = 0;

        // 승객을 모두 이동시킬 때까지 반복
        while (completed < M) {
            // 가장 가까운 승객 탐색
            int distToCus = findNearest(N, map);
            if (fuel < distToCus || distToCus == -1) break;

            // 목적지까지의 거리
            int distToDes = getDistToDes(N, map);
            if (fuel < distToCus + distToDes || distToDes == -1) break;

            // 목적지에서 충전 - 승객까지 거리
            fuel += distToDes - distToCus;
            completed++;
        }

        if (completed == M) System.out.println(fuel);
        else System.out.println(-1);
    }

    // 가장 가까운 승객 찾기
    static int findNearest(int N, int[][] map) {
        Queue<Position> pq = new PriorityQueue<>();
        boolean[][] visited = new boolean[N][N];

        pq.add(new Position(curX, curY, 0));
        visited[curX][curY] = true;

        while (!pq.isEmpty()) {
            Position cur = pq.poll();

            // Priority queue로 정렬
            // -> 우선순위가 높은 승객이 먼저 탑승
            if (map[cur.x][cur.y] > 1) {
                curCustomer = map[cur.x][cur.y];
                curX = cur.x;
                curY = cur.y;
                map[curX][curY] = 0; // 탑승한 승객 제거
                return cur.dist; // 승객 탑승 시 탐색 종료
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (isValid(nx, ny, N) && !visited[nx][ny] && map[nx][ny] != 1) {
                    visited[nx][ny] = true;
                    pq.add(new Position(nx, ny, cur.dist + 1));
                }
            }
        }

        return -1;
    }

    // 목적지까지의 거리
    static int getDistToDes(int N, int[][] map) {
        Queue<Position> pq = new PriorityQueue<>();
        boolean[][] visited = new boolean[N][N];

        pq.add(new Position(curX, curY, 0));
        visited[curX][curY] = true;

        // 현재 승객의 목적지
        int destX = destinations[curCustomer].x;
        int destY = destinations[curCustomer].y;

        while (!pq.isEmpty()) {
            Position cur = pq.poll();

            if (cur.x == destX && cur.y == destY) {
                curX = destX;
                curY = destY;
                return cur.dist;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (isValid(nx, ny, N) && !visited[nx][ny] && map[nx][ny] != 1) {
                    visited[nx][ny] = true;
                    pq.add(new Position(nx, ny, cur.dist + 1));
                }
            }
        }

        return -1;
    }

    static boolean isValid(int x, int y, int N) {
        return x >= 0 && y >= 0 && x < N && y < N;
    }
}