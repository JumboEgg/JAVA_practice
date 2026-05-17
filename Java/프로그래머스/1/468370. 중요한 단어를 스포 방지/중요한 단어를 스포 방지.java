import java.util.*;

class Solution {
    public int solution(String message, int[][] spoiler_ranges) {
        int answer = 0;
        int S = spoiler_ranges.length;
        int MAX = Integer.MAX_VALUE;
        
        HashSet<String> words = new HashSet<>();
        ArrayList<String> spoilers = new ArrayList<>();
        
        int wIdx = 0;
        int sIdx = 0;
        for(String m : message.split(" ")) {
            int wStart = wIdx;
            int wEnd = wIdx + m.length();
            int sStart = sIdx == S ? MAX : spoiler_ranges[sIdx][0];
            int sEnd = sIdx == S ? MAX : spoiler_ranges[sIdx][1];
            
            while(wStart > sEnd && sIdx < S) {
                sIdx++;
                sStart = sIdx == S ? MAX : spoiler_ranges[sIdx][0];
                sEnd = sIdx == S ? MAX : spoiler_ranges[sIdx][1];
            }
            
            if(sStart > wEnd-1) words.add(m);
            else spoilers.add(m);
            
            wIdx = wEnd + 1;
        }
        
        for(String spoiler : spoilers) {
            if(words.contains(spoiler)) continue;
            answer++;
            words.add(spoiler);
        }
        
        return answer;
    }
}