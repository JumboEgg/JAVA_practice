import java.util.HashMap;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        
        //grocery list : product, number
        HashMap<String, Integer> groceries = new HashMap<>();
        
        //grocery list backup
        for(int i=0; i<want.length; i++) {
            groceries.put(want[i], number[i]);
        }
        
        //discount list
        HashMap<String, Integer> canGet = new HashMap<>();
        
        //initialize
        for(int i=0; i<10; i++) {
            if(canGet.get(discount[i]) == null) {
                canGet.put(discount[i], 0);
            }
            canGet.put(discount[i], canGet.get(discount[i]) + 1);
        }
        
        boolean match;
        
        for(int i=0; i<discount.length; i++) {
            match = true;
            
            for(String s : groceries.keySet()) {
                if(canGet.get(s) == null ||
                   groceries.get(s) > canGet.get(s)) {
                    match = false;
                    break;
                }
            }
            
            if(match) answer++;
            
            canGet.put(discount[i], canGet.get(discount[i]) - 1);
            
            if(i+10 >= discount.length) continue;
            
            if(canGet.get(discount[i + 10]) == null) {
                canGet.put(discount[i + 10], 0);
            }
            canGet.put(discount[i + 10], canGet.get(discount[i + 10]) + 1);
        }
        
        return answer;
    }
}