import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] map;

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        Queue<int[]> land = new ArrayDeque<>();

        for (int i = 0; i< N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j< M; j++) {
                int temp = Integer.parseInt(st.nextToken());
                if (temp == 0) continue;

                map[i][j] = 1;
                land.offer(new int[] {i, j});
            }
        }

        int totalCnt = 0;
        int maxSize = 0;
        while (!land.isEmpty()) {
            int[] curLand = land.poll();
            int x = curLand[0];
            int y = curLand[1];

            if (map[x][y] != 1) continue;

            int size = searchIsland(x, y, ++totalCnt + 1);
            maxSize = Math.max(size, maxSize);
        }

        sb.append(totalCnt).append("\n").append(maxSize);

        System.out.println(sb);
    }

    static int searchIsland(int x, int y, int num) {
        int size = 0;

        Queue<int[]> toVisit = new ArrayDeque<>();
        toVisit.offer(new int[] {x, y});
        map[x][y] = num;

        while (!toVisit.isEmpty()) {
            int[] cur = toVisit.poll();
            size++;

            for(int i=0; i<4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                if (map[nx][ny] != 1) continue;

                map[nx][ny] = num;
                toVisit.offer(new int[] {nx, ny});
            }
        }

        return size;
    }
}
