import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Problem implements Comparable<Problem> {
        int level;
        int num;

        Problem(int num, int level) {
            this.num = num;
            this.level = level;
        }

        @Override
        public int compareTo(Problem o) {
            if (this.level == o.level) return this.num - o.num;
            return this.level - o.level;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        TreeSet<Problem> tree = new TreeSet<>();
        HashMap<Integer, Integer> problems = new HashMap<>();

        int N = Integer.parseInt(st.nextToken());
        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            tree.add(new Problem(n, l));
            problems.put(n, l);
        }

        int M = Integer.parseInt(br.readLine());
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            executeCommand(st, tree, problems, sb);
        }

        System.out.print(sb);
    }

    static void executeCommand(StringTokenizer st, TreeSet<Problem> tree, HashMap<Integer, Integer> ps, StringBuilder sb) {
        String command = st.nextToken();
        if ("add".equals(command)) {
            int n = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            tree.add(new Problem(n, l));
            ps.put(n, l);
        } else if ("recommend".equals(command)) {
            int order = Integer.parseInt(st.nextToken());

            if (order == 1) sb.append(tree.last().num).append("\n");
            else sb.append(tree.first().num).append("\n");
        } else {
            int n = Integer.parseInt(st.nextToken());
            int l = ps.get(n);

            tree.remove(new Problem(n, l));
            ps.remove(n);
        }
    }
}
