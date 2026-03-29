import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};

    static int N, M;
    static int[][] cheese;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        cheese = new int[N][M];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                cheese[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int time = 1;
        while (meltCheese()) time++;
        System.out.println(time);
    }

    static boolean meltCheese() {
        int[][] exposed = new int[N][M];
        Queue<int[]> toVisit = new ArrayDeque<>();

        exposed[0][0] = -1;
        toVisit.offer(new int[] {0, 0});

        while (!toVisit.isEmpty()) {
            int[] cur = toVisit.poll();
            for(int i=0; i<4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if(outOfBounds(nx, ny)) continue;
                if(exposed[nx][ny] < 0) continue;

                if(cheese[nx][ny] == 0) {
                    exposed[nx][ny] = -1;
                    toVisit.offer(new int[]{nx, ny});
                } else {
                    exposed[nx][ny]++;
                }
            }
        }

        return cheeseGone(exposed);
    }

    static boolean outOfBounds(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= M;
    }

    static boolean cheeseGone(int[][] melt) {
        int cheeseSize = 0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(melt[i][j] >= 2) cheese[i][j] = 0;
                if(cheese[i][j] > 0) cheeseSize++;
            }
        }

        return cheeseSize > 0;
    }
}
