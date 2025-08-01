class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int answer = 0;
        
        int curHP = health;
        int time = 0;
        for(int[] attack : attacks) {
            int healTime = attack[0] - time - 1;
            // 초당 회복
            int heal = healTime * bandage[1];
            // 추가 회복
            heal += (healTime / bandage[0]) * bandage[2];
            curHP = Math.min(curHP+heal, health);
            time = attack[0];
            
            // 공격
            curHP -= attack[1];
            if(curHP <= 0) break;
        }
        
        return curHP > 0 ? curHP : -1;
    }
}