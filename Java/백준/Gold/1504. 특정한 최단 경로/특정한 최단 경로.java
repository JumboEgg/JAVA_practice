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

    static final int INF = Integer.MAX_VALUE / 4;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        List<Road>[] roads = new ArrayList[N+1];
        for(int i=0; i<=N; i++) {
            roads[i] = new ArrayList<>();
        }

        for(int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            roads[start].add(new Road(end, dist));
            roads[end].add(new Road(start, dist));
        }

        st = new StringTokenizer(br.readLine());

        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        // 다익스트라
        int[] dist = new int[N+1];
        Arrays.fill(dist, INF);
        dijkstra(dist, roads, 1);

        int[] endDist = new int[N+1];
        Arrays.fill(endDist, INF);
        dijkstra(endDist, roads, N);

        int[] v1v2Dist = new int[N+1];
        Arrays.fill(v1v2Dist, INF);
        dijkstra(v1v2Dist, roads, v1);

        int min = Math.min(dist[v1] + endDist[v2] + v1v2Dist[v2],
                dist[v2] + endDist[v1] + v1v2Dist[v2]);

        System.out.println(min >= INF ? -1 : min);
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
