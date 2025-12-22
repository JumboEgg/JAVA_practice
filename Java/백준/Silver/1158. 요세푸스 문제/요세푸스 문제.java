import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        List<Integer> josephus = new ArrayList<>();
        Queue<Integer> nums = new ArrayDeque<>();

        for(int i=1; i<=N; i++) nums.add(i);

        int cnt = 0;
        while (!nums.isEmpty()) {
            int temp = nums.poll();
            cnt++;

            if (cnt < K) nums.add(temp);
            else {
                josephus.add(temp);
                cnt = 0;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append('<');
        for(int i=0; i<N-1; i++) sb.append(josephus.get(i)).append(", ");
        sb.append(josephus.get(N-1)).append('>');

        System.out.println(sb);
    }
}
