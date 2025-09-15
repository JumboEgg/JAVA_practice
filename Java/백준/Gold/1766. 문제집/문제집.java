import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class P implements Comparable<P> {
        int num;
        int prev;
        List<P> next;

        P(int num) {
            this.num = num;
            this.prev = 0;
            this.next = new ArrayList<>();
        }

        @Override
        public int compareTo(P o) {
            if (this.prev == o.prev) return this.num - o.num;
            return this.prev - o.prev;
        }
    }

    static int N, M;
    static P[] ps;
    static boolean[] solved;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        ps = new P[N+1];
        solved = new boolean[N+1];

        Queue<P> problems = new PriorityQueue<>();
        for(int i=1; i<=N; i++) {
            ps[i] = new P(i);
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            ps[B].prev++;
            ps[A].next.add(ps[B]);
        }

        for(int i=1; i<=N; i++) {
            if(ps[i].prev > 0) continue;
            problems.offer(ps[i]);
        }

        StringBuilder sb = new StringBuilder();
        while (!problems.isEmpty()) {
            P cur = problems.poll();
            for(P p : cur.next) {
                if(--p.prev == 0) problems.offer(p);
            }

            sb.append(cur.num).append(" ");
        }

        System.out.println(sb);
    }
}
