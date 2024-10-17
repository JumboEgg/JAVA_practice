import java.util.HashMap;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        int cnt = 0;
        boolean[] check = new boolean[privacies.length];

        String[] temp = today.split("\\.");

        int[] tdy = {
            Integer.parseInt(temp[0]),
            Integer.parseInt(temp[1]),
            Integer.parseInt(temp[2])
        };

        HashMap<Character, Integer> t = new HashMap<>();
        for(String term : terms) {
            temp = term.split(" ");
            t.put(term.charAt(0), Integer.parseInt(temp[1]));
        }

        for(int i=0; i<privacies.length; i++) {
            String[] start = privacies[i].split(" |\\.");
            char privacy = privacies[i].charAt(11);

            int[] date = {
                Integer.parseInt(start[0]),
                Integer.parseInt(start[1]),
                Integer.parseInt(start[2])
            };

            date[1] += t.get(privacy);

            if(date[2] == 1) {
                date[2] = 28;
                date[1]--;
            } else date[2]--;

            if(date[1] > 12) {
                date[0] += (date[1] - 1) / 12;
                date[1] = (date[1] - 1) % 12 + 1;
            }


            if(date[0] > tdy[0]) continue;
            if(date[0] == tdy[0] && date[1] > tdy[1]) continue;
            if(date[0] == tdy[0] && date[1] == tdy[1] && date[2] >= tdy[2]) continue;

            cnt++;
            check[i] = true;
        }

        int idx = 0;
        int[] answer = new int[cnt];
        for(int i=0; i<privacies.length; i++) {
            if(check[i]) answer[idx++] = i+1;
        }

        return answer;
    }
}