import java.util.TreeMap;
import java.util.Comparator;

class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        
        TreeMap<Integer, Integer> cCnt = new TreeMap<>(Comparator.reverseOrder());
        for(int c : citations) {
            int cnt = cCnt.getOrDefault(c, 0) + 1;
            cCnt.put(c, cnt);
        }
        
        int sum = 0;
        int max = cCnt.firstKey();
        for(int i=max; i>=0; i--) {
            sum += cCnt.getOrDefault(i, 0);
            if(sum < i) continue;
            
            answer = i;
            break;
        }
        
        return answer;
    }
}