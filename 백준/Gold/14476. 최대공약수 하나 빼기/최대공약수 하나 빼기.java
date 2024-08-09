import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[] nums, leftToRight, rightToLeft;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub

//		System.setIn(new FileInputStream("src/DAY05/P14476/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		nums = new int[N];
		leftToRight = new int[N];
		rightToLeft = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		leftToRight[0] = nums[0];
		for (int i = 1; i < N; i++) {
			leftToRight[i] = gcd(nums[i], leftToRight[i - 1]);
		}

		rightToLeft[N - 1] = nums[N - 1];
//		for(int i=1; i<N; i++) {
//			rightToLeft[i] = gcd(nums[N-i-1], rightToLeft[i-1]);
//		}

		for (int i = N - 2; i >= 0; i--) {
			rightToLeft[i] = gcd(nums[i], rightToLeft[i + 1]);
		}

//		System.out.println(Arrays.toString(leftToRight));
//		System.out.println(Arrays.toString(rightToLeft));

		int max = -1;
		int maxIndex = 0;
		for (int i = 0; i < N; i++) {
			int gcd = 0;
			if (i == 0) {
				gcd = rightToLeft[1];
			} else if (i == N - 1) {
				gcd = rightToLeft[N-2];
			} else {
				gcd = gcd(leftToRight[i-1], rightToLeft[i+1]);
			}
			
			if(nums[i]%gcd != 0 && max < gcd) {
				max = gcd;
				maxIndex = i;
			} 
			
		}

		if(max == -1) {
			System.out.println(-1);
		} else {
			System.out.println(max);
			System.out.println(nums[maxIndex]);
		}
	}

//	gcd(a,b)=gcd(b, a%b)
	static int gcd(int a, int b) {

		while (b != 0) {
			int r = a % b;
			a = b;
			b = r;
		}

		return a;
	}
}
