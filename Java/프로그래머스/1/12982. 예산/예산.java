import java.util.Arrays;

class Solution {
    public int solution(int[] d, int budget) {
        int answer = 0;
        
        Arrays.sort(d);
        
        if(d[0] > budget) return 0;
        if(d.length == 1) return 1;
        
        int used = d[answer++];
        while((used += d[answer]) <= budget) {
            answer++;
            if(answer == d.length) break;
        }
        
        return answer;
    }
}