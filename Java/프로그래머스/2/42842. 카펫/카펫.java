import java.util.ArrayList;

class Solution {
    public int[] solution(int brown, int yellow) {
        ArrayList<Integer> carpetV = getFactor(brown + yellow);
        ArrayList<Integer> yellowV = getFactor(yellow);
        
        for(int i : yellowV) System.out.print(i + " ");
        
        int[] answer = null;
        for(int i=0; i<carpetV.size(); i++) {
            if(carpetV.get(i) < 3) continue;
            int carpetH = (brown + yellow) / carpetV.get(i);
            
            System.out.println(carpetH + " " + carpetV.get(i));
            
            for(int j=0; j<yellowV.size(); j++) {
                if(yellowV.get(j) > carpetV.get(i) - 2) break;
                if(carpetV.get(i) - yellowV.get(j) != 2) continue;
                
                int yellowH = yellow / yellowV.get(j);
                
                System.out.println(yellowH + " " + yellowV.get(j));
                
                if(yellowH > carpetH - 2) continue;
                
                answer = new int[] {carpetH, carpetV.get(i)};
            }
            
            if(answer != null) break;
        }
        
        return answer;
    }
    
    ArrayList<Integer> getFactor(int n) {
        ArrayList<Integer> factors = new ArrayList<>();
        for(int i=1; i<=Math.sqrt(n); i++) {
            if(n%i==0) factors.add(i);
        }
        
        return factors;
    }
}