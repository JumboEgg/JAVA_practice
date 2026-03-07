import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class Main {
    static int N, min;
    static String[] students;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            students = br.readLine().split(" ");

            if(N > 32) {
                sb.append("0\n");
                continue;
            }

            boolean[] selected = new boolean[N];
            min = Integer.MAX_VALUE;

            findMinDist(selected, 0, 0);
            sb.append(min).append("\n");
        }

        System.out.print(sb);
    }

    static void findMinDist(boolean[] selected, int idx, int cnt) {
        if(cnt == 3) {
            int dist = getDist(selected);
            min = Math.min(min, dist);
            return;
        }

        for(int i=idx; i<N; i++) {
            selected[i] = true;
            findMinDist(selected, i+1, cnt+1);
            selected[i] = false;
        }
    }

    static int getDist(boolean[] selected) {
        int idx = 0;
        String[] s = new String[3];
        for(int i=0; i<N; i++) {
            if(!selected[i]) continue;
            s[idx++] = students[i];
        }

        int dist = 0;
        for(int i=0; i<3; i++) {
            for(int j=0; j<4; j++) {
                dist += s[i].charAt(j) == s[(i+1)%3].charAt(j) ? 0 : 1;
            }
        }

        return dist;
    }
}
