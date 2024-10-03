class Solution
{
    public int solution(int [][]board)
    {
        int answer = 0;
        
        int w = board.length;
        int h = board[0].length;
        
        int[][] square = new int[w][h];
        for(int i=0; i<w; i++) {
            square[i][0] = board[i][0] == 1 ? 1 : 0;
            answer = Math.max(answer, square[i][0]);
        }
        for(int j=0; j<h; j++) {
            square[0][j] = board[0][j] == 1 ? 1 : 0;
            answer = Math.max(answer, square[0][j]);
        }
        
        for(int i=1; i<w; i++) {
            for(int j=1; j<h; j++) {
                if(board[i][j] == 0) continue;
                square[i][j] = 1;
                
                if(square[i-1][j-1] > 0
                  && square[i-1][j] > 0
                  && square[i][j-1] > 0) {
                    square[i][j] = Math.min(square[i-1][j-1],
                                           Math.min(square[i][j-1],
                                           square[i-1][j])) + 1;
                }
                
                answer = Math.max(answer, square[i][j]);
            }
        }
        answer *= answer;
        
        return answer;
    }
}