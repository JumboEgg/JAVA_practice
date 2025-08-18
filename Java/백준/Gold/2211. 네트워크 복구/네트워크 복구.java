import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static final int INF = Integer.MAX_VALUE / 2;
    static int N, M; // 노드 수, 경로 수
    static List<int[]>[] conn; // 연결 경로 정보
    static int[] dist; // 1번 노드와 연결되는 최단 거리
    static int[] selected; // 연결에 사용될 경로 정보

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 거리를 무한대로 초기화
        dist = new int[N+1];
        selected = new int[N+1];
        for(int i=0; i<=N; i++) {
            dist[i] = INF;
        }

        conn = new ArrayList[N+1];
        for(int i=0; i<=N; i++) {
            conn[i] = new ArrayList<>();
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());

            int c1 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            conn[c1].add(new int[] {c2, d});
            conn[c2].add(new int[] {c1, d});
        }

        selectConnections();

        int cnt = 0;
        StringBuilder sb = new StringBuilder();
        for(int i=2; i<=N; i++) {
            if (selected[i] == 0) continue;
            cnt++;
            sb.append(i + " " + selected[i] + "\n");
        }

        System.out.println(cnt);
        System.out.println(sb);
    }

    // 다익스트라로 1번 노드와 연결되는 최단 거리/경로 찾기
    static void selectConnections() {
        dist[1] = 0;
        // 다익스트라를 위해 거리 정보를 가까운 것부터 정렬
        Queue<int[]> pq = new PriorityQueue<>(
                new Comparator<int[]>() {
                    @Override
                    public int compare(int[] o1, int[] o2) {
                        return o1[1] - o2[1];
                    }
                }
        );
        // 1번 노드에서 1번 노드까지의 거리는 0
        pq.offer(new int[] {1, 0});

        while (!pq.isEmpty()) {
            // 살펴볼 연결 정보
            int[] connection = pq.poll();

            // 1번 노드와 연결되는 노드와 거리
            int conToMain = connection[0];
            int distToMain = connection[1];

            // 이미 더 짧은 경로를 찾은 경우
            if (distToMain > dist[conToMain]) continue;

            // 새로운 연결을 사용하는 게 더 가까울 경우
            // 새로운 연결을 사용했을 때의 최단 거리로 갱신
            for (int[] c : conn[conToMain]) {
                // 새로 연결한 노드와 연결된 다른 노드와 거리
                int conToNode = c[0];
                int distToNode = c[1];

                // 새로운 연결을 이용해 경로 단축
                if (dist[conToNode] > distToNode + distToMain) {
                    dist[conToNode] = distToNode + distToMain;

                    // conToNode로 갈 때 거쳐갈 노드 저장
                    selected[conToNode] = conToMain;

                    // 최단 거리가 갱신된 노드는 다른 노드까지의 거리도 갱신한다.
                    pq.offer(new int[] {conToNode, dist[conToNode]});
                }
            }
        }
    }
}
