import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Ball implements Comparable<Ball>{
        int idx;
        int color;
        int size;

        Ball(int idx, int color, int size) {
            this.idx = idx;
            this.color = color;
            this.size = size;
        }

        @Override
        public int compareTo(Ball o) {
            return this.size - o.size;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        Ball[] balls = new Ball[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int color = Integer.parseInt(st.nextToken());
            int size = Integer.parseInt(st.nextToken());
            balls[i] = new Ball(i, color, size);
        }

        Arrays.sort(balls);

        int[] result = new int[N];
        int[] colorSum = new int[N + 1]; // 색 번호 최대 N
        int total = 0;

        // 포인터
        int p = 0;

        for (int i = 0; i < N; i++) {
            // 현재 공보다 작은 공들까지 총합 포함시키기
            while (balls[p].size < balls[i].size) {
                total += balls[p].size;
                colorSum[balls[p].color] += balls[p].size;
                p++;
            }

            result[balls[i].idx] =
                    total - colorSum[balls[i].color];
        }

        for (int i = 0; i < N; i++) {
            sb.append(result[i]).append("\n");
        }

        System.out.print(sb);
    }
}
