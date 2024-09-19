import java.util.Arrays;

class Solution {
    public String solution(int[] numbers) {
        String answer = "";
        
        int cnt = 0;
        String[] nums = new String[numbers.length];
        for(int i=0; i<numbers.length; i++) {
            nums[i] = "" + numbers[i];
            if(numbers[i] == 0) cnt++;
        }
        
        if(cnt == numbers.length) return "0";
        
        Arrays.sort(nums, (o1, o2) -> {
            int n1 = Integer.parseInt(o1 + o2);
            int n2 = Integer.parseInt(o2 + o1);
            return n2 - n1;
        });
        
        for(String num : nums) {
            answer += num;
        }
        
        return answer;
    }
}