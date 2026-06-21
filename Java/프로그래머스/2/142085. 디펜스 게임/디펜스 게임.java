import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        int answer = 0;
        
        Queue<Integer> es = new PriorityQueue<>(Collections.reverseOrder());
        int sum = 0; // 막은 적의 수
        int cnt = 0; // 사용한 무효권 개수
        
        while (n >= sum && answer < enemy.length) {
            es.offer(enemy[answer]);
            sum += enemy[answer];
            
            if(sum <= n) answer++;
            else if(cnt < k) {
                sum -= es.poll();
                cnt++;
                answer++;
            }
        }
        
        return answer;
    }
}