import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        int answer = tryAtoB(A, B);

        System.out.println(answer);
    }

    static int tryAtoB(int A, int B) {
        int count = 0;

        Queue<Long> nums = new ArrayDeque<>();
        nums.offer((long) A);

        while (!nums.isEmpty()) {
            int cnt = nums.size();
            count++;
            while (cnt-- > 0) {
                long cur = nums.poll();

                if (cur == B) return count;
                else if (cur > B) continue;

                nums.offer(cur*2);
                nums.offer(cur*10 + 1);
            }
        }

        return -1;
    }
}
