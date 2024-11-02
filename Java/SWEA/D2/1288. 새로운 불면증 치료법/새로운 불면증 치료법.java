import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			sb.append("#" + tc + " ");
			
			int N = Integer.parseInt(br.readLine());
			int cnt = 0;
			
			HashSet<Character> nums = new HashSet<>();
			
			do {
				String tmp = Integer.toString(N*(++cnt));
				for(char c : tmp.toCharArray()) {
					nums.add(c);
				}
			} while(nums.size() < 10);
			
			sb.append(cnt*N + "\n");
		}
		System.out.println(sb);
	}
}
