import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int score = Integer.parseInt(br.readLine());
        char result;
        if(score < 60) result = 'F';
        else if(score < 70) result = 'D';
        else if(score < 80) result = 'C';
        else if(score < 90) result = 'B';
        else result = 'A';
        System.out.println(result);
    }
}