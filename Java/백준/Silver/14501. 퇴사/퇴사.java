import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] job = new int[N][2];
        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            job[i][0] = Integer.parseInt(st.nextToken());
            job[i][1] = Integer.parseInt(st.nextToken());
        }

        int[] profit = new int[N+2];
        for(int i=0; i<N; i++) {
            if(i+job[i][0]-1 <= N) {
                profit[i+job[i][0]] = Math.max(profit[i+job[i][0]], profit[i]+job[i][1]);
            }
            profit[i+1] = Math.max(profit[i+1], profit[i]);
        }

        System.out.println(profit[N]);
    }
}
