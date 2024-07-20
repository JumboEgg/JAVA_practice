import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] a) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] nums = br.readLine().split(" ");
    int N = Integer.parseInt(nums[0]);
    int M = Integer.parseInt(nums[1]);
    if((N*100) < M) System.out.println("No");
    else System.out.println("Yes");
    }
}