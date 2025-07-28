import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, R;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] rotation = new int[R][3];
        for(int i=0; i<R; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());

            rotation[i] = new int[]{r, c, s};
        }

        permute(map, rotation, 0, 0);

        System.out.println(min);
    }

    static void rotate(int[][] map, int[] rotation) {
        for(int s=1; s<=rotation[2]; s++) {
            int top = rotation[0]-s;
            int bottom = rotation[0]+s;
            int right = rotation[1]+s;
            int left = rotation[1]-s;

            int temp = map[top][left];

            for(int i=top; i<bottom; i++) map[i][left] = map[i+1][left];
            for(int i=left; i<right; i++) map[bottom][i] = map[bottom][i+1];
            for(int i=bottom; i>top; i--) map[i][right] = map[i-1][right];
            for(int i=right; i>left; i--) map[top][i] = map[top][i-1];

            map[top][left+1] = temp;
        }
    }

    static int getMin(int[][] map) {
        int[] sum = new int[N];
        int min = Integer.MAX_VALUE;
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                sum[i] += map[i][j];
            }
            min = Math.min(min, sum[i]);
        }
        return min;
    }

    static void permute(int[][] curMap, int[][] rotation, int isRotated, int cnt) {
        if(cnt == R) {
            min = Math.min(min, getMin(curMap));
            return;
        }

        for(int i=0; i<R; i++) {
            if((isRotated & (1<<i)) > 0) continue;
            int[][] tmpMap = copyMap(curMap);
            rotate(tmpMap, rotation[i]);
            int next = isRotated | (1<<i);
            permute(tmpMap, rotation, next, cnt+1);
        }
    }

    static int[][] copyMap(int[][] map) {
        int[][] newMap = new int[N][M];
        for(int i=0; i<N; i++)
            for(int j=0; j<M; j++)
                newMap[i][j] = map[i][j];

        return newMap;
    }
}
