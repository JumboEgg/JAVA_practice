import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static List<Integer> nums;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nums = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(nums);
        generateArray(0, 0, "");
        System.out.print(sb);
    }

    static void generateArray(int index, int cnt, String curString) {
        if (cnt == M) {
            sb.append(curString).append("\n");
            return;
        }

        int prev = -1;
        for (int i = index; i < N; i++) {
            int current = nums.get(i);
            if (current == prev) continue;
            prev = current;
            generateArray(i, cnt + 1, curString + current + " ");
        }
    }
}
