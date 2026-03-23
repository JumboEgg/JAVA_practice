import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] parent;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        parent = new int[N];
        for(int i=0; i<N; i++) parent[i] = i;

        int answer = 0;

        for(int m=1; m<=M; m++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(getParent(a) == getParent(b)) {
                answer = m;
                break;
            }

            union(a, b);
        }

        System.out.println(answer);
    }

    static void union(int a, int b) {
        int pa = getParent(a);
        int pb = getParent(b);

        if (pa > pb) parent[pa] = pb;
        else parent[pb] = pa;
    }

    static int getParent(int c) {
        if (parent[c] == c) return c;
        return parent[c] = getParent(parent[c]);
    }
}
