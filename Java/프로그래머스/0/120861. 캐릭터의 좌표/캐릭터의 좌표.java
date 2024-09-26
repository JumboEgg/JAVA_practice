import java.util.HashMap;

class Solution {
    public int[] solution(String[] keyinput, int[] board) {
        HashMap<String, int[]> key = new HashMap<>();
        key.put("up", new int[] {0, 1});
        key.put("down", new int[] {0, -1});
        key.put("left", new int[] {-1, 0});
        key.put("right", new int[] {1, 0});
        
        int[] answer = {0, 0};
        for(int i=0; i<keyinput.length; i++) {
            int nX = answer[0] + key.get(keyinput[i])[0];
            int nY = answer[1] + key.get(keyinput[i])[1];
            
            if(Math.abs(nX) > board[0]/2) continue;
            if(Math.abs(nY) > board[1]/2) continue;
            
            answer[0] = nX;
            answer[1] = nY;
        }
        return answer;
    }
}