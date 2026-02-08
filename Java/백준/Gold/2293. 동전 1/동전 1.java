import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] coins = new int[n];
        for(int i=0; i<n; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(coins);

        int[][] costs = new int[n][k+1];
        for(int i=1; i<=k/coins[0]; i++) {
            costs[0][i*coins[0]] = 1;
        }
        for(int i=0; i<n; i++) {
            costs[i][0] = 1;
        }
        for(int c=1; c<n; c++) {
            for(int i=1; i<=k; i++) {
                for(int j=0; j<=i/coins[c]; j++) {
                    costs[c][i] += costs[c-1][i-j*coins[c]];
                }
            }
        }

        System.out.println(costs[n-1][k]);
    }
}
