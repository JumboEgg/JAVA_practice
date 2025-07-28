import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] input = br.readLine().toCharArray();
        Stack<Character> left = new Stack<>();
        Stack<Character> right = new Stack<>();
        for(char c : input) left.add(c);

        int M = Integer.parseInt(br.readLine());
        for(int i=0; i<M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();

            if("P".equals(cmd)) {
                char c = st.nextToken().charAt(0);
                left.add(c);
            }
            else if("L".equals(cmd)) {
                if(left.isEmpty()) continue;
                right.add(left.pop());
            }
            else if("D".equals(cmd)) {
                if(right.isEmpty()) continue;
                left.add(right.pop());
            }
            else {
                if(left.isEmpty()) continue;
                left.pop();
            }
        }
        StringBuilder result = new StringBuilder();
        for(char c : left) result.append(c);
        while (!right.isEmpty()) result.append(right.pop());

        System.out.println(result);
    }
}
