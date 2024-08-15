import java.util.Queue;
import java.util.ArrayDeque;

class Solution {
    public String solution(String[] cards1, String[] cards2, String[] goal) {
//         Queue<String> c1 = new ArrayDeque<>();
//         Queue<String> c2 = new ArrayDeque<>();
        
//         for(String c : cards1) {
//             c1.offer(c);
//         }
        
//         for(String c : cards2) {
//             c2.offer(c);
//         }
        
//         boolean available = true;
        
//         for(String s : goal) {
//             if(!c1.isEmpty() && s.equals(c1.peek())) {
//                 c1.poll();
//             }
//             else if(!c2.isEmpty() && s.equals(c2.peek())) {
//                 c2.poll();
//             }
//             else {
//                 available = false;
//                 break;
//             }
//         }
        
        int index1 = 0, index2 = 0;
        boolean available = true;
        
        for(String s : goal) {
            if(index1 < cards1.length &&
              cards1[index1].equals(s)) {
                index1++;
            }
            else if(index2 < cards2.length &&
                   cards2[index2].equals(s)) {
                index2++;
            }
            else {
                available = false;
                break;
            }
        }
        
        String answer = "";
        
        if(available) answer = "Yes";
        else answer = "No";
        return answer;
    }
}