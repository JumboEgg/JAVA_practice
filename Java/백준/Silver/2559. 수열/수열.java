import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static int N, K;
	public static int[] temp;
	public static int max;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		K = Integer.parseInt(input[1]);
		
		temp = new int[N];
		
		input = br.readLine().split(" ");
		for(int i=0; i<N; i++) {
			temp[i] = Integer.parseInt(input[i]);
		}
		
		max = Integer.MIN_VALUE;
		
		compareTemp();
		
		sb.append(max);
		
		System.out.println(sb);
	}

	private static void compareTemp() {

		int tempSum = 0;
		
		for(int i=0; i<K; i++) {
			tempSum += temp[i];
		}
		for(int i=0; i<N-K+1; i++) {
			if(tempSum > max) max = tempSum;
			tempSum -= temp[i];
			if(i+K > N-1) continue;
			tempSum += temp[i+K];
		}
	}
}
