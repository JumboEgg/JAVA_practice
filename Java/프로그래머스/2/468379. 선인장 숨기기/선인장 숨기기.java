import java.util.*;

class Solution {

    public int[] solution(int m, int n, int h, int w, int[][] drops) {

        int INF = drops.length + 1;

        // 각 칸이 언제 젖는지
        int[][] time = new int[m][n];

        for (int i = 0; i < m; i++) {
            Arrays.fill(time[i], INF);
        }

        for (int i = 0; i < drops.length; i++) {
            int r = drops[i][0];
            int c = drops[i][1];

            time[r][c] = i + 1;
        }

        int width = n - w + 1;

        // 각 행에서 길이 w 최소값
        int[][] rowMin = new int[m][width];

        for (int r = 0; r < m; r++) {

            Deque<Integer> dq = new ArrayDeque<>();

            for (int c = 0; c < n; c++) {

                while (!dq.isEmpty()
                        && time[r][dq.peekLast()] >= time[r][c]) {
                    dq.pollLast();
                }

                dq.addLast(c);

                // 범위 밖 제거
                while (dq.peekFirst() <= c - w) {
                    dq.pollFirst();
                }

                if (c >= w - 1) {
                    rowMin[r][c - w + 1] = time[r][dq.peekFirst()];
                }
            }
        }

        int height = m - h + 1;

        // h*w 내부 최소값
        int[][] rectMin = new int[height][width];

        for (int c = 0; c < width; c++) {

            Deque<Integer> dq = new ArrayDeque<>();

            for (int r = 0; r < m; r++) {

                while (!dq.isEmpty()
                        && rowMin[dq.peekLast()][c] >= rowMin[r][c]) {
                    dq.pollLast();
                }

                dq.addLast(r);

                while (dq.peekFirst() <= r - h) {
                    dq.pollFirst();
                }

                if (r >= h - 1) {
                    rectMin[r - h + 1][c]
                            = rowMin[dq.peekFirst()][c];
                }
            }
        }

        int best = -1;
        int[] ans = {0, 0};

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {

                // 행/열 작은 순 자동 보장
                if (rectMin[i][j] > best) {
                    best = rectMin[i][j];
                    ans = new int[]{i, j};
                }
            }
        }

        return ans;
    }
}