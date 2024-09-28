import java.util.Collections;
import java.util.HashSet;

class Solution {
    public int solution(int[] topping) {
        int answer = 0;
        
        int T = topping.length;
        
        int[] cjftn = new int[T];
        int[] ehdtod = new int[T];
        
        HashSet<Integer> cjf = new HashSet<>();
        HashSet<Integer> ehd = new HashSet<>();
        
        for(int i=0; i<T-1; i++) {
            cjf.add(topping[i]);
            ehd.add(topping[T-i-1]);
            
            cjftn[i] = cjf.size();
            ehdtod[T-i-2] = ehd.size();
        }
        
        for(int i=0; i<T-1; i++) {
            // System.out.println(cjftn[i] + " " + ehdtod[i]);
            if(cjftn[i] == ehdtod[i]) answer++;
        }
        
        return answer;
    }
}