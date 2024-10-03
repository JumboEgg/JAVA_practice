import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int[] dx = {0, 0, -1, 1};
		int[] dy = {1, -1, 0, 0};
		
		String[] input = br.readLine().split(" ");
		int R = Integer.parseInt(input[0]);
		int C = Integer.parseInt(input[1]);
		
		char[][] map = new char[R][C];
		boolean[][] visited = new boolean[R][C];
		
		Queue<int[]> water = new ArrayDeque<>();
		Queue<int[]> hedgehog = new ArrayDeque<>();

		for(int i=0; i<R; i++) {
			map[i] = br.readLine().toCharArray();
			for(int j=0; j<C; j++) {
				if(map[i][j] == 'S') {
					visited[i][j] = true;
					hedgehog.offer(new int[] {i, j});
				}
				else if(map[i][j] == '*') water.offer(new int[] {i, j});
			}
		}
		
		boolean escaped = false;
		int step = -1;
		
		while(!hedgehog.isEmpty()) {
			
			int fSize = water.size();
			while(fSize-- > 0) {
				int[] cur = water.poll();
				for(int i=0; i<4; i++) {
					int nx = cur[0] + dx[i];
					int ny = cur[1] + dy[i];
					
					if(nx < 0 || nx >= R) continue;
					if(ny < 0 || ny >= C) continue;
					
					if(map[nx][ny] == '.') {
						map[nx][ny] = '*';
						water.offer(new int[] {nx, ny});
					}
				}
			}
			
			step++;
			int hSize = hedgehog.size();
			while(hSize-- > 0) {
				int[] cur = hedgehog.poll();
				
				if(map[cur[0]][cur[1]] == 'D') {
					escaped = true;
					break;
				}
				
				for(int i=0; i<4; i++) {
					int nx = cur[0] + dx[i];
					int ny = cur[1] + dy[i];
					
					if(nx < 0 || nx >= R) continue;
					if(ny < 0 || ny >= C) continue;
					if(map[nx][ny] == 'X') continue;
					if(map[nx][ny] == '*') continue;
					if(visited[nx][ny]) continue;

					visited[nx][ny] = true;
					hedgehog.offer(new int[] {nx, ny});
				}
			}
			if(escaped) break;
		}
		
		System.out.println(escaped ? step : "KAKTUS");
	}
}
