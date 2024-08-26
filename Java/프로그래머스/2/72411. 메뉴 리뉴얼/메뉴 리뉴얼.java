import java.util.HashMap;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;

class Solution {
    //course 길이별 가능한 메뉴 조합 저장
    HashMap<String, Integer> menuComb = new HashMap<>();
    
    public String[] solution(String[] orders, int[] course) {
        
        //orders 내 항목 정렬
        for(int i=0; i<orders.length; i++) {
            char[] temp = orders[i].toCharArray();
            Arrays.sort(temp);
            orders[i] = String.valueOf(temp);
        }
        
        //선택된 메뉴 조합 저장
        ArrayList<String> selected = new ArrayList<>();
        
        for(int length : course) {
            for(String order : orders) {
                //주문별 메뉴 조합
                menuSelect(order, length, "");
            }
            //조합을 생성할 수 없으면 생략
            if(menuComb.isEmpty()) continue;
            
            //주문 수가 최대인 조합의 주문량
            int max = Collections.max(menuComb.values());
            
            if(max < 2) {
                menuComb.clear();
                continue;
            }
            
            //주문 수가 최대인 조합 저장
            for(String key : menuComb.keySet()) {
                if(menuComb.get(key) == max) {
                    selected.add(key);
                }
            }
            //초기화
            menuComb.clear();
        }
        //코스요리 오름차순 정렬
        Collections.sort(selected);
        
        String[] answer = selected.toArray(new String[selected.size()]);
        return answer;
    }
    
    void menuSelect(String order, int length, String curMenu) {
        if(length == 0) {
            if(menuComb.get(curMenu) == null) {
                menuComb.put(curMenu, 0);
            }
            menuComb.put(curMenu, menuComb.get(curMenu) + 1);
        }
        
        if("".equals(order)) {
            return;
        }
        
        for(int i=0; i<order.length(); i++) {
            menuSelect(order.substring(i+1), length - 1, curMenu + order.charAt(i));
        }
    }
}