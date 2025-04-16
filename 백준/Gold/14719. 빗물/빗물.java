import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int[] height = new int[W];
		for(int i=0; i<W; i++) {
			height[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] left = new int[W];
		int[] right = new int[W];
		
		int max = Integer.MIN_VALUE;
		for(int i=0; i<W; i++) {
			max = Math.max(height[i], max);
			left[i] = max;
		}
		
		max = Integer.MIN_VALUE;
		for(int i=W-1; i>=0; i--) {
			max = Math.max(height[i], max);
			right[i] = max;
		}

		int sum = 0;
		for(int i=0; i<W; i++) {
			sum += Math.min(left[i]-height[i], right[i]-height[i]);
		}
		
		System.out.println(sum);
	}

}
