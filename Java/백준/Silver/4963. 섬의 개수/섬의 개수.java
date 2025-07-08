import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int W, H;
    static int[][] map;

    static int[] dx = {0, 0, 1, -1, 1, 1, -1, -1};
    static int[] dy = {1, -1, 0, 0, 1, -1, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            if (W == 0 && H == 0) break;

            map = new int[H][W];
            Queue<int[]> land = new ArrayDeque<>();

            for (int i=0; i<H; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j=0; j<W; j++) {
                    int temp = Integer.parseInt(st.nextToken());
                    if (temp == 0) continue;

                    map[i][j] = 1;
                    land.offer(new int[] {i, j});
                }
            }

            int totalCnt = 0;
            while (!land.isEmpty()) {
                int[] curLand = land.poll();
                int x = curLand[0];
                int y = curLand[1];

                if (map[x][y] != 1) continue;

                searchIsland(x, y, ++totalCnt + 1);
            }

            sb.append(totalCnt).append("\n");
        }

        System.out.println(sb);
    }

    static void searchIsland(int x, int y, int num) {
        Queue<int[]> toVisit = new ArrayDeque<>();
        toVisit.offer(new int[] {x, y});
        map[x][y] = num;

        while (!toVisit.isEmpty()) {
            int[] cur = toVisit.poll();

            for(int i=0; i<8; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (nx < 0 || nx >= H || ny < 0 || ny >= W) continue;
                if (map[nx][ny] != 1) continue;

                map[nx][ny] = num;
                toVisit.offer(new int[] {nx, ny});
            }
        }
    }
}
