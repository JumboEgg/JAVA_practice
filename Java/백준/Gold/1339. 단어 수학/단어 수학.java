import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

public class Main {
    static int max;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        HashMap<Character, Integer> temp = new HashMap<>();
        int[] sums = new int[10];

        while (N-- > 0) {
            char[] input = br.readLine().toCharArray();
            int digit = 1;
            for(int i=input.length-1; i>=0; i--) {
                int cur = temp.getOrDefault(input[i], 0);
                temp.put(input[i], cur + digit);
                digit *= 10;
            }
        }

        int idx = 0;
        for(char c : temp.keySet()) {
            sums[idx++] = temp.get(c);
        }

        boolean[] nums = new boolean[10];
        getMax(sums, nums, 0, 0);

        System.out.println(max);
    }

    static void getMax(int[] sums, boolean[] nums, int idx, int sum) {
        if(idx > 9) {
            max = Math.max(max, sum);
            return;
        }

        for(int i=0; i<10; i++) {
            if(nums[i]) continue;
            nums[i] = true;
            getMax(sums, nums, idx+1, sum+sums[idx]*i);
            nums[i] = false;
        }
    }
}
