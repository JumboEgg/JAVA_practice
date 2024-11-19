import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		boolean[] isPrime = new boolean[N+1];
		Arrays.fill(isPrime, true);
		isPrime[1] = false;
		
		for(int i=2; i<=Math.sqrt(N); i++) {
			if(!isPrime[i]) continue;
			for(int j=i*2; j<=N; j+=i) {
				isPrime[j] = false;
			}
		}
		
		int b=0;
		int s=0;
		for(int i=1; i<=N; i++) {
			if(isPrime[i] && !isPrime[i-1]) {
				b--;
				s += 2;
			} else if(isPrime[i]) s++;
			else b++;
		}
		
		System.out.println(b + " " + s);
	}
}