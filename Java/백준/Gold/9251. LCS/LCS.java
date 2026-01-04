import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] s1 = br.readLine().toCharArray();
        char[] s2 = br.readLine().toCharArray();

        int l1 = s1.length;
        int l2 = s2.length;

        int[][] LCS = new int[l1+1][l2+1];

        for(int i=0; i<l1; i++) {
            for(int j=0; j<l2; j++) {
                if (s1[i] == s2[j]) LCS[i+1][j+1] = LCS[i][j] + 1;
                else LCS[i+1][j+1] = Math.max(LCS[i][j+1], LCS[i+1][j]);
            }
        }

        System.out.println(LCS[l1][l2]);
    }
}
