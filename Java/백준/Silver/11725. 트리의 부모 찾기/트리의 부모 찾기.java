import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Node {
        int num;
        int parent;
        List<Node> children;

        Node(int num) {
            this.num = num;
            this.children = new ArrayList<>();
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        Node[] nodes = new Node[N+1];

        for(int i=1; i<=N; i++) nodes[i] = new Node(i);

        for(int i=0; i<N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            nodes[a].children.add(nodes[b]);
            nodes[b].children.add(nodes[a]);
        }

        findParents(nodes);

        StringBuilder sb = new StringBuilder();
        for(int i=2; i<=N; i++) {
            sb.append(nodes[i].parent).append("\n");
        }

        System.out.println(sb);
    }

    static void findParents(Node[] nodes) {
        Queue<Node> q = new ArrayDeque<>();
        q.offer(nodes[1]);

        while (!q.isEmpty()) {
            Node n = q.poll();

            for(Node c : n.children) {
                if (c.parent != 0 || c.num == 1) continue;
                c.parent = n.num;
                q.offer(c);
            }
        }
    }
}
