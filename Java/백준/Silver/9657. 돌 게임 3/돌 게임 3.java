import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // n번째 돌을 가져가는 사람이 상근이일 경우 TRUE
        boolean[] s = new boolean[N+1];
        for(int i=1; i<=5; i++) {
            if (i > N) break;
            s[i] = true;
        }
        if (N >= 2) s[2] = false;

        for(int i=6; i<=N; i++) {
            if (s[i-1] && s[i-3] && s[i-4]) continue;
            s[i] = true;
        }

        System.out.println(s[N] ? "SK" : "CY");
    }
}
