import java.util.Queue;
import java.util.ArrayDeque;
import java.util.ArrayList;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        
        Queue<Integer> work = new ArrayDeque<>();
        
        for(int i=0; i<speeds.length; i++) {
            double temp = (100.0 - progresses[i]) / speeds[i];
            work.offer((int) Math.ceil(temp));
        }
        
        int prev = work.peek(); //진행 중인 작업의 개발 시간
        int wait = 0; //출시 대기 중인 작업 수
        ArrayList<Integer> release = new ArrayList<>();
        
        for(int i=0; i<speeds.length; i++) {
            if(prev >= work.peek()) {
                work.poll();
                wait++;
            }
            else {
                prev = work.poll();
                release.add(wait);
                wait = 1;
            }
        }
        
        release.add(wait);
        
        int[] answer = release.stream().mapToInt(Integer::intValue).toArray();
        return answer;
    }
}