import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int q = Integer.parseInt(br.readLine());

        int total = 0;
        HashMap<String, Integer> status = new HashMap<>();
        while (q-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            String cmd = st.nextToken();

            int curEmp = status.getOrDefault(name, 0);

            if ("+".equals(cmd)) {
                status.put(name, curEmp + 1);
            } else {
                if (curEmp == 0) total++;
                else status.put(name, curEmp - 1);
            }
        }

        for(String key : status.keySet()) {
            total += status.get(key);
        }

        System.out.println(total);
    }
}
