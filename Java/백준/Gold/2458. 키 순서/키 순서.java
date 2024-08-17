import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class Main {

	static class Node {
		
		int no; //자신의 번호
		ArrayList<Node> taller = new ArrayList<>();
		ArrayList<Node> shorter = new ArrayList<>();
		public Node(int no, ArrayList<Node> taller, ArrayList<Node> shorter) {
			super();
			this.no = no;
			this.taller = taller;
			this.shorter = shorter;
		}
	}
	
	static int N; //학생 수
	static Node[] stdList; //학생 리스트	
	static boolean[] visited;
	static Stack<Integer> toVisit;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		int M = Integer.parseInt(input[1]);
		
		stdList = new Node[N+1]; //1부터 시작
		for(int i=1; i<=N; i++) {
			stdList[i] = new Node(i, new ArrayList<Node>(), new ArrayList<Node>());
		}
		
		for(int i=0; i<M; i++) {
			input = br.readLine().split(" ");
			
			int taller = Integer.parseInt(input[0]);
			int shorter = Integer.parseInt(input[1]);
			
			stdList[taller].shorter.add(stdList[shorter]);
			stdList[shorter].taller.add(stdList[taller]);
		}
		
		visited = new boolean[N+1];
		toVisit = new Stack<>();
		
		int count = N;
		for(int i=1; i<=N; i++) {
			dfsTall(i);
			dfsShort(i);
			
			for(int j=1; j<=N; j++) {
				if(!visited[j]) {
					count--;
					break;
				}
			}
			
			Arrays.fill(visited, false);
		}
		
		sb.append(count);
		
		System.out.println(sb);
	}


	private static void dfsTall(int stdNum) {
		
		visited[stdNum] = true;
		
		for(Node n : stdList[stdNum].taller) {
			if(!visited[n.no]) toVisit.push(n.no);
		}
		
		if(toVisit.isEmpty()) return;
		
		dfsTall(toVisit.pop());
	}
	
	private static void dfsShort(int stdNum) {
		
		visited[stdNum] = true;
		
		for(Node n : stdList[stdNum].shorter) {
			if(!visited[n.no]) toVisit.push(n.no);
		}
		
		if(toVisit.isEmpty()) return;
		
		dfsShort(toVisit.pop());
	}
}
