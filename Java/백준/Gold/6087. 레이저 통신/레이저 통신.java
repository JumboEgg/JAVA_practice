import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Route implements Comparable<Route> {
        int x, y, dir;
        int cnt;

        Route(int x, int y, int dir, int cnt) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Route o) {
            return this.cnt - o.cnt;
        }
    }

    static final int INF = Integer.MAX_VALUE;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    static int W, H;
    static char[][] map;
    static int[][][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new char[H][W];
        int[] C = new int[2];

        for(int i=0; i<H; i++) {
            map[i] = br.readLine().toCharArray();
            for(int j=0; j<W; j++) {
                if (map[i][j] == 'C') C = new int[] {i, j};
            }
        }

        visited = new int[H][W][4];
        for(int i=0; i<H; i++) {
            for(int j=0; j<W; j++) {
                for(int k=0; k<4; k++) {
                    visited[i][j][k] = INF;
                }
            }
        }

        int answer = searchMinMirror(C);
        System.out.println(answer);
    }

    static int searchMinMirror(int[] start) {
        Queue<Route> toVisit = new PriorityQueue<>();
        for(int i=0; i<4; i++) {
            int x = start[0];
            int y = start[1];
            visited[x][y][i] = 0;

            int nx = x + dx[i];
            int ny = y + dy[i];
            if (outOfBounds(nx, ny)) continue;
            if (map[nx][ny] == '*') continue;

            visited[nx][ny][i] = 0;
            toVisit.offer(new Route(nx, ny, i, 0));
        }

        while (!toVisit.isEmpty()) {
            Route cur = toVisit.poll();
            if (map[cur.x][cur.y] == 'C') {
                return cur.cnt;
            }

            for(int i=-1; i<2; i++) {
                int dir = (cur.dir + i + 4) % 4;
                int nx = cur.x + dx[dir];
                int ny = cur.y + dy[dir];

                if(outOfBounds(nx, ny)) continue;
                if(map[nx][ny] == '*') continue;
                if(visited[nx][ny][dir] != INF) continue;

                int nCnt = cur.dir == dir ? cur.cnt : cur.cnt + 1;
                visited[nx][ny][dir] = nCnt;
                toVisit.offer(new Route(nx, ny, dir, nCnt));
            }
        }

        return -1;
    }

    static boolean outOfBounds(int x, int y) {
        return x < 0 || x >= H || y < 0 || y >= W;
    }
}
