import java.io.*;
import java.util.*;

public class Main {
	static int N, M, S = 1;
	static int[] segTree; // 세그먼트 트리 (강수량 최대값 저장)
	static YearInfo[] infos; // 입력받은 연도별 데이터 (1-based)
	static Map<Integer, YearInfo> yearMap = new HashMap<>();
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// N 입력
		N = Integer.parseInt(br.readLine());
		while (S < N)
			S <<= 1;

		infos = new YearInfo[N + 1];
		segTree = new int[2 * S];

		// 강수량 정보 입력
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int year = Integer.parseInt(st.nextToken());
			int rain = Integer.parseInt(st.nextToken());

			infos[i] = new YearInfo(year, rain, i);
			segTree[i + S - 1] = rain;
			yearMap.put(year, infos[i]);
		}

		// 세그먼트 트리 초기화
		build();

		// 쿼리 입력
		M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int beforeY = Integer.parseInt(st.nextToken());
			int nowX = Integer.parseInt(st.nextToken());
			sb.append(processQuery(beforeY, nowX)).append("\n");
		}

		System.out.print(sb);
	}

	// 세그먼트 트리 초기화
	static void build() {
		for (int i = S - 1; i > 0; i--) {
			segTree[i] = Math.max(segTree[i * 2], segTree[i * 2 + 1]);
		}
	}

	// 구간 최대값 쿼리
	static int query(int l, int r) {
		if (l > r)
			return 0;
		int res = 0;
		l += S - 1;
		r += S - 1;
		while (l <= r) {
			if ((l & 1) == 1)
				res = Math.max(res, segTree[l++]);
			if ((r & 1) == 0)
				res = Math.max(res, segTree[r--]);
			l >>= 1;
			r >>= 1;
		}
		return res;
	}

	// 쿼리 처리
	static String processQuery(int beforeY, int nowX) {
		YearInfo before = yearMap.get(beforeY);
		YearInfo now = yearMap.get(nowX);

		// (1) 두 연도 모두 기록 없음
		if (before == null && now == null)
			return "maybe";

		// (2) 이전 연도만 기록됨
		if (before != null && now == null) {
			int left = before.index + 1;
			int right = findInsertPosition(nowX) - 1;
			int maxRain = query(left, right);
			return (maxRain < before.rain) ? "maybe" : "false";
		}

		// (3) 현재 연도만 기록됨
		if (before == null && now != null) {
			int left = findInsertPosition(beforeY);
			int right = now.index - 1;
			int maxRain = query(left, right);
			return (maxRain >= now.rain) ? "false" : "maybe";
		}

		// (4) 두 연도 모두 기록 있음
		if (before.index >= now.index)
			return "false";

		int maxRain = query(before.index + 1, now.index - 1);
		if (before.rain < now.rain || maxRain >= now.rain)
			return "false";

		int recordedYears = now.index - before.index - 1;
		int actualGap = now.year - before.year - 1;
		return (recordedYears == actualGap) ? "true" : "maybe";
	}

	// year가 들어가야 할 위치 찾기 (lower bound)
	static int findInsertPosition(int year) {
		int l = 1, r = N;
		while (l < r) {
			int mid = (l + r) / 2;
			if (infos[mid].year < year)
				l = mid + 1;
			else
				r = mid;
		}
		return r;
	}
}

// 연도 정보 클래스
class YearInfo {
	int year, rain, index;

	public YearInfo(int year, int rain, int index) {
		this.year = year;
		this.rain = rain;
		this.index = index;
	}
}
