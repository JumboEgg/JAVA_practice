import java.util.ArrayList;

class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = getSize(brown, yellow);
        return answer;
    }
    
    int[] getSize(int b, int y) {
        double temp = Math.sqrt(4 - 2*b - 4*y + b*b/4.0);
        return new int[] {(int)(b/2.0+2+temp)/2, (int)(b/2.0+2-temp)/2};
    }
}