import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] A;
    static boolean[][] cloud;
    static int[] dx = {0, 0, -1, -1, -1, 0, 1, 1, 1}; // 1~8 방향
    static int[] dy = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    static int[] diagX = {-1, -1, 1, 1};
    static int[] diagY = {-1, 1, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        List<int[]> clouds = new ArrayList<>();
        clouds.add(new int[]{N - 1, 0});
        clouds.add(new int[]{N - 1, 1});
        clouds.add(new int[]{N - 2, 0});
        clouds.add(new int[]{N - 2, 1});

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            boolean[][] visited = new boolean[N][N];
            List<int[]> newClouds = new ArrayList<>();

            // (1) 구름 이동 + (2) 비 내리기
            for (int[] c : clouds) {
                int nx = (c[0] + dx[d] * s % N + N) % N;
                int ny = (c[1] + dy[d] * s % N + N) % N;
                A[nx][ny]++;
                visited[nx][ny] = true;
                newClouds.add(new int[]{nx, ny});
            }

            // (3) 구름 사라짐 (visited[][]로 추적)

            // (4) 물 복사 버그
            for (int[] c : newClouds) {
                int cnt = 0;
                for (int k = 0; k < 4; k++) {
                    int nx = c[0] + diagX[k];
                    int ny = c[1] + diagY[k];
                    if (nx >= 0 && nx < N && ny >= 0 && ny < N && A[nx][ny] > 0) {
                        cnt++;
                    }
                }
                A[c[0]][c[1]] += cnt;
            }

            // (5) 새로운 구름 생성
            clouds.clear();
            for (int x = 0; x < N; x++) {
                for (int y = 0; y < N; y++) {
                    if (!visited[x][y] && A[x][y] >= 2) {
                        A[x][y] -= 2;
                        clouds.add(new int[]{x, y});
                    }
                }
            }
        }

        // 결과 계산
        int sum = 0;
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                sum += A[i][j];

        System.out.println(sum);
    }
}
