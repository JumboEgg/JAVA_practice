import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Position implements Comparable<Position> {
        int pos, time;
        Position(int pos, int time) {
            this.pos = pos;
            this.time = time;
        }

        @Override
        public int compareTo(Position o) {
            return this.time - o.time;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int time = search(N, K);

        System.out.println(time);
    }

    static int search(int N, int K) {
        Queue<Position> toVisit = new PriorityQueue<>();
        int[] visitied = new int[200001];

        toVisit.offer(new Position(N, 0));
        Arrays.fill(visitied, 200000);
        visitied[N] = 0;

        while (!toVisit.isEmpty()) {
            Position cur = toVisit.poll();

            if (cur.pos == K) {
                return cur.time;
            }

            int[] next = new int[] {cur.pos + 1, cur.pos - 1, cur.pos * 2};
            int[] time = new int[] {cur.time + 1, cur.time + 1, cur.time};
            for(int i=0; i<3; i++) {
                if (next[i] > 200000 || next[i] < 0) continue;
                if (visitied[next[i]] <= time[i]) continue;

                visitied[next[i]] = time[i];
                toVisit.offer(new Position(next[i], time[i]));
            }
        }

        return -1;
    }
}
