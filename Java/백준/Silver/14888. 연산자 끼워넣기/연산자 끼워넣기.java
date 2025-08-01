import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] nums = new int[N];
		
		String[] input = br.readLine().split(" ");
		for(int i=0; i<N; i++) {
			nums[i] = Integer.parseInt(input[i]);
		}
		
		int[] op = new int[4];
		int cnt = 0;
		input = br.readLine().split(" ");
		for(int i=0; i<4; i++) {
			op[i] = Integer.parseInt(input[i]);
			cnt += op[i];
		}
		
		getMinMax(nums, op, 0, nums[0]);
		
		System.out.println(max);
		System.out.println(min);
	}
	
	static void getMinMax(int[] nums, int[] op, int index, int value) {
		if(index == nums.length - 1) {
			max = Math.max(max, value);
			min = Math.min(min, value);
			return;
		}
		
		index++;
		
		if(op[0] > 0) {
			int newValue = value + nums[index];
			op[0]--;
			getMinMax(nums, op, index, newValue);
			op[0]++;
		}
		
		if(op[1] > 0) {
			int newValue = value - nums[index];
			op[1]--;
			getMinMax(nums, op, index, newValue);
			op[1]++;
		}
		
		if(op[2] > 0) {
			int newValue = value * nums[index];
			op[2]--;
			getMinMax(nums, op, index, newValue);
			op[2]++;
		}
		
		if(op[3] > 0) {
			int newValue = value / nums[index];
			op[3]--;
			getMinMax(nums, op, index, newValue);
			op[3]++;
		}
	}
}
