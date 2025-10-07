import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        boolean[] isNotPrime = new boolean[N+1];
        isNotPrime[0] = true;
        isNotPrime[1] = true;

        for(int i=2; i<=Math.sqrt(N); i++) {
            if (isNotPrime[i]) continue;
            for(int j=2; j<=N/i; j++) {
                isNotPrime[i*j] = true;
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=M; i<=N; i++) {
            if (isNotPrime[i]) continue;
            sb.append(i).append("\n");
        }

        System.out.print(sb);
    }
}
