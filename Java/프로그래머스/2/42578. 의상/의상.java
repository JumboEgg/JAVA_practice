import java.util.HashMap;

class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;
        HashMap<String, Integer> cCnt = new HashMap<>();
        
        for(String[] cloth : clothes) {
            int cnt = cCnt.getOrDefault(cloth[1], 0) + 1;
            cCnt.put(cloth[1], cnt);
        }
        
        for(String key : cCnt.keySet()) {
            answer *= cCnt.get(key) + 1;
        }
        
        answer--;
        
        return answer;
    }
}