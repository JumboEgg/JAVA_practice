import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {
	
	public static int N, M, D;
	public static char[][] map;
	public static ArrayList<Integer> killCount = new ArrayList<>();
	public static int[] archor = new int[3];

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        String[] input1 = in.readLine().split(" ");
        N = Integer.parseInt(input1[0]);
        M = Integer.parseInt(input1[1]);
        D = Integer.parseInt(input1[2]);
        
        map = new char[N][M];
        for(int i=0; i<N; i++) {
        	map[i] = in.readLine().replace(" ", "").toCharArray();
        }

		placeArc(0, 0);
        
        int result = Collections.max(killCount);
        
        sb.append(result);
        
        System.out.println(sb);
    }

    public static void placeArc(int cnt, int start) {
		if(cnt == 3) {
			getKillCount(archor);
			return;
		}
		for(int i=start; i<M; i++) {
			archor[cnt] = i;
			placeArc(cnt + 1, i + 1);
		}
	}
	
	public static void getKillCount(int[] archor) {	
		int count = 0;
		int[][] killed = new int[N][M];
		
		for(int i=N; i>0; i--) {
			for(int j=0; j<3; j++) {
				for(int k=1; k<=D; k++) {
					int cnt = fight(archor[j], killed, i, k);
					if(cnt < 0) continue;
					count += cnt;
					break;
				}
			}
		}
		killCount.add(count);
	}

	public static int fight(int pos, int[][] killed, int moved, int range) {
		for(int atkX=pos-range+1; atkX<pos+range; atkX++) {
			int atkY = moved - range + Math.abs(atkX-pos);
				
			//범위 확인
			if(!idxCheck(atkX, atkY)) continue;
			
			//적이 없을 경우
			if(map[atkY][atkX] == '0') continue;
			
			if(killed[atkY][atkX] == 0) {
				killed[atkY][atkX] = moved;
				return 1;
			}
			else if(killed[atkY][atkX] == moved) {
				return 0;
			}
		}
		return -1;
	}
	
	public static boolean idxCheck(int x, int y) {
		if(x < 0 || x >= M) return false;
		if(y < 0 || y >= N) return false;
		return true;
	}
}