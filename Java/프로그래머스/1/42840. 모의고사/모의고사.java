import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

class Solution {
    public int[] solution(int[] answers) {
        int[] pattern1 = {1, 2, 3, 4, 5};
        int[] pattern2 = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] pattern3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        
        int[] ptlength = {pattern1.length, pattern2.length, pattern3.length};
        int[] scores = new int[3];
        
        for(int i=0; i<answers.length; i++) {
            if(pattern1[i%ptlength[0]] == answers[i]) scores[0]++;
            if(pattern2[i%ptlength[1]] == answers[i]) scores[1]++;
            if(pattern3[i%ptlength[2]] == answers[i]) scores[2]++;
        }
        
        int maxScore = Arrays.stream(scores).max().getAsInt();
        
        List<Integer> winner = new ArrayList<>();
        for(int i=0; i<scores.length; i++) {
            if(scores[i] == maxScore) {
                winner.add(i+1);
            }
        }
        int[] answer = winner.stream().mapToInt(Integer::intValue).toArray();
        return answer;
    }
}