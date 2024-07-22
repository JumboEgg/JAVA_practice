import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int x = Integer.parseInt(br.readLine());
        int y = Integer.parseInt(br.readLine());
        int result;
        if(x>0 && y>0) result = 1;
        else if(x<0 && y>0) result = 2;
        else if(x<0 && y<0) result = 3;
        else result = 4;
        System.out.println(result);
    }
}