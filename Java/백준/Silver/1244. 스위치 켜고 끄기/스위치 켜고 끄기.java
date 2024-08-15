import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	
	public static int N;
	public static int[] state;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		state = new int[N];
		
		String[] input = br.readLine().split(" ");
		for(int i=0; i<N; i++) {
			state[i] = Integer.parseInt(input[i]);
		}
		
		int M = Integer.parseInt(br.readLine());
		for(int i=0; i<M; i++) {
			input = br.readLine().split(" ");
			
			int gen = Integer.parseInt(input[0]);
			int num = Integer.parseInt(input[1]);
			
			useSwitch(gen, num);
		}
		
		for(int i=0; i<N; i++) {
			if(i != 0 && i%20 == 0) {
				sb.append("\n");
			}
			sb.append(state[i] + " ");
		}
		
		System.out.println(sb);
	}

	private static void useSwitch(int gen, int num) {

		if(gen == 1) { //남학생
			int index = -1;
			while((index+=num) < N) {
				state[index] = (state[index] + 1) % 2;
			}
		}
		else { //여학생
			int index = 0;
			--num;
			
			state[num] = (state[num] + 1) % 2;
			
			while(num-(++index) > -1 && num+index < N
					&& state[num-index] == state[num+index]) {
				state[num-index] = (state[num-index] + 1) % 2;
				state[num+index] = (state[num+index] + 1) % 2;
			}
		}
	}
}
