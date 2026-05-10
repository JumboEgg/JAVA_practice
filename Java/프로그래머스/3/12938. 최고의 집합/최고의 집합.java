import java.util.ArrayList;

class Solution {
    public int[] solution(int n, int s) {
        int[] answer = new int[n];
        
        int a = s/n;
        int b = s%n;
        
        if(a == 0) return new int[]{-1};
        
        for(int i=0; i<n-b; i++) answer[i] = a;
        for(int i=n-b; i<n; i++) answer[i] = a+1;
        
        return answer;
    }
}