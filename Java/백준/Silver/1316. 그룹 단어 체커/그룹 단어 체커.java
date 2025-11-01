import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int cnt = 0;

        while (N-- > 0) {
            char[] word = br.readLine().toCharArray();
            HashSet<Character> letters = new HashSet<>();
            boolean isGrouped = true;
            char prev = 0;

            for(char c : word) {
                if (prev == c) continue;
                if (letters.contains(c)) {
                    isGrouped = false;
                    break;
                }
                letters.add(c);
                prev = c;
            }

            if (isGrouped) cnt++;
        }

        System.out.println(cnt);
    }
}
