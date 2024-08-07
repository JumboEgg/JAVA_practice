import java.util.Stack;

class Solution {
    public int solution(String s) {
        
        // (, ), {, }, [, ]
        int[] totalBrc = new int[3];
        char c;
        
        for(int i=0; i<s.length(); i++) {
            c = s.charAt(i);

            if(c=='(') totalBrc[0]++;
            else if(c == ')') totalBrc[0]--;

            else if(c == '{') totalBrc[1]++;
            else if(c == '}') totalBrc[1]--;

            else if(c == '[') totalBrc[2]++;
            else if(c == ']') totalBrc[2]--;
        }
        
        if(totalBrc[0] != 0 ||
           totalBrc[1] != 0 ||
           totalBrc[2] != 0) {
            return 0;
        }
        
        int count = 0;
        boolean valid = true;
        Stack<Character> str = new Stack<>();
        for(int i=0; i<s.length(); i++) {
            for(int j=0; j<s.length(); j++) {
                
                c = s.charAt((i+j)%s.length());
                
                if(c=='(') str.push(c);
                else if(c == '{') str.push(c);
                else if(c == '[') str.push(c);
                
                else if(c == ')') {
                    if(str.empty() || str.pop() != '(') {
                        valid = false;
                        break;
                    }
                }
                else if(c == '}') {
                    if(str.empty() || str.pop() != '{') {
                        valid = false;
                        break;
                    }
                }
                else if(c == ']') {
                    if(str.empty() || str.pop() != '[') {
                        valid = false;
                        break;
                    }
                }
            }
            if(valid && str.empty()) count++;
            str.clear();
            valid = true;
        }
        
//         int[] cntBrc = new int[3];
//         for(int i=0; i<s.length(); i++) {
            
//             for(int j=0; j<6; j++) {
//                 cntBrc[j] = 0;
//             }
            
//             for(int j=0; j<s.length(); j++) {
//                 c = s.charAt((i+j)%s.length());
                
//                 if(c=='(') cntBrc[0]++;
//                 else if(c == ')') {
//                     cntBrc[0]--;
//                     if(cntBrc[0] < 0) break;
//                 }
                
//                 else if(c == '{') cntBrc[1]++;
//                 else if(c == '}') {
//                     cntBrc[1]--;
//                     if(cntBrc[1] < 0) break;
//                 }
                
//                 else if(c == '[') cntBrc[2]++;
//                 else if(c == ']') {
//                     cntBrc[2]--;
//                     if(cntBrc[2] < 0) break;
//                 }
//             }
            
//             if(cntBrc[0] == 0 &&
//                cntBrc[1] == 0 &&
//                cntBrc[2] == 0) {
//                 count++;
//             }
//         }
        
        return count;
    }
}