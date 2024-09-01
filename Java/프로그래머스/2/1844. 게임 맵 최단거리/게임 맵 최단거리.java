import java.util.Queue;
import java.util.ArrayDeque;

class Solution {
    public int solution(int[][] maps) {
        
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};

        int n = maps.length;
        int m = maps[0].length;
        
        boolean[][] visited = new boolean[n][m];
        
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(maps[i][j] == 0) {
                    visited[i][j] = true;
                }
            }
        }
        
        int answer = -1;
        int step = 0;
        
        Queue<int[]> toVisit = new ArrayDeque<>();
        toVisit.offer(new int[] {0, 0});
        visited[0][0] = true;
        
        while(!toVisit.isEmpty()) {
            step++;
            int size = toVisit.size();
            
            while(size-- > 0) {
                int[] cur = toVisit.poll();
                
                if(cur[0] == n-1 && cur[1] == m-1) {
                    answer = step;
                    break;
                }
                
                for(int i=0; i<4; i++) {
                    int newX = cur[0] + dx[i];
                    int newY = cur[1] + dy[i];
                    
                    if(newX < 0 || newX >= n) continue;
                    if(newY < 0 || newY >= m) continue;
                    
                    if(visited[newX][newY]) continue;
                    
                    toVisit.offer(new int[] {newX, newY});
                    visited[newX][newY] = true;
                }
            }
            
            if(answer != -1) break;
        }
        
        return answer;
    }
}