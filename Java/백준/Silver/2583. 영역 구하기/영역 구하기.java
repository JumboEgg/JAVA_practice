import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Queue;

public class Main {
	
	static int N, M;
	static boolean[][] visited;
	
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");
		M = Integer.parseInt(input[0]);
		N = Integer.parseInt(input[1]);
		int K = Integer.parseInt(input[2]);
		
		visited = new boolean[M][N];
		
		int[][] areas = new int[K][4];
		for(int i=0; i<K; i++) {
			input = br.readLine().split(" ");
			for(int j=0; j<4; j++) {
				areas[i][j] = Integer.parseInt(input[j]);
			}
			
			checkArea(areas[i]);
		}
		
		int count = 0;
		ArrayList<Integer> sizes = new ArrayList<>();
		for(int i=0; i<N*M; i++) {
			if(visited[i/N][i%N]) continue;
			count++;
			sizes.add(checkArea(i/N, i%N));
		}
		
		Collections.sort(sizes);
		
		System.out.println(count);
		for(int size : sizes) {
			System.out.print(size + " ");
		}
	}
	
	static void checkArea(int[] area) {
		for(int i=area[1]; i<area[3]; i++) {
			for(int j=area[0]; j<area[2]; j++) {
				visited[i][j] = true;
			}
		}
	}
	
	static int checkArea(int x, int y) {
		Queue<int[]> toVisit = new ArrayDeque<>();
		visited[x][y] = true;
		
		toVisit.offer(new int[] {x, y});
		
		int size = 0;
		
		while(!toVisit.isEmpty()) {
			
			size++;			
			int[] cur = toVisit.poll();
			
			for(int i=0; i<4; i++) {
				int nx = cur[0] + dx[i];
				int ny = cur[1] + dy[i];
				
				if(nx < 0 || nx >= M) continue;
				if(ny < 0 || ny >= N) continue;
				
				if(visited[nx][ny]) continue;
				
				visited[nx][ny] = true;
				toVisit.offer(new int[] {nx, ny});
			}
		}
		
		return size;
	}
}
