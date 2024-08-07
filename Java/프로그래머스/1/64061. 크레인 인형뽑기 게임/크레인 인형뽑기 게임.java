import java.util.Stack;

class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        
        Stack<Integer> basket = new Stack<>();
        int crane = 0;
        for(int m : moves) {
            while(crane < board.length && board[crane][m-1] == 0) {
                crane++;
            }
            if(crane == board.length) {
                crane = 0;
                continue;
            }
            
            if(!basket.empty() && 
               basket.peek() == board[crane][m-1]) {
                answer += 2;
                basket.pop();
            }
            else {
                basket.push(board[crane][m-1]);
            }
            board[crane][m-1] = 0;
            
            crane = 0;
        }
        
        return answer;
    }
}