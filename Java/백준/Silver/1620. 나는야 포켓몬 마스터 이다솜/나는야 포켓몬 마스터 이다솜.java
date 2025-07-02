import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuffer sb = new StringBuffer();

        final String REGEX = "[0-9]+";

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        String[] numToName = new String[N+1];
        HashMap<String, Integer> nameToNum = new HashMap<>();

        for(int i=1; i<=N; i++) {
            String name = br.readLine();
            numToName[i] = name;
            nameToNum.put(name, i);
        }

        for(int i=0; i<M; i++) {
            String input = br.readLine();
            if(input.matches(REGEX)) {
                int id = Integer.parseInt(input);
                sb.append(numToName[id]).append("\n");
            } else {
                sb.append(nameToNum.get(input)).append("\n");
            }
        }

        System.out.println(sb);
    }
}
