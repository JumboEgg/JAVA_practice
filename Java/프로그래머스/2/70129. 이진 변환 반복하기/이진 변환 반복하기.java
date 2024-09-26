class Solution {
    public int[] solution(String s) {
        int[] answer = {0, 0};
        
        int sLen = 0;
        int newLen = 0;
        while(newLen != 1) {            
            sLen = s.length();
            s = s.replaceAll("0", "");
            newLen = s.length();
            s = Integer.toBinaryString(newLen);
            
            answer[0]++;
            answer[1] += sLen - newLen;
        }
        
        return answer;
    }
}