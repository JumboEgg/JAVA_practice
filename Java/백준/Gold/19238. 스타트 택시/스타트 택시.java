import java.util.*;
import java.io.*;

public class Main {
    // BFS 위치 정보 클래스
    static class Position implements Comparable<Position> {
        int x, y, dist;
        
        public Position(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
        
        @Override
        public int compareTo(Position o) {
            if (this.dist == o.dist) {
                if (this.x == o.x) {
                    return this.y - o.y; // 거리와 행이 같으면 열 오름차순
                } else {
                    return this.x - o.x; // 거리가 같으면 행 오름차순
                }
            }
            return this.dist - o.dist; // 거리 오름차순
        }
    }
    
    static int[] dx = {-1, 1, 0, 0}; // 상하좌우
    static int[] dy = {0, 0, -1, 1};
    static int curX, curY, curCustomer; // 현재 위치 및 현재 고객
    static ArrayList<Position> destinations = new ArrayList<>(); // 목적지 정보
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int fuel = Integer.parseInt(st.nextToken());
        
        int[][] map = new int[N][N];
        
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
        
        // 승객 정보 저장 (승객 번호는 2부터 시작)
        for (int i = 2; i < M + 2; i++) {
            st = new StringTokenizer(br.readLine());
            int sx = Integer.parseInt(st.nextToken()) - 1;
            int sy = Integer.parseInt(st.nextToken()) - 1;
            int ex = Integer.parseInt(st.nextToken()) - 1;
            int ey = Integer.parseInt(st.nextToken()) - 1;
            
            map[sx][sy] = i; // 지도에 승객 번호 저장
            destinations.add(new Position(ex, ey, 0)); // 목적지 저장
        }
        
        int completedCount = 0;
        
        // M명의 승객을 모두 태우기
        while (completedCount < M) {
            // 1. 가장 가까운 승객 찾기
            int distToCustomer = findNearestCustomer(N, map);
            if (fuel < distToCustomer || distToCustomer == -1) {
                break; // 승객에게 갈 연료가 부족하거나 찾을 수 없음
            }
            
            // 2. 목적지까지의 거리 계산
            int distToDestination = findDestination(N, map);
            if (fuel < distToCustomer + distToDestination || distToDestination == -1) {
                break; // 목적지까지 갈 연료가 부족하거나 갈 수 없음
            }
            
            // 3. 연료 계산: 승객까지 가는데 쓴 연료 + 목적지까지 가는데 쓴 연료의 2배 충전
            fuel += distToDestination - distToCustomer;
            completedCount++;
        }
        
        if (completedCount == M) {
            System.out.println(fuel);
        } else {
            System.out.println(-1);
        }
    }
    
    // 가장 가까운 승객 찾기
    static int findNearestCustomer(int N, int[][] map) {
        PriorityQueue<Position> pq = new PriorityQueue<>();
        boolean[][] visited = new boolean[N][N];
        
        pq.add(new Position(curX, curY, 0));
        visited[curX][curY] = true;
        
        while (!pq.isEmpty()) {
            Position cur = pq.poll();
            
            if (map[cur.x][cur.y] > 1) { // 승객 발견
                curCustomer = map[cur.x][cur.y];
                curX = cur.x;
                curY = cur.y;
                map[curX][curY] = 0; // 승객을 태웠으므로 지도에서 제거
                return cur.dist;
            }
            
            // 4방향 탐색
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                
                if (isValid(nx, ny, N) && !visited[nx][ny] && map[nx][ny] != 1) {
                    visited[nx][ny] = true;
                    pq.add(new Position(nx, ny, cur.dist + 1));
                }
            }
        }
        
        return -1; // 승객을 찾을 수 없음
    }
    
    // 목적지까지의 최단거리 찾기
    static int findDestination(int N, int[][] map) {
        PriorityQueue<Position> pq = new PriorityQueue<>();
        boolean[][] visited = new boolean[N][N];
        
        pq.add(new Position(curX, curY, 0));
        visited[curX][curY] = true;
        
        // 현재 승객의 목적지 좌표
        int destX = destinations.get(curCustomer - 2).x;
        int destY = destinations.get(curCustomer - 2).y;
        
        while (!pq.isEmpty()) {
            Position cur = pq.poll();
            
            if (cur.x == destX && cur.y == destY) { // 목적지 도착
                curX = destX;
                curY = destY;
                return cur.dist;
            }
            
            // 4방향 탐색
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                
                if (isValid(nx, ny, N) && !visited[nx][ny] && map[nx][ny] != 1) {
                    visited[nx][ny] = true;
                    pq.add(new Position(nx, ny, cur.dist + 1));
                }
            }
        }
        
        return -1; // 목적지에 도달할 수 없음
    }
    
    // 유효한 좌표인지 확인
    static boolean isValid(int x, int y, int N) {
        return x >= 0 && y >= 0 && x < N && y < N;
    }
}