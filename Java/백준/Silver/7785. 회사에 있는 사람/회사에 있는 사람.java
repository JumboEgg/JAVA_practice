import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.TreeSet;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		TreeSet<String> working = new TreeSet<>(Comparator.reverseOrder());
		
		int N = Integer.parseInt(br.readLine());
		for(int i=0; i<N; i++) {
			String[] input = br.readLine().split(" ");
			if("enter".equals(input[1])) working.add(input[0]);
			else working.remove(input[0]);
		}
		
		for(String worker : working) System.out.println(worker);
	}
}