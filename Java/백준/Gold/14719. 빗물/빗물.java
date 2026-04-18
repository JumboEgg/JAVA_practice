import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int max = 0;
        int[] blocks = new int[W];

        for(int i=0; i<W; i++) {
            blocks[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, blocks[i]);
        }

        int total = 0;
        boolean start = false;
        int startIdx = 0;

        for(int i=1; i<=max; i++) {
            start = false;
            startIdx = 0;
            for(int j=0; j<W; j++) {
                if(blocks[j] < i) continue;
                if(start) {
                    total += j - startIdx - 1;
                } else {
                    start = true;
                }
                startIdx = j;
            }
        }

        System.out.println(total);
    }
}
