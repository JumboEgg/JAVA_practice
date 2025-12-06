import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        List<Integer> orbs = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            orbs.add(Integer.parseInt(st.nextToken()));
        }

        searchMax(orbs, 0);

        System.out.println(max);
    }

    static void searchMax(List<Integer> orbs, int sum) {
        if (orbs.size() == 2) {
            max = Math.max(max, sum);
            return;
        }

        for(int i=1; i<orbs.size()-1; i++) {
            int orb = orbs.get(i);
            int prevOrb = orbs.get(i-1);
            int nextOrb = orbs.get(i+1);

            orbs.remove(i);
            searchMax(orbs, sum + prevOrb * nextOrb);
            orbs.add(i, orb);
        }
    }
}
