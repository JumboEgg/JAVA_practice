import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] pSum = new int[N+1][M+1];
        for(int i=1; i<=N; i++) {
            for(int j=1; j<=M; j++) {
                pSum[i][j] = map[i-1][j-1] + pSum[i-1][j] + pSum[i][j-1] - pSum[i-1][j-1];
            }
        }

        int max = Integer.MIN_VALUE;
        for(int n=1; n<=N; n++) { // 영역의 세로 길이
            for(int m=1; m<=M; m++) { // 영역의 가로 길이
                for(int i=0; i<=N-n; i++) {
                    for(int j=0; j<=M-m; j++) {
                        int sum = pSum[i+n][j+m] - pSum[i][j+m] - pSum[i+n][j] + pSum[i][j];
                        max = Math.max(sum, max);
                    }
                }
            }
        }

        System.out.println(max);
    }
}
