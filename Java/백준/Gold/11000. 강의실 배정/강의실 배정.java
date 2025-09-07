import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Lecture implements Comparable<Lecture> {
        int start;
        int end;

        Lecture(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Lecture o) {
            if(this.end == o.end) return this.start - o.start;
            else return this.end - o.end;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<Lecture> lectures = new ArrayList<>();
        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            lectures.add(new Lecture(start, end));
        }

        lectures.sort(new Comparator<Lecture>() {
            @Override
            public int compare(Lecture o1, Lecture o2) {
                if(o1.start != o2.start) return o1.start - o2.start;
                return o1.end - o2.end;
            }
        });

        PriorityQueue<Lecture> pq = new PriorityQueue<>();
        pq.offer(lectures.get(0));

        for(int i=1; i<N; i++) {
            Lecture l = pq.peek();
            // 한 강의실에서 할 수 있는 강의를 찾으면 강의실이 비는 시간 갱신
            if(l.end <= lectures.get(i).start) pq.poll();
            pq.offer(lectures.get(i));
        }

        System.out.println(pq.size());
    }
}
