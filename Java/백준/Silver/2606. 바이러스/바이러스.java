import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        int[] parents = new int[N+1];
        for(int i=1; i<=N; i++) parents[i] = i;

        while (K-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            connectNodes(a, b, parents);
        }

        int count = -1;
        for(int i=1; i<=N; i++) {
            if (getParent(i, parents) == getParent(1, parents)) count++;
        }

        System.out.println(count);
    }

    static void connectNodes(int a, int b, int[] parents) {
        int pa = getParent(a, parents);
        int pb = getParent(b, parents);
        if (pa != pb) parents[pa] = pb;
    }

    static int getParent(int i, int[] parents) {
        if (parents[i] == i) return i;
        return parents[i] = getParent(parents[i], parents);
    }
}
