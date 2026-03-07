import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static final int INF = Integer.MAX_VALUE / 2;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int TC = Integer.parseInt(br.readLine());
        while (TC-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());

            // 간선 리스트 준비
            List<int[]> edges = new ArrayList<>();

            // 일반 간선 입력
            while (M-- > 0) {
                st = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken());

                edges.add(new int[] { S, E, T });
                edges.add(new int[] { E, S, T }); // 양방향 간선
            }

            // 웜홀 간선 입력 (음수 가중치)
            while (W-- > 0) {
                st = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken());

                edges.add(new int[] { S, E, -T });
            }

            // 음의 사이클이 존재하는지 확인
            boolean flag = false;
            for (int i = 1; i <= N; i++) {
                if (findLoop(N, i, edges)) {
                    flag = true;
                    break;
                }
            }

            sb.append(flag ? "YES" : "NO").append("\n");
        }

        System.out.print(sb);
    }

    // 벨만-포드 알고리즘을 이용한 음의 사이클 탐지
    static boolean findLoop(int N, int start, List<int[]> edges) {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        // N-1번 반복을 통해 최단 거리 갱신
        for (int i = 1; i < N; i++) {
            boolean updated = false;
            for (int[] edge : edges) {
                int u = edge[0], v = edge[1], weight = edge[2];
                if (dist[u] != INF && dist[v] > dist[u] + weight) {
                    dist[v] = dist[u] + weight;
                    updated = true;
                }
            }
            // 더 이상 갱신되지 않으면 종료
            if (!updated) break;
        }

        // N번째 반복에서 음의 사이클 발견 여부 확인
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], weight = edge[2];
            if (dist[u] != INF && dist[v] > dist[u] + weight) {
                return true; // 음의 사이클 발견
            }
        }

        return false;
    }
}