class Solution {
    public int solution(int n) {
        int[] tiles = new int[n+1];
        tiles[0] = 1;
        tiles[1] = 1;
        for(int i=2; i<=n; i++) {
            tiles[i] = tiles[i-1] + tiles[i-2];
            tiles[i] %= 1000000007;
        }
        
        int answer = tiles[n];
        return answer;
    }
}