import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        char[] table = br.readLine().toCharArray();

        int cnt = 0;

        for(int i = 0; i < N; i++) {
            if(table[i] == 'P') {
                for(int j = Math.max(0, i - K); j <= Math.min(N - 1, i + K); j++) {
                    if(table[j] == 'H') {
                        table[j] = '.';
                        cnt++;
                        break;
                    }
                }
            }
        }

        System.out.println(cnt);
    }
}