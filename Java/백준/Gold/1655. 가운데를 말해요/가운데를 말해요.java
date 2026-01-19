import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        Queue<Integer> prev = new PriorityQueue<>(Comparator.reverseOrder());
        Queue<Integer> post = new PriorityQueue<>();

        int first = Integer.parseInt(br.readLine());
        int second = Integer.parseInt(br.readLine());

        sb.append(first).append("\n");
        if (first < second) {
            prev.offer(first);
            post.offer(second);
            sb.append(first).append("\n");
        } else {
            prev.offer(second);
            post.offer(first);
            sb.append(second).append("\n");
        }
        N -= 2;

        while (N-- > 0) {
            int input = Integer.parseInt(br.readLine());
            if (input > post.peek()) {
                post.offer(input);
                if (post.size() > prev.size()) {
                    prev.offer(post.poll());
                }
            } else {
                prev.offer(input);
                if (prev.size() - post.size() > 1) {
                    post.offer(prev.poll());
                }
            }
            sb.append(prev.peek()).append("\n");
        }

        System.out.print(sb);
    }
}
