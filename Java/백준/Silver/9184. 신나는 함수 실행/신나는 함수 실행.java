import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int[][][] w = new int[21][21][21];

        for(int a=0; a<=20; a++) {
            for(int b=0; b<=20; b++) {
                for(int c=0; c<=20; c++) {
                    if (a==0 || b==0 || c==0) {
                        w[a][b][c] = 1;
                    }
                    else if (a<b && b<c) {
                        w[a][b][c] = w[a][b][c-1] + w[a][b-1][c-1] - w[a][b-1][c];
                    } else {
                        w[a][b][c] = w[a-1][b][c] + w[a-1][b-1][c] + w[a-1][b][c-1] - w[a-1][b-1][c-1];
                    }
                }
            }
        }

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (a==-1 && b==-1 && c==-1) break;

            int ans = 0;
            if (a <= 0 || b <= 0 || c <= 0) ans = 1;
            else if (a > 20 || b > 20 || c > 20) ans = w[20][20][20];
            else ans = w[a][b][c];

            sb.append("w(" + a + ", " + b + ", " + c + ") = " + ans + "\n");
        }

        System.out.print(sb);
    }
}