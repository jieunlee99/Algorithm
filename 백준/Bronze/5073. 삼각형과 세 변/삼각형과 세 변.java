import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			if(a == 0 && b == 0 && c == 0) {
				break;
			}
			
			Set<Integer> set = new HashSet<>();
			set.add(a);
			set.add(b);
			set.add(c);
			

			if(a+b <= c || b+c<=a || a+c<=b) {
				sb.append("Invalid").append("\n");
			} else if(set.size() == 1) {
				sb.append("Equilateral").append("\n");
			} else if(set.size() == 2) {
				sb.append("Isosceles").append("\n");
			} else if(set.size() == 3) {
				sb.append("Scalene").append("\n");
			} 
		}
		
		System.out.print(sb.toString());
	}

}
