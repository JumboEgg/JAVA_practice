import java.util.HashMap;

class Solution {
    public String solution(String[] participant, String[] completion) {
        HashMap<String, Integer> list = new HashMap<>();
        
        for(String p : participant) {
            if(list.get(p) == null) {
                list.put(p, 0);
            }
            list.put(p, list.get(p) + 1);
        }
        
        for(String c : completion) {
            if(list.get(c) == 1) {
                list.remove(c);
            }
            else {
                list.put(c, list.get(c) - 1);
            }
        }
        
        String answer = "";
        
        for(String name : list.keySet()) {
            answer = name;
        }
        return answer;
    }
}