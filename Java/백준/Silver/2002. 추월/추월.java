import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        HashSet<String> passed = new HashSet<>(); // 추월한 차량

        Queue<String> start = new ArrayDeque<>();
        Queue<String> end = new ArrayDeque<>();
        for (int i=0; i<N; i++) start.offer(br.readLine());
        for (int i=0; i<N; i++) end.offer(br.readLine());

        while (!end.isEmpty()) {
            if(passed.contains(start.peek())) start.poll();
            else if(start.peek().equals(end.peek())) {
                start.poll();
                end.poll();
            } else {
                passed.add(end.poll());
            }
        }

        System.out.println(passed.size());
    }
}
