import java.io.BufferedReader;
//import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[] parent;

	public static void main(String[] args) throws NumberFormatException, IOException {
//		System.setIn(new FileInputStream("src/DAY06/P1717/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		parent = new int[N+1];
		for(int i=0; i<=N; i++) {
			parent[i] = i;
		}

		int op, a, b;
		int aRoot, bRoot;
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			op = Integer.parseInt(st.nextToken());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			aRoot = find(a);
			bRoot = find(b);
			
			// 합집합
			if(op == 0) {
				parent[aRoot] = bRoot;
			} 
			// 두 원소가 같은 집합에 포함되어 있는지 확인
			else if(op == 1) {
				// 같은 집합에 포함되어 있다면 "YES", 그렇지 않다면 "NO" 출력
				if(aRoot == bRoot) {
					System.out.println("YES");
				} else {
					System.out.println("NO");
				}
			}
		}
	}
	
	static int find(int num) {
		if(parent[num] == num) return num;
		else return parent[num] = find(parent[num]);
	}
}
