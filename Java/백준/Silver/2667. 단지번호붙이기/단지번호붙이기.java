import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    static int N;
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        List<int[]> pos = new ArrayList<>();

        map = new int[N][N];
        for(int i=0; i<N; i++) {
            char[] input = br.readLine().toCharArray();
            for(int j=0; j<N; j++) {
                map[i][j] = input[j] - '0';
                if (map[i][j] == 1) pos.add(new int[]{i, j});
            }
        }

        visited = new boolean[N][N];
        List<Integer> cnt = new ArrayList<>();
        for(int[] p : pos) {
            if(visited[p[0]][p[1]]) continue;
            visited[p[0]][p[1]] = true;
            cnt.add(bfs(p));
        }

        Collections.sort(cnt);

        StringBuilder sb = new StringBuilder();
        sb.append(cnt.size()).append("\n");
        for(int i : cnt) sb.append(i).append("\n");

        System.out.print(sb);
    }

    static int bfs(int[] start) {
        Queue<int[]> toVisit = new ArrayDeque<>();
        toVisit.offer(start);

        int count = 0;
        while (!toVisit.isEmpty()) {
            count++;
            int[] cur = toVisit.poll();

            for(int i=0; i<4; i++) {
                int x = cur[0] + dx[i];
                int y = cur[1] + dy[i];

                if(isOutOfBounds(x, y)) continue;
                if(visited[x][y]) continue;
                if(map[x][y] == 0) continue;
                toVisit.offer(new int[] {x, y});
                visited[x][y] = true;
            }
        }

        return count;
    }

    static boolean isOutOfBounds(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= N;
    }
}
