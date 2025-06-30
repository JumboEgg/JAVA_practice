import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    static class Position {
        int x, y;

        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N, M, P;
    static int[] S;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    static int[] result;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        S = new int[P+1]; // 플레이어 번호 1부터 시작
        st = new StringTokenizer(br.readLine());
        Queue<Position>[] position = new ArrayDeque[P+1];

        for(int i=1; i<=P; i++) {
            S[i] = Integer.parseInt(st.nextToken());
            position[i] = new ArrayDeque<>();
        }

        int[][] map = new int[N][M]; // 빈 공간 0, 성벽 1~9
        result = new int[P+1];

        for(int i=0; i<N; i++) {
            char[] temp = br.readLine().toCharArray();
            for(int j=0; j<M; j++) {
                if(temp[j] == '.') continue;
                if(temp[j] == '#') {
                    map[i][j] = -1;
                    continue;
                }

                // 성 위치 저장
                map[i][j] = temp[j] - '0';
                position[map[i][j]].offer(new Position(i, j));
                result[map[i][j]]++;
            }
        }

        bfs(position, map);

        for(int i=1; i<=P; i++) {
            System.out.print(result[i] + " ");
        }
    }

    static void bfs(Queue<Position>[] position, int[][] map) {
        int finished = 0;
        boolean[] isFinished = new boolean[P+1]; // 각 플레이어 탐색 완료 여부
        isFinished[0] = true;

        while (finished < P) {
            for(int i=1; i<=P; i++) {
                if(isFinished[i]) continue;
                int depth = 0;

                while (depth++ < S[i]) {
                    int size = position[i].size();
                    if(size == 0) break;

                    while (size-- > 0) {
                        Position cur = position[i].poll();

                        for(int j=0; j<4; j++) {
                            int nx = cur.x + dx[j];
                            int ny = cur.y + dy[j];

                            if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                            if(map[nx][ny] != 0) continue;

                            map[nx][ny] = i;
                            result[i]++;
                            position[i].offer(new Position(nx, ny));
                        }
                    }
                    if(position[i].isEmpty()) {
                        isFinished[i] = true;
                        finished++;
                    }
                }
            }
        }
    }
}
