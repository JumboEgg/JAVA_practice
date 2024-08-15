import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	
	public static int[][] move;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		move = new int[6][2];
		int maxX = 0; //밭의 최대 가로 길이
		int maxY = 0; //밭의 최대 세로 길이
		
		for(int i=0; i<6; i++) {
			String[] input = br.readLine().split(" ");
			
			move[i][0] = Integer.parseInt(input[0]);
			move[i][1] = Integer.parseInt(input[1]);
			
			if(move[i][0] == 1 || move[i][0] == 2) {
				maxX = Math.max(maxX, move[i][1]);
			}
			else {
				maxY = Math.max(maxY, move[i][1]);
			}
		}
		
		int concaveArea = check();
		
		sb.append((maxX*maxY - concaveArea)*N);
		
		System.out.println(sb);
	}

	private static int check() {
		
		//오른쪽으로 꺾은 위치
		int dirChange = 5;
		for(int i=0; i<5; i++) {
			if(!turnLeft(move[i][0], move[i+1][0])) {
				dirChange = i;
			}
		}
		
		return move[dirChange][1] * move[(dirChange+1)%6][1];
	}

	private static boolean turnLeft(int cur, int next) {

		if(cur == 1 && next == 4) return true;
		if(cur == 4 && next == 2) return true;
		if(cur == 2 && next == 3) return true;
		if(cur == 3 && next == 1) return true;
		
		return false;
	}
}
