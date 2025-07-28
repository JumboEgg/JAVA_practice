import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int TC = Integer.parseInt(br.readLine());
        while (TC-- > 0) {
            char[] commands = br.readLine().toCharArray();
            int N = Integer.parseInt(br.readLine());

            String input = br.readLine();
            String[] temp = input.substring(1, input.length()-1).split(",");

            List<Integer> nums = new ArrayList<>();
            for(String s : temp) {
                if("".equals(s)) continue;
                nums.add(Integer.parseInt(s));
            }

            int start = 0;
            int end = N-1;
            boolean isFliped = false;
            boolean error = false;

            for(char c : commands) {
                if(c == 'R') isFliped = !isFliped;
                else {
                    if(start > end) {
                        error = true;
                        break;
                    }
                    if(isFliped) end--;
                    else start++;
                }
            }

            if(error) sb.append("error\n");
            else {
                nums = nums.subList(start, end+1);
                if(isFliped) Collections.reverse(nums);
                sb.append(nums.toString().replace(" ", "")).append("\n");
            }
        }

        System.out.println(sb);
    }
}
