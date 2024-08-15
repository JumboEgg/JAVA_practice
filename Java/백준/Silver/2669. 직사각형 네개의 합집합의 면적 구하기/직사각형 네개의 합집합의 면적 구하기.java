import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static int[][] area = new int[101][101];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<4; i++) {
			String[] input = br.readLine().split(" ");
			
			int x1 = Integer.parseInt(input[0]);
			int y1 = Integer.parseInt(input[1]);
			int x2 = Integer.parseInt(input[2]);
			int y2 = Integer.parseInt(input[3]);
			
			areaCheck(x1, y1, x2, y2);
		}
		
		int result = 0;
		
		for(int i=0; i<101; i++) {
			for(int j=0; j<101; j++) {
				if(area[i][j] > 0) {
					result++;
				}
			}
		}
		
		sb.append(result);
		
		System.out.println(sb);
	}

	private static void areaCheck(int x1, int y1, int x2, int y2) {

		for(int i=x2; i>x1; i--) {
			for(int j=y2; j>y1; j--) {
				area[i][j]++;
			}
		}
	}
	
}
