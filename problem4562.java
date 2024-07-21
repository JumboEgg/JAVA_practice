import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for (int i=0; i<N; i++) {
            String[] nums = br.readLine().split(" ");
            int X = Integer.parseInt(nums[0]);
            int Y = Integer.parseInt(nums[1]);
            if(X<Y) System.out.println("NO BRAINS");
            else System.out.println("MMM BRAINS");
        }
    }
}