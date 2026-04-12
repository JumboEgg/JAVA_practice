import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int x = N - K * (K + 1) / 2;
        if(x < 0) {
            System.out.println(-1);
        } else {
            System.out.println(K - 1 + (x%K == 0 ? 0 : 1));
        }
    }
}
