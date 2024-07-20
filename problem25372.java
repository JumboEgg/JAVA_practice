import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
//    public static boolean isNum(String passcode) {
//        return passcode.chars().allMatch(Character::isDigit);
//    }
//    아니 누가 현관문 비밀번호에 영문자 넣는데
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String passcode = new String();
        boolean result = false;
        for(int i=0; i<N; i++) {
            passcode = br.readLine();
            if(passcode.length() >= 10 || passcode.length() <= 5) result = false;
//            else result = isNum(passcode);
            else result = true;

            if(result) System.out.println("yes");
            else System.out.println("no");
        }
    }
}