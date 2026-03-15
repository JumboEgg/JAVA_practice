import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static int[][] map;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new int[9][9];

        for(int i=0; i<9; i++) {
            char[] input = br.readLine().toCharArray();
            for(int j=0; j<9; j++) {
                map[i][j] = input[j] - '0';
            }
        }

        solve(0);

        StringBuilder sb = new StringBuilder();
        for(int[] t : map) {
            for(int n : t) {
                sb.append(n);
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }

    static boolean solve(int idx) {
        if(idx >= 81) return true;

        int x = idx / 9;
        int y = idx % 9;
        if(map[x][y] != 0) return solve(idx + 1);

        for(int i=1; i<10; i++) {
            if(!checkAvail(x, y, i)) continue;
            map[x][y] = i;
            if(solve(idx + 1)) return true;
            map[x][y] = 0;
        }

        return false;
    }

    static boolean checkAvail(int x, int y, int n) {
        for(int i=0; i<9; i++) {
            if (map[x][i] == n || map[i][y] == n) return false;
        }
        int sx = (x / 3) * 3;
        int sy = (y / 3) * 3;

        for(int i=sx; i<sx+3; i++) {
            for(int j=sy; j<sy+3; j++) {
                if(map[i][j] == n) return false;
            }
        }

        return true;
    }
}
