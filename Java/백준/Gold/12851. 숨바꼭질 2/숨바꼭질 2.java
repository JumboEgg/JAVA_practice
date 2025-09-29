import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    final static int INF = Integer.MAX_VALUE;
    static int min = INF;
    static int cnt;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        getDist(A, B);

        System.out.println(min + "\n" + cnt);
    }

    static void getDist(int A, int B) {
        Queue<int[]> toVisit = new ArrayDeque<>();
        toVisit.offer(new int[] {A, 0}); // 현재 위치, 이동 횟수

        int[] visited = new int[100001];
        Arrays.fill(visited, INF);

        while (!toVisit.isEmpty()) {
            int[] cur = toVisit.poll();

            if (cur[1] > min) continue;
            if (cur[0] == B) {
                if (min > cur[1]) {
                    min = cur[1];
                    cnt = 0;
                }
                cnt++;
            }

            if (visited[cur[0]] < cur[1]) continue;
            visited[cur[0]] = cur[1];

            int s = cur[1] + 1;

            if (cur[0] > 0) {
                int p = cur[0] - 1;
                toVisit.offer(new int[] {p, s});
            }

            if (cur[0] < 100000) {
                int p = cur[0] + 1;
                toVisit.offer(new int[] {p, s});
            }

            if (cur[0] <= 50000) {
                int p = cur[0]*2;
                toVisit.offer(new int[] {p, s});
            }
        }
    }
}
