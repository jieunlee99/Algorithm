import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, M; // N: 도시의 수, M: 여행 계획 도시의 수
	static int[] parent; // 유니온 파인드 배열

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		// 1. 유니온 파인드 배열 초기화 (1-based 인덱스 사용)
		parent = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}

		// 2. 연결 관계를 읽고 바로 Union 연산 수행
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				int connected = Integer.parseInt(st.nextToken());
				// 연결되어 있다면 (i와 j가 같은 집합에 속하도록) 합친다.
				if (connected == 1) {
					union(i, j);
				}
			}
		}

		// 3. 여행 계획 입력 및 확인
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] travelPlan = new int[M];
		for (int i = 0; i < M; i++) {
			travelPlan[i] = Integer.parseInt(st.nextToken());
		}

		// 여행 계획이 없으면 (M=0) 문제 조건상 YES일 수 있지만, M >= 1이므로
		if (M == 0) {
			System.out.println("YES");
			return;
		}

		// 4. 여행 계획의 모든 도시가 같은 집합에 속하는지 확인
		// 첫 번째 도시의 루트를 기준 루트로 설정
		int startRoot = find(travelPlan[0]);

		// 나머지 도시들의 루트가 startRoot와 같은지 확인
		for (int i = 1; i < M; i++) {
			if (find(travelPlan[i]) != startRoot) {
				System.out.println("NO");
				return;
			}
		}

		// 모든 도시의 루트가 같다면 여행 가능
		System.out.println("YES");
	}

	// 합집합 (Union by Rank/Size 없이 간단한 Union)
	static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);

		// 일반적으로 작은 번호를 부모로 설정
		if (aRoot != bRoot) {
			parent[bRoot] = aRoot;
		}
	}

	// 루트 찾기 (경로 압축 적용)
	static int find(int num) {
		if (parent[num] == num) {
			return num;
		}
		return parent[num] = find(parent[num]);
	}
}
