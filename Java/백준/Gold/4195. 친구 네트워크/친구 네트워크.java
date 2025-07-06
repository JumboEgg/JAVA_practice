import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    static HashMap<String, Integer> name;
    static int[] friendOf;
    static int[] count;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int TC = Integer.parseInt(br.readLine());

        for(int tc=0; tc<TC; tc++) {
            int N = Integer.parseInt(br.readLine());

            int friendNum = 0;
            name = new HashMap<>();
            friendOf = new int[2*N + 1]; // 최대 친구 수 + 1
            count = new int[2*N + 1]; // 자신이 속한 그룹 인원

            for(int i=0; i<2*N+1; i++) {
                friendOf[i] = i;
                count[i] = 1;
            }

            for(int i=0; i<N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String name1 = st.nextToken();
                String name2 = st.nextToken();

                if(!name.containsKey(name1)) name.put(name1, ++friendNum);
                if(!name.containsKey(name2)) name.put(name2, ++friendNum);

                addFriend(name1, name2);

                int lead = getParentOf(name.get(name1));
                sb.append(count[lead]).append("\n");
            }
        }

        System.out.println(sb);
    }

    static void addFriend(String name1, String name2) {
        int f1 = name.get(name1);
        int f2 = name.get(name2);

        int p1 = getParentOf(f1);
        int p2 = getParentOf(f2);
        
        if(p1 == p2) return;

        friendOf[p2] = p1; // 친구 그룹 대표 변경
        count[p1] += count[p2]; // 친구 수 통합
    }

    static int getParentOf(int c) {
        if(friendOf[c] == c) return c;
        else return friendOf[c] = getParentOf(friendOf[c]);
    }
}
