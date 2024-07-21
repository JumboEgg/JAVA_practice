import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String N = new String();
        while(!(N = br.readLine()).equals("0")) {
            int length = 4 * N.length() + 1;
            length = length - (int) N.chars().filter(c->c=='1').count()
                + (int) N.chars().filter(c->c=='0').count();
            System.out.println(length);
        }
    }
}