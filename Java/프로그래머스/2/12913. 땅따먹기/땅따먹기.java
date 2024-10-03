class Solution {
    int solution(int[][] land) {
        int answer = 0;
        
        int h = land.length;
        int w = land[0].length;
        
        int[][] sum = new int[h][w];
        
        for(int i=0; i<w; i++) {
            sum[0][i] = land[0][i];
        }
        
        for(int i=1; i<h; i++) {
            for(int j=0; j<w; j++) {
                
                int max = -1;
                int maxIdx = -1;
                for(int k=0; k<w; k++) {
                    if(k == j) continue;
                    if(sum[i-1][k] >= max) {
                        max = sum[i-1][k];
                        maxIdx = k;
                    }
                }
                sum[i][j] = sum[i-1][maxIdx] + land[i][j];
            }
        }
        
        for(int k=0; k<w; k++) {
            if(sum[h-1][k] >= answer) {
                answer = sum[h-1][k];
            }
        }
        
        return answer;
    }
}