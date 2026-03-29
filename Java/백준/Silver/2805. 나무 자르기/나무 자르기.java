import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int max = 0;
        int start = 0;
        int end = 0;
        int[] trees = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            trees[i] = Integer.parseInt(st.nextToken());
            end = Math.max(end, trees[i]);
        }

        while (start <= end) {
            int mid = (start + end)/2;
            long length = getLength(mid, trees);
            if(M <= length) {
                start = mid + 1;
                max = mid;
            } else {
                end = mid - 1;
            }
        }

        System.out.println(max);
    }

    static long getLength(int h, int[] trees) {
        long sum = 0;
        for(int tree : trees) {
            sum += Math.max(0, tree - h);
        }
        return sum;
    }
}
