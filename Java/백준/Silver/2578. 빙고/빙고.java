import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	
	public static int[][] board;
	public static int bingo = 0;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		board = new int[5][5];
		
		for(int i=0; i<5; i++) {
			String[] input = br.readLine().split(" ");
			for(int j=0; j<5; j++) {
				board[i][j] = Integer.parseInt(input[j]);
			}
		}

		int num = 0;
		int cnt = 0;
		
		for(int i=0; i<5; i++) {
			String[] input = br.readLine().split(" ");
			
			for(int j=0; j<5; j++) {
				
				num = Integer.parseInt(input[j]);
				
				int[] index = findIndex(num);
				
				board[index[0]][index[1]] = 0;
				isBingo(index[0], index[1]);
				
				if(bingo >= 3) {
					cnt = i*5 + j + 1;
					break;
				}
			}
			if(cnt != 0) break;
		}
		
		sb.append(cnt).append("\n");
		
		System.out.println(sb);
	}

	private static void isBingo(int X, int Y) {
		
		int x = 0;
		int y = 0;
		
		//가로 세로 빙고 확인
		for(int i=0; i<5; i++) {
			x += board[X][i];
			y += board[i][Y];
		}
		
		if(x == 0) bingo++;
		if(y == 0) bingo++;
		
		int z = 0;
		
		if(X == Y) {
			for(int i=0; i<5; i++) {
				z += board[i][i];
			}
			if(z == 0) bingo++;
		}
        
        z = 0;
			
		if (X+Y == 4) {
			for(int i=0; i<5; i++) {
				z += board[i][4-i];
			}
			if(z == 0) bingo++;
		}
		
		return;
	}

	private static int[] findIndex(int num) {
		for(int i=0; i<5; i++) {
			for(int j=0; j<5; j++) {
				if(board[i][j] == num) {
					return new int[] {i, j};
				}
			}
		}
		
		return null;
	}

}
