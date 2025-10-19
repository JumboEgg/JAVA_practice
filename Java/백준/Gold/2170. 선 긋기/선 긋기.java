import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Line implements Comparable<Line>{
        int start;
        int end;

        Line(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Line o) {
            return this.start - o.start;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Queue<Line> lines = new PriorityQueue<>();
        while (N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            lines.offer(new Line(start, end));
        }

        int total = 0;
        Line first = lines.poll();
        int start = first.start;
        int end = first.end;

        while (!lines.isEmpty()) {
            Line cur = lines.poll();
            if (end < cur.start) {
                total += end - start;
                start = cur.start;
                end = cur.end;
            } else {
                end = Math.max(end, cur.end);
            }
        }

        total += end - start;

        System.out.println(total);
    }
}
