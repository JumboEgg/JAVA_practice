import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        System.out.println(fact(1, N));
    }
    public static BigInteger fact(int n, int N) {
        BigInteger result = BigInteger.valueOf(n);
        if(n<N) {
            int mid = (n+N)/2;
            return fact(n, mid).multiply(fact(mid+1, N));
        }
        return result;
    }
}