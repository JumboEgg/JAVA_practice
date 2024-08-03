import java.util.Set;
import java.util.TreeSet;
import java.util.Iterator;

class Solution {
    public int[] solution(int[] numbers) {
        Set<Integer> result = new TreeSet<>();
        int length = numbers.length;
        
        for(int i=0; i<length; i++) {
            for(int j=i+1; j<length; j++) {
                result.add(numbers[i]+numbers[j]);
            }
        }
        
        Iterator<Integer> ans = result.iterator();
        int[] answer = new int[result.size()];
        for(int i=0; i<result.size(); i++) {
            answer[i] = ans.next().intValue();
        }
        
        return answer;
    }
}