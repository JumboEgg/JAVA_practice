import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        // 숫자 개수, 각 값에 도달하는 계산식의 개수
        long[][] count = new long[N+1][21];
        count[1][nums[1]] = 1;

        for(int i=2; i<N; i++) {
            // i번째 숫자를 i-1번째까지 계산한 수식에 더하거나 뺀다.
            for(int j=0; j<=20; j++) {
                // i-1번째까지 계산한 결과가 j인 식이 존재할 때만 계산한다.
                if(count[i-1][j] == 0) continue;
                if(j+nums[i] <= 20) count[i][j+nums[i]] += count[i-1][j];
                if(j-nums[i] >= 0) count[i][j-nums[i]] += count[i-1][j];
            }
        }

        System.out.println(count[N-1][nums[N]]);
    }
}
