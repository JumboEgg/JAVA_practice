import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] S = br.readLine().toCharArray();
        char[] bomb = br.readLine().toCharArray();

        Stack<Character> c = new Stack<>();
        for(char s : S) {
            c.push(s);
            if (s == bomb[bomb.length-1]) {
                if (checkBomb(c, bomb)) removeBomb(c, bomb);
            }
        }

        StringBuilder sb = new StringBuilder();
        for(char i : c) sb.append(i);

        System.out.println(sb.length() > 0 ? sb : "FRULA");
    }

    // stack 내에 폭발 문자열과 일치하는 문자열이 생성되었는지 확인
    static boolean checkBomb(Stack<Character> c, char[] bomb) {
        if (c.size() < bomb.length) return false;
        int lastIdx = c.size() - 1;
        for(int i=0; i<bomb.length; i++) {
            if (c.get(lastIdx - i) != bomb[bomb.length - 1 - i]) return false;
        }
        return true;
    }

    // 폭발 문자열의 글자 수만큼 문자 제거
    static void removeBomb(Stack<Character> c, char[] bomb) {
        for(int i=0; i<bomb.length; i++) {
            c.pop();
        }
    }
}
