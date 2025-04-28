class Solution {
    public int solution(int[] wallet, int[] bill) {
        int answer = 0;
        int wMin = wallet[1] > wallet[0] ? wallet[0] : wallet[1];
        int wMax = wallet[1] > wallet[0] ? wallet[1] : wallet[0];
        
        while(Math.min(bill[0], bill[1]) > wMin
             || Math.max(bill[0], bill[1]) > wMax) {
            if (bill[0] > bill[1]) bill[0] /= 2;
            else bill[1] /= 2;
            answer++;
        }
        
        return answer;
    }
}