import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	
	static int w, h;
	static int[][] map;
	static Queue<int[]> toVisit;
	static boolean[][] visited;
	static int count;
	static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
	static int[] dy = {1, 1, 0, -1, -1, -1, 0, 1};

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			String[] input = br.readLine().split(" ");
			w = Integer.parseInt(input[0]);
			h = Integer.parseInt(input[1]);
			
			if(w == 0 && h == 0) break;
			
			map = new int[h][w];
			visited = new boolean[h][w];
			
			int cnt = 0; //섬 유무 확인
			
			for(int i=0; i<h; i++) {
				input = br.readLine().split(" ");
				for(int j=0; j<w; j++) {
					map[i][j] = Integer.parseInt(input[j]);
					if(map[i][j] == 0) {
						visited[i][j] = true;
					}
					else cnt++;
				}
			}
			
			//섬이 없을 경우
			if(cnt == 0) {
				sb.append("0\n");
				continue;
			}
			
			//섬이 있을 경우
			count = 0;
			toVisit = new ArrayDeque<>();
			
			for(int i=0; i<h; i++) {
				for(int j=0; j<w; j++) {
					if(visited[i][j]) continue;
					count++;
					isConnected(new int[] {i, j});
				}
			}
			
			sb.append(count + "\n");
		}
		
		System.out.println(sb);
	}

	//섬이 연결되어 있으면 count 1 감소
	private static void isConnected(int[] cur) {
		
		if(visited[cur[0]][cur[1]]) {
			return;
		}

		visited[cur[0]][cur[1]] = true;
		search(cur);

		while(!toVisit.isEmpty()) {
			isConnected(toVisit.poll());
		}
	}

	private static void search(int[] cur) {

		for(int i=0; i<8; i++) {
			int curX = cur[0] + dx[i];
			int curY = cur[1] + dy[i];
	
			if(curX < 0 || curX >= h) continue;
			if(curY < 0 || curY >= w) continue;
			
			if(visited[curX][curY]) continue;
			
			toVisit.offer(new int[] {curX, curY});
		}
	}
}
