import java.util.HashMap;
import java.util.HashSet;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        
        int[] answer = new int[id_list.length];
        
        //유저명, index 저장
        HashMap<String, Integer> user = new HashMap<>();
        for(int i=0; i<id_list.length; i++) {
            user.put(id_list[i], i);
        }
        
        //자신을 신고한 사람 목록 저장
        //신고당한 사람, 신고한 사람
        HashMap<String, HashSet<String>> reports = new HashMap<>();
        for(int i=0; i<report.length; i++) {
            String[] temp = report[i].split(" ");
            if(reports.get(temp[1]) == null) {
                reports.put(temp[1], new HashSet<>());
            }
            reports.get(temp[1]).add(temp[0]);
        }
        
        //유저별 신고당한 횟수
        int[] reportNum = new int[id_list.length];
        for(String s : reports.keySet()) {
            reportNum[user.get(s)] = reports.get(s).size();
        }
        
        for(int i=0; i<reportNum.length; i++) {
            if(reportNum[i] < k) continue;
            
            for(String s : reports.get(id_list[i])) {
                answer[user.get(s)]++;
            }
        }
        
        return answer;
    }
}