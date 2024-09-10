import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[][] map = new int[N][N];
		for(int i=0; i<N; i++) {
			String[] input = br.readLine().split(" ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(input[j]);
			}
		}
		
		int[][][] pipe = new int[N][N][3];
		
		for(int i=1; i<N; i++) {
			if(map[0][i] == 1) break;
			pipe[0][i][0] = 1;
		}
		
		for(int i=1; i<N; i++) {
			for(int j=1; j<N; j++) {
				if(map[i][j] == 1) continue;
				pipe[i][j][1] = pipe[i-1][j][1] + pipe[i-1][j][2];
				pipe[i][j][0] = pipe[i][j-1][0] + pipe[i][j-1][2];
				if(map[i-1][j] == 1 || map[i][j-1] == 1) continue;
				pipe[i][j][2] = pipe[i-1][j-1][0] + pipe[i-1][j-1][1] + pipe[i-1][j-1][2];
			}
		}
		
		int total = 0;
		for(int i=0; i<3; i++) {
			total += pipe[N-1][N-1][i];
		}
		
		System.out.println(total);
	}
}
