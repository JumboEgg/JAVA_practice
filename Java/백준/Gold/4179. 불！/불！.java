import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int R, C;
    static char[][] map;
    static int[][] fTime;
    static int[][] jTime;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        fTime = new int[R][C];
        jTime = new int[R][C];

        Queue<int[]> fQueue = new ArrayDeque<>();
        Queue<int[]> jQueue = new ArrayDeque<>();

        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = line.charAt(j);
                fTime[i][j] = INF;
                jTime[i][j] = -1;

                if (map[i][j] == 'F') {
                    fQueue.offer(new int[]{i, j});
                    fTime[i][j] = 0;
                } else if (map[i][j] == 'J') {
                    jQueue.offer(new int[]{i, j});
                    jTime[i][j] = 0;
                }
            }
        }

        spreadFire(fQueue);

        int result = move(jQueue);

        if (result == -1)
            System.out.println("IMPOSSIBLE");
        else
            System.out.println(result);
    }

    static void spreadFire(Queue<int[]> fireQ) {
        while (!fireQ.isEmpty()) {
            int[] cur = fireQ.poll();
            int y = cur[0], x = cur[1];

            for (int dir = 0; dir < 4; dir++) {
                int ny = y + dy[dir];
                int nx = x + dx[dir];

                if (ny < 0 || nx < 0 || ny >= R || nx >= C) continue;
                if (map[ny][nx] == '#' || fTime[ny][nx] != INF) continue;

                fTime[ny][nx] = fTime[y][x] + 1;
                fireQ.offer(new int[]{ny, nx});
            }
        }
    }

    static int move(Queue<int[]> toVisit) {
        while (!toVisit.isEmpty()) {
            int[] cur = toVisit.poll();
            int y = cur[0], x = cur[1];

            if (y == 0 || x == 0 || y == R - 1 || x == C - 1) {
                return jTime[y][x] + 1;
            }

            for (int dir = 0; dir < 4; dir++) {
                int ny = y + dy[dir];
                int nx = x + dx[dir];

                if (ny < 0 || nx < 0 || ny >= R || nx >= C) continue;
                if (map[ny][nx] == '#' || jTime[ny][nx] != -1) continue;

                int nextTime = jTime[y][x] + 1;
                if (fTime[ny][nx] <= nextTime) continue;

                jTime[ny][nx] = nextTime;
                toVisit.offer(new int[]{ny, nx});
            }
        }
        return -1;
    }
}

