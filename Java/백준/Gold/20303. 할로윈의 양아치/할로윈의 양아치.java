import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static class Child {
		int candy;
		List<Integer> friends;
		
		Child(int candy) {
			this.candy = candy;
			this.friends = new ArrayList<>();
		}
	}
	
	static class CandyGroup {
		int candies;
		int children;
		
		CandyGroup(int candies, int children) {
			this.candies = candies;
			this.children = children;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		//사탕 개수 입력
		Child[] childInfo = new Child[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			childInfo[i] = new Child(Integer.parseInt(st.nextToken()));
		}
		
		//친구 관계 입력
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int c1 = Integer.parseInt(st.nextToken());
			int c2 = Integer.parseInt(st.nextToken());
			
			childInfo[c1].friends.add(c2);
			childInfo[c2].friends.add(c1);
		}
		
		//친구의 친구 탐색
		List<CandyGroup> candyGroup = new ArrayList<>();
		boolean[] isFriend = new boolean[N+1];
		for(int i=1; i<=N; i++) {
			if(isFriend[i]) continue;
			
			Queue<Integer> toVisit = new ArrayDeque<>();
			toVisit.add(i);
			isFriend[i] = true;
			
			List<Integer> friendGroup = new ArrayList<>();
			friendGroup.add(i);
			int totalCandy = childInfo[i].candy;
			
			while(!toVisit.isEmpty()) {
				int cur = toVisit.poll();
				for(int friend : childInfo[cur].friends) {
					if(isFriend[friend]) continue;
					
					totalCandy += childInfo[friend].candy;
					friendGroup.add(friend);
					isFriend[friend] = true;
					toVisit.add(friend);
				}
			}
			
//			System.out.println(friendGroup.toString() + " " + totalCandy);
			
			//각 친구 그룹이 보유한 사탕 수 저장
			candyGroup.add(new CandyGroup(totalCandy, friendGroup.size()));
		}
		
		int G = candyGroup.size();
		int[][] C = new int[G+1][K]; //최대 K 미만
		
		for(int g=1; g<=G; g++) {
			int gSize = candyGroup.get(g-1).children;
			int cNum = candyGroup.get(g-1).candies;
			
			for(int c=1; c<K; c++) {
				if(gSize <= c) {
					C[g][c] = Math.max(C[g-1][c], cNum+C[g-1][c-gSize]);
				} else {
					C[g][c] = C[g-1][c];
				}
			}
		}
		
		System.out.println(C[G][K-1]);
	}
}
