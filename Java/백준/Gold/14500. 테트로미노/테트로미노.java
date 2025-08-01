import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    static int N, M;
    static int[][] map;
    static boolean[][] selected;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N+2][M+2];
        selected = new boolean[N+1][M+1];

        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        for(int i=1; i<=N; i++) {
            for(int j=1; j<=M; j++) {
                selected[i][j] = true;
                getMaxSum(i, j, selected, 0, map[i][j]);
                selected[i][j] = false;

                // ㅜ 모양 탐색
                getMaxTSum(i, j);
            }
        }

        System.out.println(max);
    }

    static void getMaxSum(int prevX, int prevY, boolean[][] selected, int depth, int sum) {
        if(depth == 3) {
            max = Math.max(max, sum);
            return;
        }

        for(int i=0; i<4; i++) {
            int nx = prevX + dx[i];
            int ny = prevY + dy[i];

            if(outOfBound(nx, ny)) continue;
            if(selected[nx][ny]) continue;

            selected[nx][ny] = true;
            getMaxSum(nx, ny, selected, depth + 1, sum + map[nx][ny]);
            selected[nx][ny] = false;
        }
    }

    static boolean outOfBound(int nx, int ny) {
        return nx < 1 || nx > N || ny < 1 || ny > M;
    }

    static void getMaxTSum(int x, int y) {
        int sum = map[x][y];
        for(int i=0; i<4; i++) {
            sum += map[x+dx[i]][y+dy[i]];
        }

        for(int i=0; i<4; i++) {
            max = Math.max(max, sum-map[x+dx[i]][y+dy[i]]);
        }
    }
}
