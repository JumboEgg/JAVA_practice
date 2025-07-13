import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int answer = -1;

        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        for (int i=0; i<N; i++) {
            char[] temp = br.readLine().toCharArray();
            for (int j=0; j<M; j++) {
                map[i][j] = temp[j] - '0';
            }
        }

        Queue<int[]> toVisit = new ArrayDeque<>();
        boolean[][][] isVisited = new boolean[N][M][2];

        // x, y, 벽을 부수기 여부, 거리
        toVisit.offer(new int[] {0, 0, 0, 1});
        isVisited[0][0][0] = true;

        while (!toVisit.isEmpty()) {
            int[] cur = toVisit.poll();
            if (cur[0] == N-1 && cur[1] == M-1) {
                answer = cur[3];
                break;
            }

            for (int i=0; i<4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                int wall = cur[2];
                int dist = cur[3];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                if (isVisited[nx][ny][wall]) continue;

                if (map[nx][ny] == 0) {
                    isVisited[nx][ny][wall] = true;
                    toVisit.offer(new int[] {nx, ny, wall, dist+1});
                }
                else if (map[nx][ny] == 1 && wall == 0) {
                    isVisited[nx][ny][1] = true;
                    toVisit.offer(new int[] {nx, ny, 1, dist+1});
                }
            }
        }

        System.out.println(answer);
    }
}
