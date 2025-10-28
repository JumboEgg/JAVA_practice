import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int k = Integer.parseInt(br.readLine());

            Queue<Long> q = new PriorityQueue<>();
            Queue<Long> rq = new PriorityQueue<>(Comparator.reverseOrder());

            HashMap<Long, Integer> cnt = new HashMap<>();

            while (k-- > 0) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                if ("I".equals(st.nextToken())) {
                    long num = Long.parseLong(st.nextToken());
                    q.offer(num);
                    rq.offer(num);
                    cnt.put(num, cnt.getOrDefault(num, 0) + 1);
                } else {
                    if (cnt.isEmpty()) continue;
                    int order = Integer.parseInt(st.nextToken());
                    if (order == 1) remove(rq, cnt);
                    else remove(q, cnt);
                }
            }

            clean(q, cnt);
            clean(rq, cnt);

            if (q.isEmpty() || rq.isEmpty()) {
                sb.append("EMPTY\n");
            } else {
                sb.append(rq.peek()).append(" ").append(q.peek()).append("\n");
            }
        }

        System.out.print(sb);
    }

    static void remove(Queue<Long> q, HashMap<Long, Integer> cnt) {
        clean(q, cnt);
        if (!q.isEmpty()) {
            long num = q.poll();
            cnt.put(num, cnt.get(num)-1);
            if (cnt.get(num) == 0) cnt.remove(num);
        }
    }

    static void clean(Queue<Long> q, Map<Long, Integer> cnt) {
        while (!q.isEmpty() && !cnt.containsKey(q.peek())) {
            q.poll();
        }
    }
}
