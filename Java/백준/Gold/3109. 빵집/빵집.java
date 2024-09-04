import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class Main {
	
	static int R, C;
	static char[][] map;
	static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");
		R = Integer.parseInt(input[0]);
		C = Integer.parseInt(input[1]);
		
		visited = new boolean[R][C];
		map = new char[R][C];
		for(int i=0; i<R; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		int result = 0;
		for(int i=0; i<R; i++) {
			if(searchPath(i, 0)) result++;
		}
		
		System.out.println(result);
	}
	
	static boolean searchPath(int row, int col) {
		
		visited[row][col] = true;
		if(col == C-1) return true;;
		
		if(row-1 > -1 && map[row-1][col+1]=='.'
				&& !visited[row-1][col+1]) {
			if(searchPath(row-1, col+1)) return true;
		}
		
		if(map[row][col+1]=='.'
				&& !visited[row][col+1]) {
			if(searchPath(row, col+1)) return true;
		}
		
		if(row+1 < R && map[row+1][col+1]=='.'
				&& !visited[row+1][col+1]) {
			if(searchPath(row+1, col+1)) return true;
		}
		
		return false;
	}
}
