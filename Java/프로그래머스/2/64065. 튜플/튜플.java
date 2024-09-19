import java.util.Arrays;
import java.util.HashMap;
import java.util.Collections;

class Solution {
    public int[] solution(String s) {
        String[] set = s.substring(2, s.length()-2).split("\\},\\{");
        Arrays.sort(set, (o1, o2) -> o1.length() - o2.length());
        
        int[] answer = new int[set.length];
        
        String[] prev = set[0].split(",");
        answer[0] = Integer.parseInt(prev[0]);
        
        for(int i=1; i<set.length; i++) {
            String[] nums = set[i].replace("}", "").split(",");
            Arrays.sort(nums);
            
            for(int j=0; j<i; j++) {
                if(nums[j].equals(prev[j])) continue;
                answer[i] = Integer.parseInt(nums[j]);
                break;
            }
            
            prev = nums;
            if(answer[i] == 0) answer[i] = Integer.parseInt(nums[i]);
        }
        
        return answer;
    }
}