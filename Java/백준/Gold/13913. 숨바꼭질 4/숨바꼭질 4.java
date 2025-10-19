import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int MAX = 100000;
        int[] visited = new int[MAX + 1];
        int[] prev = new int[MAX + 1];
        Arrays.fill(visited, -1);

        Queue<Integer> q = new ArrayDeque<>();
        q.offer(N);
        visited[N] = 0;
        prev[N] = -1;

        while (!q.isEmpty()) {
            int cur = q.poll();
            if (cur == K) break;

            int[] nexts = {cur - 1, cur + 1, cur * 2};
            for (int next : nexts) {
                if (next < 0 || next > MAX) continue;
                if (visited[next] != -1) continue;

                visited[next] = visited[cur] + 1;
                prev[next] = cur;
                q.offer(next);
            }
        }

        List<Integer> path = new ArrayList<>();
        for (int i = K; i != -1; i = prev[i]) {
            path.add(i);
        }
        Collections.reverse(path);

        StringBuilder sb = new StringBuilder();
        sb.append(visited[K]).append('\n');
        for (int p : path) sb.append(p).append(' ');
        System.out.println(sb);
    }
}
