import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Doc{
        int priority;
        int order;

        Doc(int priority, int order) {
            this.priority = priority;
            this.order = order;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        Queue<Integer> priorities = new PriorityQueue<>(Comparator.reverseOrder());
        Queue<Doc> docs = new ArrayDeque<>();

        int TC = Integer.parseInt(br.readLine());
        while (TC-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());

            for (int i=0; i<N; i++) {
                int doc = Integer.parseInt(st.nextToken());

                priorities.offer(doc);
                docs.offer(new Doc(doc, i));
            }


            for (int i=1; i<=N; i++) {
                int p = priorities.poll();
                while (p != docs.peek().priority) {
                    Doc d = docs.poll();
                    docs.offer(d);
                }

                Doc cur = docs.poll();
                if (M == cur.order) {
                    sb.append(i).append("\n");
                    break;
                }
            }

            priorities.clear();
            docs.clear();
        }

        System.out.print(sb);
    }
}
