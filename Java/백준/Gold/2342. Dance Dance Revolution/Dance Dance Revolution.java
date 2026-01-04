import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int[][] cost = {
            {1, 2, 2, 2, 2},
            {0, 1, 3, 4, 3},
            {0, 3, 1, 3, 4},
            {0, 4, 3, 1, 3},
            {0, 3, 4, 3, 1}
    };

    static List<Integer> inputs;
    static int size;
    static int[][][] energy;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        inputs = new ArrayList<>();
        while (true) {
            String input = st.nextToken();
            if ("0".equals(input)) break;
            inputs.add(Integer.parseInt(input));
        }

        size = inputs.size();

        // 현재 idx, 왼발, 오른발
        energy = new int[size][5][5];
        int answer = getMinEnergy(0, 0, 0);

        System.out.println(answer);
    }

    static int getMinEnergy(int idx, int left, int right) {
        if (idx == size) return 0;
        if (energy[idx][left][right] != 0) return energy[idx][left][right];

        int next = inputs.get(idx);
        energy[idx][left][right] = Math.min(
                getMinEnergy(idx + 1, next, right) + cost[left][next],
                getMinEnergy(idx + 1, left, next) + cost[right][next]
        );

        return energy[idx][left][right];
    }
}
