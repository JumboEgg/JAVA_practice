import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Edge implements Comparable<Edge> {
        int start;
        int end;
        int dist;

        Edge(int start, int end, int dist) {
            this.start = start;
            this.end = end;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge o) {
            return this.dist - o.dist;
        }
    }

    static int V;
    static int[] parents;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        parents = new int[V+1];
        for(int i=1; i<=V; i++) parents[i] = i;

        Queue<Edge> edges = new PriorityQueue<>();
        for(int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            edges.offer(new Edge(start, end, dist));
        }

        int sum = 0;

        while (!edges.isEmpty()) {
            Edge cur = edges.poll();
            if(sameParent(cur)) continue;
            connect(cur);
            sum += cur.dist;
        }

        System.out.println(sum);
    }

    static boolean sameParent(Edge e) {
        return getParent(e.start) == getParent(e.end);
    }

    static int getParent(int num) {
        if (parents[num] == num) return num;
        return parents[num] = getParent(parents[num]);
    }

    static void connect(Edge e) {
        int p = getParent(e.start);
        parents[p] = getParent(e.end);
    }
}
