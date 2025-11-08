import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        Stack<Integer> s = new Stack<>();

        int num = 1;
        while (N-- > 0) {
            int cur = Integer.parseInt(br.readLine());
            if (cur >= num) {
                while (num <= cur){
                    s.push(num++);
                    sb.append("+\n");
                }
                s.pop();
                sb.append("-\n");
            } else {
                if (s.peek() == cur) {
                    s.pop();
                    sb.append("-\n");
                } else {
                    sb.delete(0, sb.length());
                    sb.append("NO\n");
                    break;
                }
            }
        }
        System.out.print(sb);
    }
}
