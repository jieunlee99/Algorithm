import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int T; // 0 < t < 100

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            // 1 <= k, c <= 10^9
            // k명이 참가한다면, k*x+1개의 사탕이 필요하다. (x는 자연수)
            // 한 봉지에는 총 c개의 사탕이 들어있다.
            // k*x + 1 = c*y (여기서 나온 y를 출력하면 된다.)
            long k = Long.parseLong(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            sb.append(solve(k, c)).append("\n");
        }

        System.out.print(sb);
    }

    // 확장 유클리드 호제법
    static Result extendedGcd(long a, long b) {
        if (b == 0) {
            return new Result(a, 1, 0);
        }

        Result result = extendedGcd(b, a % b);
        long gcd = result.gcd;
        long x = result.y;
        long y = result.x - (a / b) * result.y;
        return new Result(gcd, x, y);
    }

    static String solve(long k, long c) {
        
        // C가 1일 때 특수한 경우
        if (c == 1) {
            // 해가 k보다 작으면 가능, 크면 불가능
            return k + 1 > 1_000_000_000 ? "IMPOSSIBLE" : String.valueOf(k + 1);
        }

        // K가 1일 때 특수한 경우
        if (k == 1) {
            return "1";
        }
        
        Result result = extendedGcd(k, c);

        /// 해가 존재하지 않음
        if (result.gcd != 1) {
            return "IMPOSSIBLE";
        }

        // 초기 해
        long x0 = result.x;
        long y0 = result.y;

        // y가 자연수가 되기 위해 k 값 조정
        long tMin = (long) Math.ceil((double) x0 / c);
        long y = y0 + k * tMin;

        // y가 자연수 및 제한 조건을 만족하는지 확인
        if (y <= 0 || y > 1_000_000_000) {
            return "IMPOSSIBLE";
        }
        return String.valueOf(y);
    }
}

class Result {
    long gcd, x, y;

    public Result(long gcd, long x, long y) {
        this.gcd = gcd;
        this.x = x;
        this.y = y;
    }
}