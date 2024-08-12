class Solution {
    public static String[] fr;
    
    public int solution(String[] friends, String[] gifts) {
        
        int size = friends.length;
        fr = friends;
        
        int[][] give = new int[size][size];
        
        for(String g : gifts) {
            String[] giftFromTo = g.split(" ");
            int giver = getIndex(giftFromTo[0]);
            int receiver = getIndex(giftFromTo[1]);
            give[giver][receiver]++;
        }
        
        int[] totalGive = new int[size];
        int[] totalGet = new int[size];
        int[] nextGet = new int[size];
        
        for(int i=0; i<size; i++) {
            for(int j=0; j<size; j++) {
                totalGive[i] += give[i][j];
                totalGet[j] += give[i][j];
            }
        }
        
        for(int i=0; i<size; i++) {
            for(int j=i+1; j<size; j++) {
                
                if(give[i][j] > give[j][i]) {
                    nextGet[i]++;
                }
                else if(give[i][j] < give[j][i]) {
                    nextGet[j]++;
                }
                else {
                    if(totalGive[i] - totalGet[i]
                       > totalGive[j] - totalGet[j]) {
                        nextGet[i]++;
                    }
                    else if (totalGive[i] - totalGet[i]
                       < totalGive[j] - totalGet[j]) {
                        nextGet[j]++;
                    }
                }
            }
        }
        
        int max = -1;
        for(int i=0; i<size; i++) {
            if(max < nextGet[i]) {
                max = nextGet[i];
            }
        }
        
        int answer = max;
        return answer;
    }
    
    public static int getIndex(String name) {
        for(int i=0; i<fr.length; i++) {
            if(name.equals(fr[i])) {
                return i;
            }
        }
        return -1;
    }
}