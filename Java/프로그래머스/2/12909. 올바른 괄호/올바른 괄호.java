class Solution {
    boolean solution(String s) {
        
        int[] count = new int[2];
        
        for(String str : s.split("")) {
            if(str.equals("(")) count[0]++;
            else if (str.equals(")")) count[1]++;
            if(count[0] < count[1]) return false;
        }
        
        if(count[0] == count[1]) {
            return true;
        }
        
        return false;
    }
}