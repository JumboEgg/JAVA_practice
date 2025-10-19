import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        Queue<Integer> time = new PriorityQueue<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            time.offer(Integer.parseInt(st.nextToken()));
        }

        int total = 0;
        while (!time.isEmpty()) {
            total += N * time.poll();
            N--;
        }

        System.out.println(total);
    }
}
