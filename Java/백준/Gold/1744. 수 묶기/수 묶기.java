import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<Integer> p = new ArrayList<>();
        List<Integer> n = new ArrayList<>();
        for(int i=0; i<N; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num > 0) p.add(num);
            else n.add(num);
        }

        Collections.sort(p, Collections.reverseOrder());
        Collections.sort(n);

        int sum = 0;
        int idx = 0;
        while (idx < p.size()) {
            if (idx < p.size() - 1 && p.get(idx + 1) > 1) {
                sum += p.get(idx) * p.get(idx + 1);
                idx += 2;
            } else {
                sum += p.get(idx++);
            }
        }

        idx = 0;
        while (idx < n.size()) {
            if (idx < n.size() - 1) {
                sum += n.get(idx) * n.get(idx + 1);
                idx += 2;
            } else {
                sum += n.get(idx++);
            }
        }

        System.out.println(sum);
    }
}
