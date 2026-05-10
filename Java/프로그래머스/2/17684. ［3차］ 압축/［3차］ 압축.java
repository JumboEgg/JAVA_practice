import java.util.HashMap;
import java.util.ArrayList;

class Solution {
    public int[] solution(String msg) {
        int[] answer = {};
        ArrayList<Integer> idxs = new ArrayList<>();
        
        HashMap<String, Integer> lzw = new HashMap<>();
        
        int idx = 1;
        int start = 0;
        int end = 1;
        
        for(char c='A'; c<='Z'; c++) lzw.put(Character.toString(c), idx++);
        
        while(start < msg.length()) {
            String sub = msg.substring(start, end);
            boolean flag = false;
            
            while(lzw.containsKey(sub)) {
                if(++end > msg.length()) {
                    flag = true;
                    break; 
                }
                sub = msg.substring(start, end);
            }
            
            if(!flag) {
                lzw.put(sub, idx++);
                sub = msg.substring(start, --end);
            }

            idxs.add(lzw.get(sub));
            
            start = end;
            end++;
        }
        
        answer = new int[idxs.size()];
        for(int i=0; i<idxs.size(); i++) answer[i] = idxs.get(i);
        
        return answer;
    }
}