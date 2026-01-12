import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static long B;
    static int[][] A;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        B = Long.parseLong(st.nextToken());

        A = new int[N][N];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken()) % 1000;
            }
        }

        int[][] calc = calcApowB();

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                sb.append(calc[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }

    static int[][] calcApowB() {
        int[][] answer = new int[N][N];
        for(int i=0; i<N; i++)
            answer[i][i] = 1;

        while (B > 0) {
            if (B%2 == 1) answer = calculate(A, answer);
            A = calculate(A, A);
            B /= 2;
        }

        return answer;
    }

    static int[][] calculate(int[][] c1, int[][] c2) {
        int[][] result = new int[N][N];

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                for(int k=0; k<N; k++) {
                    result[i][j] += c1[i][k] * c2[k][j];
                }
                result[i][j] = result[i][j] % 1000;
            }
        }
        return result;
    }
}
