import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = {0, -1, 1, 0, 0};
    static int[] dy = {0, 0, 0, 1, -1};

    static class Shark {
        int r, c, s, d, z;
        Shark(int r, int c, int s, int d, int z) {
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;
        }
    }

    final static int INF = Integer.MAX_VALUE;
    static int R, C;
    static int totalSize;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Shark> sharks = new ArrayList<>();
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            sharks.add(new Shark(r, c, s, d, z));
        }

        for(int i=0; i<C; i++) {
            catchShark(sharks, i);
            moveSharks(sharks);
        }

        System.out.println(totalSize);
    }

    // pos에서 상어를 잡고 totalSize에 더하기
    static void catchShark(List<Shark> sharks, int pos) {
        int minDist = INF;
        Shark target = null;

        for(Shark shark : sharks) {
            if (shark.c != pos) continue;
            if (shark.r < minDist) {
                minDist = shark.r;
                target = shark;
            }
        }

        if (target != null) {
            totalSize += target.z;
            sharks.remove(target);
        }
    }

    // 상어 이동
    static void moveSharks(List<Shark> sharks) {
        Shark[][] map = new Shark[R][C];

        for(Shark shark : sharks) {
            shark.r += dx[shark.d]*shark.s;
            shark.c += dy[shark.d]*shark.s;
            checkRCS(shark);

            if (map[shark.r][shark.c] != null) {
                Shark prev = map[shark.r][shark.c];
                if (prev.z < shark.z) {
                    map[shark.r][shark.c] = shark;
                }
            } else map[shark.r][shark.c] = shark;
        }

        sharks.clear();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] != null) {
                    sharks.add(map[i][j]);
                }
            }
        }
    }

    // 범위를 초과한 상어의 이동 경로 수정
// 범위를 초과한 상어의 이동 경로 수정
    static void checkRCS(Shark shark) {
        if (shark.d == 1 || shark.d == 2) { // 위/아래 이동
            int cycle = 2 * (R - 1);
            int nr = shark.r % cycle;
            if (nr < 0) nr += cycle;

            if (nr >= R) { // 경계 넘음 → 반대쪽에서 반사
                shark.r = cycle - nr;
                shark.d = (shark.d == 1 ? 2 : 1); // 방향 반전
            } else {
                shark.r = nr;
            }
        } else { // 왼/오른쪽 이동
            int cycle = 2 * (C - 1);
            int nc = shark.c % cycle;
            if (nc < 0) nc += cycle;

            if (nc >= C) {
                shark.c = cycle - nc;
                shark.d = (shark.d == 3 ? 4 : 3); // 방향 반전
            } else {
                shark.c = nc;
            }
        }
    }
}
