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
        int X = Integer.parseInt(st.nextToken());

        List<Road>[] roads = new ArrayList[N+1];
        List<Road>[] revRoads = new ArrayList[N+1];
        for(int i=0; i<=N; i++) {
            roads[i] = new ArrayList<>();
            revRoads[i] = new ArrayList<>();
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            roads[start].add(new Road(end, dist));
            revRoads[end].add(new Road(start, dist));
        }

        // 다익스트라를 양방햐으로 한 번씩 시행
        int[] dist = new int[N+1];
        Arrays.fill(dist, INF);
        dijkstra(dist, roads, X);

        int[] revDist = new int[N+1];
        Arrays.fill(revDist, INF);
        dijkstra(revDist, revRoads, X);

        int max = 0;
        for (int i=1; i<=N; i++) {
            max = Math.max(max, dist[i] + revDist[i]);
        }

        System.out.println(max);
    }

    static void dijkstra(int[] dist, List<Road>[] roads, int X) {
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
    }
}
