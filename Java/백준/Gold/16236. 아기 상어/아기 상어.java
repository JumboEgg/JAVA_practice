import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};

    static int N;
    static int[][] map;
    static int[] fish;
    static int sharkSize = 2;
    static int time;
    static int growth;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        fish = new int[7];

        int[] shark = new int[2];

        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) continue;
                if (map[i][j] == 9) {
                    shark[0] = i;
                    shark[1] = j;
                    map[i][j] = 0;
                }
                else fish[map[i][j]]++;
            }
        }

        while (checkFish()) {
            shark = eatNearestFish(shark);
            if (shark == null) break;
        }

        System.out.println(time);
    }

    static boolean checkFish() {
        int cnt = 0;
        for(int i=1; i<Math.min(sharkSize, 7); i++) {
            cnt += fish[i];
        }

        return cnt > 0;
    }

    static int[] eatNearestFish(int[] start) {
        Queue<int[]> toVisit = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[2] != o2[2]) return o1[2] - o2[2];
                if (o1[0] != o2[0]) return o1[0] - o2[0];
                return o1[1] - o2[1];
            }
        });
        boolean[][] visited = new boolean[N][N];

        toVisit.offer(new int[]{start[0], start[1], 0});
        visited[start[0]][start[1]] = true;

        while (!toVisit.isEmpty()) {
            int[] cur = toVisit.poll();

            int value = map[cur[0]][cur[1]];
            if (value > 0 && value < sharkSize) {
                time += cur[2];
                fish[value]--;
                map[cur[0]][cur[1]] = 0;
                if (++growth == sharkSize) {
                    sharkSize++;
                    growth = 0;
                }
                return cur;
            }

            for(int i=0; i<4; i++) {
                int x = cur[0] + dx[i];
                int y = cur[1] + dy[i];
                int dist = cur[2] + 1;

                if (outOfBounds(x, y)) continue;
                if (map[x][y] > sharkSize) continue;
                if (visited[x][y]) continue;

                visited[x][y] = true;
                toVisit.offer(new int[] {x, y, dist});
            }
        }
        return null;
    }

    static boolean outOfBounds(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= N;
    }
}
