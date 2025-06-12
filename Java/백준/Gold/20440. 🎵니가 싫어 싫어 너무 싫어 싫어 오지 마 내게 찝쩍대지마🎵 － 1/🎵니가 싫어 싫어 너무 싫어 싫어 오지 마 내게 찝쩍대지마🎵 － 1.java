import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        TreeMap<Integer, Integer> variance = new TreeMap<>();

        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            changeVar(variance, start, 1);
            changeVar(variance, end, -1);
        }

        int cur = 0; // 현재 모기 수
        int max = Integer.MIN_VALUE; // 최대 모기 수
        int start = 0; // 최대 구간 시작 시간
        int end = 0; // 최대 구간 종료 시간
        for(Integer key : variance.keySet()) {
            int var = variance.get(key);

            if(var > 0) {
                cur += var;
                if(cur > max) {
                    start = key;
                    end = key;
                    max = cur;
                }
            }
            else if(var < 0) {
                cur += var;
                if(start == end) end = key;
            }
        }

        if(start == end) end = N;

        System.out.println(max);
        System.out.println(start + " " + end);
    }

    static void changeVar(TreeMap<Integer, Integer> variance, int time, int value) {
        if(variance.containsKey(time)) {
            int var = variance.get(time) + value;
            variance.put(time, var);
        } else variance.put(time, value);
    }
}
