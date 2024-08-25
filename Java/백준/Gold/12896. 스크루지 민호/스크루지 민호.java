import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

public class Main {

	static class City {

		ArrayList<Integer> connected;

		public City() {
			super();
		}

		public City(ArrayList<Integer> connected) {
			this.connected = connected;
		}

	}

	static int N;
	static City[] road;
	static int min = Integer.MAX_VALUE;
	
	static int far;
	static int max = Integer.MIN_VALUE;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		road = new City[N + 1]; // 0번 노드 사용하지 않음
		
		for(int i=1; i<N+1; i++) {
			road[i] = new City(new ArrayList<>());
		}

		for (int i = 0; i < N - 1; i++) {
			String[] input = br.readLine().split(" ");

			int to = Integer.parseInt(input[0]);
			int from = Integer.parseInt(input[1]);

			road[to].connected.add(from);
			road[from].connected.add(to);
		}

		findFarthest(1);
		findFarthest(far);
		
		System.out.println((max + 1)/2);
	}

	private static void findFarthest(int start) {
		
		Queue<Integer> toVisit = new ArrayDeque<>();
		boolean[] visited = new boolean[N + 1];

		toVisit.offer(start);
		visited[start] = true;
		
		int farthest = start;
		int count = -1;

		while (!toVisit.isEmpty()) {

			count++;
			int size = toVisit.size();

			while (--size >= 0) {

				int cur = toVisit.poll();
				
				for (int to : road[cur].connected) {
					if (!visited[to]) {
						toVisit.offer(to);
						farthest = to;
						visited[to] = true;
					}
				}
			}
		}

		if(max < count) {
			max = count;
			far = farthest;
		}
		
	}
}
