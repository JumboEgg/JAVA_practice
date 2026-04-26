import java.util.Queue;
import java.util.ArrayDeque;

class Solution {
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        
        int N = words.length;
        int L = begin.length();
        
        int end = -1;
        boolean[][] conn = new boolean[N+1][N+1];
        
        for(int i=0; i<N; i++) {
            if(target.equals(words[i])) end = i;
            for(int j=i+1; j<N; j++) {
                int cnt = 0;
                for(int k=0; k<L; k++) {
                    if(words[i].charAt(k) != words[j].charAt(k)) cnt++;
                }
                if(cnt == 1) conn[i][j] = conn[j][i] = true;
            }
            
            int cnt = 0;
            for(int k=0; k<L; k++) {
                if(words[i].charAt(k) != begin.charAt(k)) cnt++;
            }
            if(cnt == 1) conn[i][N] = conn[N][i] = true;
        }
        
        answer = findDist(conn, end, N);
        
        return answer;
    }
    
    int findDist(boolean[][] conn, int end, int N) {
        if (end < 0) return 0;
        
        boolean[] visited = new boolean[N+1];
        Queue<Integer> toVisit = new ArrayDeque<>();
        
        toVisit.offer(N);
        visited[N] = true;
        
        int cnt = -1;
        
        while(!toVisit.isEmpty()) {
            int size = toVisit.size();
            cnt++;
            
            while(size-- > 0) {
                int cur = toVisit.poll();
                if(cur == end) return cnt;
                
                for(int i=0; i<N; i++) {
                    if(visited[i]) continue;
                    if(!conn[cur][i]) continue;
                    
                    visited[i] = true;
                    toVisit.offer(i);
                }
            }
        }
        
        return 0;
    }
}