import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split("-");

        int answer = getSum(input[0]);
        for(int i=1; i<input.length; i++) {
            answer -= getSum(input[i]);
        }

        System.out.println(answer);
    }

    static int getSum(String input) {
        int sum = 0;
        String[] nums = input.split("\\+");

        for(String num : nums) {
            sum += Integer.parseInt(num);
        }

        return sum;
    }
}
