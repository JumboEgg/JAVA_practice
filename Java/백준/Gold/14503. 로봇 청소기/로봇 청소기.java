import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        int count = 0;

        Queue<int[]> toVisit = new ArrayDeque<>();
        toVisit.offer(new int[] {R, C, D});

        while (!toVisit.isEmpty()) {
            int[] cur = toVisit.poll();
            int x = cur[0];
            int y = cur[1];
            int d = cur[2];

            if(map[x][y] == 0) {
                count++;
                map[x][y] = 2;
            }

            boolean moved = false;

            // 청소되지 않은 칸 탐색
            for(int i=0; i<4; i++) {
                int nd = (3+d-i)%4;
                int nx = x + dx[nd];
                int ny = y + dy[nd];

                if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                if(map[nx][ny] == 0) {
                    toVisit.offer(new int[] {nx, ny, nd});
                    moved = true;
                    break;
                }
            }

            // 청소할 칸이 없다면 후진
            if(!moved) {
                int nd = (d+2)%4;
                int nx = x + dx[nd];
                int ny = y + dy[nd];

                if(nx < 0 || nx >= N || ny < 0 || ny >= M) break;
                if(map[nx][ny] == 1) break;
                toVisit.offer(new int[] {nx, ny, d});
            }
        }

        System.out.println(count);
    }
}
