import java.util.HashSet;

class Solution {
    public int solution(int[] nums) {
        
        HashSet<Integer> ponketmon = new HashSet<>();
        
        for(Integer i : nums) {
            ponketmon.add(i);
        }
        
        int answer = Math.min(nums.length/2,ponketmon.size());
        return answer;
    }
}