import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;
    static char[][] map;
    static HashMap<String, Integer> wordCnt = new HashMap<>();

    static int[] dx = {0, 0, 1, 1, 1, -1, -1, -1};
    static int[] dy = {1, -1, 0, 1, -1, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        for(int i=0; i<N; i++) map[i] = br.readLine().toCharArray();

        List<String> favs = new ArrayList<>();
        for(int i=0; i<K; i++) favs.add(br.readLine());

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                generateString(i, j, 1, "" + map[i][j]);
            }
        }

        StringBuilder sb = new StringBuilder();
        for(String fav : favs) sb.append(wordCnt.getOrDefault(fav, 0)).append("\n");

        System.out.print(sb);
    }

    static void generateString(int x, int y, int cnt, String cur) {
        int curCnt = wordCnt.getOrDefault(cur, 0);
        wordCnt.put(cur, curCnt + 1);

        if(cnt >= 5) return;

        for(int i=0; i<8; i++) {
            int nx = (x + dx[i] + N) % N;
            int ny = (y + dy[i] + M) % M;

            generateString(nx, ny, cnt + 1, cur + map[nx][ny]);
        }
    }
}
