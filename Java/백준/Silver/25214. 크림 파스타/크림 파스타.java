import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] nums = new int[N];
        for(int i=0; i<N; i++) nums[i] = Integer.parseInt(st.nextToken());

        int min = nums[0];
        int[] diff = new int[N];

        sb.append("0 ");

        for(int i=1; i<N; i++) {
            diff[i] = Math.max(diff[i-1], nums[i]-min);
            min = Math.min(nums[i], min);
            sb.append(diff[i]).append(" ");
        }

        System.out.println(sb);
    }
}
