import java.util.HashMap;
import java.util.ArrayList;

class Solution {
    
    class Node {
        String parent;
        int amount;
        int profit;
        
        Node(String parent, int amount) {
            this.parent = parent;
            this.amount = amount;
        }
    }        
    
    //직원 정보 입력
    HashMap<String, Node> list = new HashMap<>();
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {

        
        //root node 생성
        list.put("-", new Node(null, 0));
        
        //list 생성
        for(int i=0; i<enroll.length; i++) {
            list.put(enroll[i], new Node(referral[i], 0));
        }
        
        //판매원 정보 입력
        for(int i=0; i<seller.length; i++) {
            int prof = amount[i] * 100;
            list.get(seller[i]).amount += amount[i];
            list.get(seller[i]).profit += prof;
            getProfit(seller[i], prof);
        }
        
        //계산 결과 저장
        int[] answer = new int[enroll.length];
        for(int i=0; i<enroll.length; i++) {
            answer[i] = list.get(enroll[i]).profit;
        }
        
        return answer;
    }
    
    //다단계에 상납하기
    void getProfit(String cur, int prof) {
        
        if(prof / 10 < 1) return;
        
        String parent = list.get(cur).parent;
        if(parent == null) return;

        list.get(parent).profit += prof / 10;
        list.get(cur).profit -= prof / 10;

        getProfit(parent, prof / 10);
    }
}