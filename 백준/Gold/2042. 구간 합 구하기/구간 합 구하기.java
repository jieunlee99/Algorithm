import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, M, K;
	static long[] nums; // 입력
	static long[] tree;
	static int S; // leaf start, size

	public static void main(String[] args) throws IOException {
		
//		System.setIn(new FileInputStream("src/DAY03/P2042/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 수의 개수
		M = Integer.parseInt(st.nextToken()); // 수의 변경이 일어나는 횟수
		K = Integer.parseInt(st.nextToken()); // 구간의 합을 구하는 횟수

		nums = new long[N];
		for(int i=0; i<N; i++) {
			nums[i] = Long.parseLong(br.readLine());
		}
//		System.out.println(Arrays.toString(nums));
		
		// 리프 개수에 맞게....
		S = 1;
		while (S < N) {
			S *= 2;
		}

		tree = new long[S * 2]; // S*2-1개 필요하고 0번 비워둠
		init();
		
		// a, b, c
		for(int i=0; i<M+K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long c = Long.parseLong(st.nextToken());
//			System.out.println(a+" "+b+" "+c);
			
			if(a == 1) {
				// a = 1, b번째 수를 c로 바꾸기 -> update
				updateBU(b, c);
			} else if(a == 2) {
				// a = 2, b번째 수부터 c번쨰 수의 합을 구하여 출력-> query
				long sum = queryBU(b, (int)c);
				System.out.println(sum);
			}
		}

	}

	// bottom-up 으로만 해도 충분
	static void init() {
		for (int i = 0; i < N; i++) {
			tree[S + i] = nums[i];
		}

		for (int i = S - 1; i > 0; i--) {
			tree[i] = tree[i * 2] + tree[i * 2 + 1];
		}
	}

	// top-down

	static long query(int left, int right, int node, int queryLeft, int queryRight) {
		if (queryRight < left || right < queryLeft) { // 영역 밖 -> 무시
			return 0; // 영향가지 않는 값 return (합 0, 곱 1)
		} else if (queryLeft <= left && right <= queryRight) { // 영역 안
			return tree[node];
		} else { // 걸쳐있음
			int mid = (left + right) / 2;
			return query(left, mid, node * 2, queryLeft, queryRight)
					+ query(mid + 1, right, node * 2 + 1, queryLeft, queryRight);
		}
	}

	static void update(int left, int right, int node, int target, long diff) {
		if (target < left || right < target) { // 상관 X
			return;
		} else {
			tree[node] += diff;
			if (left != right) { // 내부 노드일 때
				int mid = (left + right) / 2;
				update(left, mid, node * 2, target, diff);
				update(mid + 1, right, node * 2 + 1, target, diff);
			}
		}
	}

	// bottom-up

	static long queryBU(int queryLeft, int queryRight) {
		// S가 리프의 시작 (무조건 짝수)
		int left = S + queryLeft - 1;
		int right = S + queryRight - 1;

		long sum = 0;

		// left=right일 때는 하나의 값 존재
		while (left <= right) {
			if (left % 2 == 1) {
				sum += tree[left++]; // 내 값을 더해준다.
			}

			if (right % 2 == 0) { //
				sum += tree[right--]; // 내 값을 더해준다.
			}

			left /= 2;
			right /= 2;
		}

		return sum;
	}

	static void updateBU(int target, long value) {
		int node = S + target - 1;
		tree[node] = value;

		node /= 2;
		while (node > 0) {
			tree[node] = tree[node * 2] + tree[node * 2 + 1];
			node /= 2;
		}
	}
}
