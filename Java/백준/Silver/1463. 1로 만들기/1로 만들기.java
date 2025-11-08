import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int calcNum[] = new int[N+1];
		calcNum[1] = 0;
		
		for(int i=2; i<=N; i++) {
			int min = Integer.MAX_VALUE;
			
			if(i%3==0) {
				int calc = calcNum[i/3] + 1;
				min = Math.min(min, calc);
			}
			
			if(i%2==0) {
				int calc = calcNum[i/2] + 1;
				min = Math.min(min, calc);
			}
			
			min = Math.min(min, calcNum[i-1]+1);
			
			calcNum[i] = min;
		}
		
//		System.out.println(Arrays.toString(calcNum));
		System.out.println(calcNum[N]);
	}
}
