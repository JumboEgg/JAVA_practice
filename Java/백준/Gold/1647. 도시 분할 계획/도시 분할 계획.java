import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Road implements Comparable<Road> {
        int start;
        int end;
        int price;

        Road(int start, int end, int price) {
            this.start = start;
            this.end = end;
            this.price = price;
        }


        @Override
        public int compareTo(Road o) {
            return this.price - o.price;
        }

        @Override
        public String toString() {
            return "start: " + start + ", end: " + end + ", price: " + price;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Road> roads = new ArrayList<>();
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());

            roads.add(new Road(a, b, p));
        }

        Collections.sort(roads);

        int[] parent = new int[N+1];
        for(int i=1; i<=N; i++) parent[i] = i;

        List<Road> selectedRoads = new ArrayList<>();
        for(Road road : roads) {
            if (findParent(parent, road.start) == findParent(parent, road.end)) continue;
            selectedRoads.add(road);
            connect(parent, road.start, road.end);

            if (selectedRoads.size() == N-1) break;
        }

        int answer = 0;
        for(int i=0; i<N-2; i++) answer += selectedRoads.get(i).price;

        System.out.println(answer);
    }

    static void connect(int[] parent, int a, int b) {
        if (findParent(parent, a) == findParent(parent, b)) return;
        parent[findParent(parent, b)] = findParent(parent, a);
    }

    static int findParent(int[] parent, int child) {
        if (child == parent[child]) return child;
        return parent[child] = findParent(parent, parent[child]);
    }
}
