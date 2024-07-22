import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int year = Integer.parseInt(br.readLine());
        int result;
        
        if(year%100!=0) {
            if(year%4==0) result = 1;
            else result = 0;
        }
        else {
            if (year%400==0) result = 1;
            else result = 0;
        }
        
        System.out.println(result);
    }
}