import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        String vowel = "aeiou";
        int count = 0;
        for (int i=0; i<str.length(); i++) {
            for (int j=0; j<5; j++) {
                if(str.charAt(i) == vowel.charAt(j)) count++;
            }
        }
        System.out.println(count);
    }
}