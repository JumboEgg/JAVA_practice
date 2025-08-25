import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Road implements Comparable<Road> {
        int to;
        int dist;

        Road(int to, int dist) {
            this.to = to;
            this.dist = dist;
        }

        @Override
        public int compareTo(Road o) {
            return this.dist - o.dist;
        }
    }

    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        List<Road>[] roads = new ArrayList[N+1];
        for(int i=0; i<=N; i++) {
            roads[i] = new ArrayList<>();
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            roads[start].add(new Road(end, 1));
        }

        // 다익스트라
        int[] dist = new int[N+1];
        Arrays.fill(dist, INF);

        dist[X] = 0;
        Queue<Road> next = new PriorityQueue<>();
        next.offer(new Road(X, 0));

        while (!next.isEmpty()) {
            Road cur = next.poll();

            if (cur.dist > dist[cur.to]) continue;

            for (Road road : roads[cur.to]) {
                if (dist[road.to] > road.dist + cur.dist) {
                    dist[road.to] = road.dist + cur.dist;

                    next.offer(new Road(road.to, dist[road.to]));
                }
            }
        }

        boolean found = false;
        StringBuilder sb = new StringBuilder();
        for (int i=1; i<=N; i++) {
            if (dist[i] == K) {
                sb.append(i).append("\n");
                found = true;
            }
        }
        
        if (!found) sb.append(-1);

        System.out.println(sb);
    }
}
