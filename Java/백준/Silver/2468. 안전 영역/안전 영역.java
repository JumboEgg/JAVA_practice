import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Queue;

public class Main {
	
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static int max = 1;
	
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		HashSet<Integer> heights = new HashSet<>();
		
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			String[] input = br.readLine().split(" ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(input[j]);
				heights.add(map[i][j]);
			}
		}
		
		visited = new boolean[N][N];
		
		int count = 0;
		for(int rain : heights) {
			
			count = 0;
			
			for(int i=0; i<N; i++) {
				Arrays.fill(visited[i], false);
			}
			
			for(int i=0; i<N*N; i++) {
				if(visited[i/N][i%N]) continue;
				if(map[i/N][i%N] < rain) continue;
				
				count++;
				searchLand(i/N, i%N, rain);
			}
			
			max = Math.max(max, count);
		}
		
		System.out.println(max);
	}
	
	//탐색 지점의 x, y, 비가 고인 높이(rain 이상이면 잠기지 않은 것으로 한다.)
	static void searchLand(int x, int y, int rain) {
		Queue<int[]> toVisit = new ArrayDeque<>();
		visited[x][y] = true;
		
		toVisit.offer(new int[] {x, y});
		
		while(!toVisit.isEmpty()) {
			
			int[] cur = toVisit.poll();
			
			for(int i=0; i<4; i++) {
				int nx = cur[0] + dx[i];
				int ny = cur[1] + dy[i];
				
				if(nx < 0 || nx >= N) continue;
				if(ny < 0 || ny >= N) continue;
				
				if(visited[nx][ny]) continue;
				if(map[nx][ny] < rain) continue;
				
				visited[nx][ny] = true;
				toVisit.offer(new int[] {nx, ny});
			}
		}
	}
}
