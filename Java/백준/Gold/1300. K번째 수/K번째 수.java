import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        int low = 1;
        int up = K;

        while (low < up) {
            int x = (low + up) / 2; // 중앙값
            int count = 0; // 중앙값까지의 숫자 개수

            for(int i=1; i<=N; i++) {
                count += Math.min(N, x/i); // 각 행의 x 이하 값 개수 합산
            }

            if(count < K) low = x + 1;
            else up = x;
        }

        System.out.println(low);
    }
}
