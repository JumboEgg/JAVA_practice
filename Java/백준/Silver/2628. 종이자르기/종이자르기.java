import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
	
	public static int max;
	public static ArrayList<Integer> cutX, cutY;

	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String[] input = br.readLine().split(" ");
		int X = Integer.parseInt(input[0]);
		int Y = Integer.parseInt(input[1]);
		
		int cut = Integer.parseInt(br.readLine());
		
		cutX = new ArrayList<>();
		cutY = new ArrayList<>();
		
		cutX.add(0);
		cutY.add(0);
		cutX.add(X);
		cutY.add(Y);
		
		for(int i=0; i<cut; i++) {
			input = br.readLine().split(" ");
			
			if("0".equals(input[0])) {
				cutY.add(Integer.parseInt(input[1]));
			}
			else {
				cutX.add(Integer.parseInt(input[1]));
			}
		}
		
		max = Integer.MIN_VALUE;
		
		Collections.sort(cutX);
		Collections.sort(cutY);
		
		compareSize();
		
		sb.append(max);
		
		System.out.println(sb);
	}

	private static void compareSize() {
		int size = 0;
		for(int i=1; i<cutX.size(); i++) {
			for(int j=1; j<cutY.size(); j++) {
				size = (cutX.get(i)-cutX.get(i-1))*(cutY.get(j)-cutY.get(j-1));
				max = Math.max(max, size);
			}
		}
	}
}
