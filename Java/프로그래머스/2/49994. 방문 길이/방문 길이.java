import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

class Solution {
    public int solution(String dirs) {
        int answer = 0;
        
        HashMap<String, int[]> move = new HashMap<>();
        move.put("U", new int[]{0, 1});
        move.put("D", new int[]{0, -1});
        move.put("R", new int[]{1, 0});
        move.put("L", new int[]{-1, 0});
        
        HashSet<String> route = new HashSet<>();
        int[] dir = new int[2];
        int[] cur = {0, 0};
        
        for(String d : dirs.split("")) {
            dir = move.get(d);
            if(Math.abs(cur[0] + dir[0]) > 5 ||
               Math.abs(cur[1] + dir[1]) > 5) {
                continue;
            }
            route.add(cur[0] + " " + cur[1] + " " + (cur[0] + dir[0]) + " " + (cur[1] + dir[1]));
            route.add((cur[0] + dir[0]) + " " + (cur[1] + dir[1]) + " " + cur[0] + " " + cur[1]);
            cur[0] += dir[0];
            cur[1] += dir[1];
            System.out.println(cur[0] + " " + cur[1]);
        }

        answer = route.size() / 2;
        
        return answer;
    }
}