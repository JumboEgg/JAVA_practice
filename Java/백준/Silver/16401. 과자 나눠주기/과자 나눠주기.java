import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int M;
    static int[] cookies;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        cookies = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            cookies[i] = Integer.parseInt(st.nextToken());
        }

        int answer = getLength();
        System.out.println(answer);
    }

    static int getLength() {
        int start = 1;
        int end = Arrays.stream(cookies).max().getAsInt();

        int maxLength = 0;
        while (start <= end) {
            int mid = (start + end) / 2;
            int cnt = 0;

            for (int cookie : cookies) cnt += cookie / mid;

            if (cnt >= M) {
                maxLength = mid;
                start = mid + 1;
            } else end = mid - 1;
        }

        return maxLength;
    }
}
