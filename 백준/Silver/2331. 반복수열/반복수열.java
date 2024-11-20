import java.util.*;
import java.io.*;

public class Main {

	static int A, P;
	static ArrayList<Integer> arrayList;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());

		arrayList = new ArrayList<>();
			
		int current = A;
		int next;
		
		arrayList.add(current);
		
		while(true) {
			next = makeNextNum(current);
			if(arrayList.contains(next)) {
				break;
			}
			arrayList.add(next);
			current = next;			
		}
		
		int cnt = arrayList.indexOf(next);
		System.out.println(cnt);
	}
	
	static int makeNextNum(int current) {
		int nextNum = 0;
		
		while(current > 0) {
			int temp = current % 10;
			nextNum += Math.pow(temp, P);
			current /= 10;
		}
		
		return nextNum;
	}
}
