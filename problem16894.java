//1946
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    final static int FOUNDATION = 1946;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int currentYear = Integer.parseInt(br.readLine());
        System.out.println(currentYear - FOUNDATION);
    }
}