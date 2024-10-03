class Solution {
    public int solution(int[][] triangle) {
        int height = triangle.length;
        int width = triangle[height-1].length;
        
        int[][] sum = new int[height][width];
        sum[0][0] = triangle[0][0];
        
        int max = -1;
        
        for(int i=1; i<height; i++) {
            for(int j=0; j<=i; j++) {
                if(j==0) {
                    sum[i][j] = sum[i-1][j] + triangle[i][j];
                } else if(j==i) {
                    sum[i][j] = sum[i-1][j-1] + triangle[i][j];
                } else {
                    int left = sum[i-1][j-1] + triangle[i][j];
                    int right = sum[i-1][j] + triangle[i][j];
                    sum[i][j] = Math.max(left, right);
                }
                max = Math.max(max, sum[i][j]);
            }
        }
        
        int answer = max;
        return answer;
    }
}