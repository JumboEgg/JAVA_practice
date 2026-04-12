import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] flowers = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            flowers[i] = Integer.parseInt(st.nextToken());
        }

        int[] psum = new int[N];
        psum[0] = flowers[0];
        for(int i=1; i<N; i++) {
            psum[i] = psum[i-1] + flowers[i];
        }

        int[] rPsum = new int[N];
        rPsum[N-1] = flowers[N-1];
        for(int i=N-2; i>=0; i--) {
            rPsum[i] = rPsum[i+1] + flowers[i];
        }

        int max = 0;

        for(int i=1; i<N-1; i++) {
            // 벌 벌 꿀통
            max = Math.max(max,
                    (psum[N-1] - flowers[0] - flowers[i]) +
                            (psum[N-1] - psum[i])
            );

            // 꿀통 벌 벌
            max = Math.max(max,
                    (psum[N-1] - flowers[N-1] - flowers[i]) +
                            psum[i-1]
            );

            // 벌 꿀통 벌
            max = Math.max(max,
                    (psum[i] - flowers[0]) +
                            (rPsum[i] - flowers[N-1])
            );
        }

        System.out.println(max);
    }
}