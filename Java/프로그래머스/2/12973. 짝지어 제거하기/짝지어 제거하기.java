import java.util.Stack;

class Solution
{
    public int solution(String s)
    {
        int answer;
        
        Stack<Character> stk = new Stack<>();
        
        for(char c : s.toCharArray()) {
            if(!stk.empty() && stk.peek() == c) stk.pop();
            else stk.push(c);
        }
        
        if(stk.empty()) answer = 1;
        else answer = 0;
        
        return answer;
    }
}