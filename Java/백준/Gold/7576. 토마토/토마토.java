import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;


public class Main {
	public static void main(String[] args) throws Exception {
		
		int[] dx = {0, 1, 0, -1};
		int[] dy = {1, 0, -1, 0};		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");
		
		int N = Integer.parseInt(input[0]);
		int M = Integer.parseInt(input[1]);
		
		int[][] tomatoes = new int[M][N];
		boolean[][] isRipe = new boolean[M][N];
		
		Queue<int[]> toRipe = new ArrayDeque<>();
		
		for(int i=0; i<M; i++) {
			input = br.readLine().split(" ");
			for(int j=0; j<N; j++) {
				tomatoes[i][j] = Integer.parseInt(input[j]);
				if(tomatoes[i][j] == 1) {
					isRipe[i][j] = true;
					toRipe.offer(new int[] {i, j});
				} else if(tomatoes[i][j] == -1) {
					isRipe[i][j] = true;
				}
			}
		}
		
		int days = -1;
		
		while(!toRipe.isEmpty()) {
			
			days++;
			int size = toRipe.size();
			
			while(size-- > 0) {
				
				int[] cur = toRipe.poll();
				for(int i=0; i<4; i++) {
					
					int newX = cur[0] + dx[i];
					int newY = cur[1] + dy[i];
					
					if(newX < 0 || newX >= M) continue;
					if(newY < 0 || newY >= N) continue;
					
					if(tomatoes[newX][newY] == -1) continue;
					if(isRipe[newX][newY]) continue;
					
					isRipe[newX][newY] = true;
					toRipe.offer(new int[] {newX, newY});
				}
			}
		}
		
		for(int i=0; i<N*M; i++) {
			if(!isRipe[i/N][i%N]) days = -1;
		}
		
		System.out.println(days);
	}
}
