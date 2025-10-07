import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        long[] psum = new long[N+1];
        for(int i=1; i<=N; i++) {
            psum[i] = Integer.parseInt(br.readLine()) + psum[i-1];
        }

        long[] sum = new long[N+1];
        sum[M] = psum[M];
        long max = Math.max(sum[M], 0);
        long min = 0;
        for(int i=M+1; i<=N; i++) {
            min = Math.min(psum[i-M], min);
            sum[i] = Math.max(sum[i-1] + psum[i] - psum[i-1], psum[i] - min);
            max = Math.max(sum[i], max);
        }

        System.out.println(max);
    }
}
