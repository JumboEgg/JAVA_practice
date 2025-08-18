import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        List<List<int[]>> roads = new ArrayList<>();
        for(int i=0; i<=D; i++) {
            roads.add(new ArrayList<>());
        }

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            if (end > D) continue;

            roads.get(end).add(new int[] {start, dist});
        }

        int map[] = new int[D+1];
        for(int i=1; i<=D; i++) {
            map[i] = map[i-1] + 1;

            if(roads.get(i).isEmpty()) continue;
            for(int[] road : roads.get(i)) {
                map[i] = Math.min(map[i], map[road[0]] + road[1]);
            }
        }

        System.out.println(map[D]);
    }
}
