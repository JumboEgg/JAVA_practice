import java.util.Map;
import java.util.TreeMap;

class Solution {
    
    public int[] solution(int N, int[] stages) {
        int[] failedUserNum = new int[N+1];
        for(int i=0; i<stages.length; i++) {
            failedUserNum[stages[i]-1]++;
        }
        
        int[] playedUserNum = new int[N+1];
        playedUserNum[N] = failedUserNum[N];
        for(int i=N-1; i>=0; i--) {
            playedUserNum[i] = playedUserNum[i+1] + failedUserNum[i];
        }
        
        TreeMap<Integer,Double> failRatio = new TreeMap<>();
        for(int i=0; i<N; i++) {
            if(playedUserNum[i] == 0) {
                failRatio.put(i, 0.0);
                continue;
            }
            failRatio.put(i, (double) failedUserNum[i]/playedUserNum[i]);
        }
        
        int[] answer = new int[N];
        double max;
        int index;
        for(int i=0; i<N; i++) {
            max = -1;
            index = -1;
            for(Integer key : failRatio.keySet()) {
                if(max < failRatio.get(key)) {
                    max = failRatio.get(key);
                    index = key;
                }
            }
            answer[i] = index+1;
            failRatio.remove(index);
        }
        return answer;
    }
}