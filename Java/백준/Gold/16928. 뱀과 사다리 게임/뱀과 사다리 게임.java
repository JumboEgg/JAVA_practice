import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static final int INF = Integer.MAX_VALUE;
    static int[] connection;

    static class Route implements Comparable<Route> {
        int pos;
        int cnt;

        Route(int pos, int cnt) {
            this.pos = pos;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Route o) {
            return this.cnt - o.cnt;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        connection = new int[101];
        int input = Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken());

        while (input-- > 0) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            connection[start] = end;
        }

        int answer = searchDist();
        System.out.println(answer);
    }

    static int searchDist() {
        Queue<Route> toVisit = new PriorityQueue<>();
        toVisit.offer(new Route(1, 0));

        int[] visited = new int[101];
        Arrays.fill(visited, INF);
        visited[1] = 0;

        while (!toVisit.isEmpty()) {
            Route cur = toVisit.poll();

            if(cur.pos == 100) return cur.cnt;

            if(connection[cur.pos] == 0) {
                for(int i=1; i<=6; i++) {
                    if(cur.pos + i > 100) break;
                    if(visited[cur.pos + i] <= cur.cnt + 1) continue;

                    toVisit.offer(new Route(cur.pos + i, cur.cnt + 1));
                    visited[cur.pos + i] = cur.cnt + 1;
                }
            } else {
                if(visited[connection[cur.pos]] <= cur.cnt) continue;
                toVisit.offer(new Route(connection[cur.pos], cur.cnt));
                visited[connection[cur.pos]] = cur.cnt;
            }
        }

        return -1;
    }
}
