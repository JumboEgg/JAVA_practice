import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Queue;

public class Main {
	
	static final int INF = Integer.MAX_VALUE;
	
	static int N, M;
	static char map[][];
	static int distance[][];
	static boolean[][] visited;
	static int[] connection;
	
	//상하좌우
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		
		map = new char[N][M];
		visited = new boolean[N][M];
		
		//지도 입력
		for(int i=0; i<N; i++) {
			map[i] = br.readLine().replace(" ",  "").toCharArray();
		}

		//섬 탐색
		int islandNum = 0;
		ArrayList<int[]> islands = new ArrayList<>();
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(visited[i][j]) continue;
				if(map[i][j] == '0') continue;
				islands.add(searchIsland(i, j, islandNum++));
			}
		}

        distance = new int[islandNum+1][islandNum+1];
		connection = new int[islandNum+1];
		
		//거리 정보 초기화
		for(int[] dist : distance) {
			Arrays.fill(dist, INF);
		}
		
		//연결 정보 초기화
		for(int i=1; i<=islandNum; i++) {
			connection[i] = i;
		}
		
		//섬 간의 거리 탐색
		for(int i=0; i<islands.size(); i++) {
			searchDist(islands.get(i), i+1);
		}
		
		//연결 정보 저장
		ArrayList<int[]> con = new ArrayList<>();
		
		for(int i=1; i<=islandNum; i++) {
			for(int j=1; j<=islandNum; j++) {
				if(distance[i][j] == INF) continue;
				con.add(new int[] {i, j, distance[i][j]});
			}
		}
		
		Collections.sort(con, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[2] - o2[2];
			}
		});
		
		//union-find로 연결
		int result = 0;
		int cnt = 0;
		boolean totalConnect = false;
		for(int[] c : con) {
			if(connect(c[0], c[1])) {
				cnt++;
				result += c[2];
			}
			if(cnt == islandNum-1) {
				totalConnect = true;
				break;
			}
		}
		
		System.out.println(totalConnect ? result : -1);
	}
	
	//연결된 섬 정보 갱신
	static int[] searchIsland(int x, int y, int islandNum) {
		int[] island = {INF, INF, -1, -1};
		int[] start = {x, y};
		
		Queue<int[]> toVisit = new ArrayDeque<>();
		
		toVisit.offer(start);
		visited[x][y] = true;
		map[x][y] += islandNum;
		
		while(!toVisit.isEmpty()) {
			int[] cur = toVisit.poll();
			
			//섬 정보 갱신
			checkTopLeft(island, cur);
			
			for(int i=0; i<4; i++) {
				int nx = cur[0] + dx[i];
				int ny = cur[1] + dy[i];
				
				if(nx < 0 || nx >= N) continue;
				if(ny < 0 || ny >= M) continue;
				
				if(visited[nx][ny]) continue;
				if(map[nx][ny] == '0') continue;
				
				visited[nx][ny] = true;
				map[nx][ny] += islandNum;
				toVisit.offer(new int[] {nx, ny});
			}
		}
		
		return island;
	}
	
	//섬이 존재하는 범위 갱신
	static void checkTopLeft(int[] curInfo, int[] check) {
		if(curInfo[0] >= check[0]) {
			curInfo[0] = check[0];
		}
		if(curInfo[1] >= check[1]) {
			curInfo[1] = check[1];
		}
		if(curInfo[2] <= check[0]) {
			curInfo[2] = check[0];
		}
		if(curInfo[3] <= check[1]) {
			curInfo[3] = check[1];
		}
	}
	
	//직접 연결할 수 있는 섬까지의 거리 갱신
	static void searchDist(int[] island, int islandNum) {
		
		for(int i=island[0]; i<=island[2]; i++) {
			for(int j=island[1]; j<=island[3]; j++) {
				getDist(i, j, islandNum);
			}
		}
	}
	
	
	static void getDist(int x, int y, int island) {
		if(map[x][y]-'0' != island) return;
	
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx < 0 || nx >= N) continue;
			if(ny < 0 || ny >= M) continue;
			
			if(map[nx][ny] != '0') continue;
			
			int dist = 0;
			while(map[nx][ny] == '0') {
				dist++;
				nx += dx[i];
				ny += dy[i];
				
				if(nx < 0 || nx >= N) break;
				if(ny < 0 || ny >= M) break;
			}
			
			if(nx < 0 || nx >= N) continue;
			if(ny < 0 || ny >= M) continue;
			
			if(map[nx][ny] == island) continue;
			
			if(dist < 2) continue;
			
			distance[island][map[nx][ny]-'0']
					= Math.min(distance[island][map[nx][ny]-'0'], dist);
		}
	}
	
	static boolean connect(int island1, int island2) {
		int p1 = searchParent(island1);
		int p2 = searchParent(island2);
		
		if(p1 == p2) return false;
		
		connection[p1] = p2;
		
		return true;
	}
	
	static int searchParent(int island) {
		if(connection[island] == island) return island;
		return connection[island] = searchParent(connection[island]);
	}
}
