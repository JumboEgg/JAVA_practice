import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int K = Integer.parseInt(br.readLine());

        Stack<Integer> nums = new Stack<>();
        while (K-- > 0) {
            int n = Integer.parseInt(br.readLine());
            if (n == 0) nums.pop();
            else nums.push(n);
        }

        int sum = 0;
        for(int num : nums) sum += num;

        System.out.println(sum);
    }
}
