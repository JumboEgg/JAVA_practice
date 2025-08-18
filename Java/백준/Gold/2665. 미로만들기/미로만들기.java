import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
    final static int INF = Integer.MAX_VALUE;
    static int[] dx = { 0, 0, 1, -1 };
    static int[] dy = { 1, -1, 0, 0 };

    static int N;
    static int[][] map;
    static int min;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for(int i=0; i<N; i++) {
            char[] input = br.readLine().toCharArray();
            for(int j=0; j<N; j++) {
                map[i][j] = input[j] - '0';
            }
        }

        min = INF;

        bfs();

        System.out.println(min);
    }

    static void bfs() {
        Queue<int[]> toVisit = new ArrayDeque<>();
        boolean[][][] visited = new boolean[N][N][N*N];

        if (map[0][0] == 1) {
            toVisit.offer(new int[] {0, 0, 0});
            visited[0][0][0] = true;
        } else {
            toVisit.offer(new int[] {0, 0, 1});
            visited[0][0][1] = true;
        }

        while (!toVisit.isEmpty()) {
            int[] cur = toVisit.poll();

            int px = cur[0];
            int py = cur[1];
            int pw = cur[2]; // 현재까지 부순 벽 개수

            if (px == N-1 && py == N-1) {
                min = Math.min(min, pw);
            }

            for (int i=0; i<4; i++) {
                int nx = px + dx[i];
                int ny = py + dy[i];

                if (isOutOfBound(nx, ny)) continue;
                int nw = map[nx][ny] == 1 ? pw : pw + 1;
                if (nw >= min) continue;
                if (visited[nx][ny][nw]) continue;

                toVisit.offer(new int[] {nx, ny, nw});
                visited[nx][ny][nw] = true;
            }
        }
    }

    static boolean isOutOfBound(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= N;
    }
}
