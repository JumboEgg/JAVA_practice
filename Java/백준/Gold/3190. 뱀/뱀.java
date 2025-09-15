import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int[][] map;
    static int L;
    static HashMap<Integer, String> dirs;
    static int curDir;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        map = new int[N][N];
        map[0][0] = 2;

        for(int i=0; i<K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken())-1;
            int y = Integer.parseInt(st.nextToken())-1;

            map[x][y] = 1;
        }

        L = Integer.parseInt(br.readLine());

        dirs = new HashMap<>();
        for(int i=0; i<L; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            String dir = st.nextToken();

            dirs.put(time, dir);
        }

        List<int[]> snake = new ArrayList<>();
        snake.add(new int[]{0, 0});
        curDir = 1; // 12시 방향부터 시계 방향으로 0, 1, 2, 3
        int time = 0;

        while (true) {
            time++;
            int[] next = getNextHeadPos(snake.get(snake.size() - 1), time);

            if(outOfBounds(next)) break;

            if(map[next[0]][next[1]] == 2) break;
            else if (map[next[0]][next[1]] == 0) {
                int[] tail = snake.get(0);
                map[tail[0]][tail[1]] = 0;
                snake.remove(0);
            }

            map[next[0]][next[1]] = 2;
            snake.add(next);

            if(dirs.containsKey(time)) {
                int dir = "D".equals(dirs.get(time)) ? 1 : -1;
                curDir = (4+curDir+dir)%4;
            }
        }

        System.out.println(time);
    }

    static int[] getNextHeadPos(int[] head, int time) {
        int nextX = head[0] + dx[curDir];
        int nextY = head[1] + dy[curDir];

        return new int[] {nextX, nextY};
    }

    static boolean outOfBounds(int[] pos) {
        return pos[0] < 0 || pos[0] >= N || pos[1] < 0 || pos[1] >= N;
    }
}
