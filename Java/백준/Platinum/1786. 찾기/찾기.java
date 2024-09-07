import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		char[] T = br.readLine().toCharArray();
		char[] P = br.readLine().toCharArray();
		
		int tLength = T.length;
		int pLength = P.length;
		
		//1단계 : 부분 일치 테이블 만들기
		int[] pi = new int[pLength];
		
		//i : 접미사 포인터(1부터 시작)
		//j : 접두사 포인터
		for(int i=1, j=0; i<pLength; i++) {
			while(j>0 && P[i]!=P[j]) {
				//j의 이전 위치 j-1까지는 일치했으므로 일치했던 곳으로 이동
				j = pi[j-1];
			}
			
			//접미사와 접두사가 같은 경우, 그 길이를 부분일치 테이블에 기록
			if(P[i] == P[j]) pi[i] = ++j;
			else pi[i] = 0;
		}
		
//		System.out.println(Arrays.toString(pi));
		
		
		//2단계 : 부분일치 테이블을 활용해 텍스트와 패턴 비교
		int count = 0; //일치하는 패턴 개수
		List<Integer> list = new ArrayList<>(); //일치하는 패턴의 첫 인덱스 번호 저장
		
		//i : 텍스트의 포인터(첫 글자부터 비교하기 위해 0부터 시작)
		//j : 패턴 포인터
		for(int i=0, j=0; i<tLength; i++) {
			
			//본문과 패턴이 불일치할 경우
			while(j>0 && T[i]!=P[j]) {
				//j의 이전 위치 j-1까지는 일치했으므로 일치했던 곳으로 이동
				j = pi[j-1];
			}
			
			//두 글자가 일치할 경우
			if(T[i] == P[j]) {
				//j가 패턴의 마지막 인덱스라면 패턴과 일치하는 단어 찾기 완료
				if(j==pLength-1) {
					count++;
					list.add(i-(pLength-1)+1); //찾은 단어의 첫 인덱스 번호 저장
					
					j = pi[j];
				}
				else j++; //i와 함께 증가하며 탐색
			}
		}
		
		System.out.println(count);
		if(count > 0) {
//			System.out.println(list);
			for(int index : list) {
				System.out.print(index + " ");
			}
		}
	}
}
