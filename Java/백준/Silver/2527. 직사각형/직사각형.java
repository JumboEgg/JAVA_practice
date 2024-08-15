import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	
	public static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int i=0; i<4; i++) {
			String[] input = br.readLine().split(" ");
			
			int x1 = Integer.parseInt(input[0]);
			int y1 = Integer.parseInt(input[1]);
			int p1 = Integer.parseInt(input[2]);
			int q1 = Integer.parseInt(input[3]);
			
			int x2 = Integer.parseInt(input[4]);
			int y2 = Integer.parseInt(input[5]);
			int p2 = Integer.parseInt(input[6]);
			int q2 = Integer.parseInt(input[7]);
			
			check(x1, y1, p1, q1, x2, y2, p2, q2);
		}
		
		System.out.println(sb);
	}

	private static void check(int x1, int y1, int p1, int q1, int x2, int y2, int p2, int q2) {

		if(x1 > p2 || x2 > p1 || y1 > q2 || y2 > q1) {
			sb.append("d\n");
			return;
		}
		
		if( (x1 == p2 && y1 == q2) ||
			(x1 == p2 && q1 == y2) ||
			(x2 == p1 && y2 == q1) ||
			(x2 == p1 && y1 == q2)) {
				sb.append("c\n");
				return;
			}
		
		if(x1 == p2 || x2 == p1 || y1 == q2 || y2 == q1) {
			sb.append("b\n");
			return;
		}
		
		sb.append("a\n");
		return;
	}
}
