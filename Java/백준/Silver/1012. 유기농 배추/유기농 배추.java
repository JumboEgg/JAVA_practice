import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int TC = Integer.parseInt(br.readLine());
        for(int tc=0; tc<TC; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            int[][] map = new int[N][M];
            List<int[]> worms = new ArrayList<>();
            for(int i=0; i<K; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                map[x][y] = 1;
                worms.add(new int[] {x, y});
            }

            int count = 0;
            for(int[] worm : worms) {
                int x = worm[0];
                int y = worm[1];
                if(map[x][y] != 1) continue;
                bfs(x, y, map);
                count++;
            }

            sb.append(count).append("\n");
        }

        System.out.println(sb);
    }

    static void bfs(int x, int y, int[][] map) {
        Queue<int[]> toVisit = new ArrayDeque<>();
        toVisit.offer(new int[] {x, y});

        while (!toVisit.isEmpty()) {
            int[] cur = toVisit.poll();
            for(int i=0; i<4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                if(map[nx][ny] != 1) continue;

                toVisit.offer(new int[] {nx, ny});
                map[nx][ny] = 2;
            }
        }
    }
}
