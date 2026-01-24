import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, max;
    static int[] nums;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        nums = new int[N];
        visited = new boolean[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i=0; i<N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0; i<N; i++) {
            visited[i] = true;
            getMaxSum(i, 1, 0);
            visited[i] = false;
        }

        System.out.println(max);
    }

    static void getMaxSum(int prev, int cnt, int sum) {
        if (cnt == N) {
            max = Math.max(max, sum);
            return;
        }

        for(int i=0; i<N; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            getMaxSum(i, cnt+1, sum + Math.abs(nums[prev] - nums[i]));
            visited[i] = false;
        }
    }
}
