
import java.util.*;
import java.io.*;

public class Main {
	
	static int M, N, K;
	static int[] nums;
	

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		M = Integer.parseInt(br.readLine());
		
		nums = new int[M];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<M; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
			N += nums[i];
		}
		
		K = Integer.parseInt(br.readLine());
		
		double result = 0;
		for(int i=0; i<M; i++) {
			double value = 1;
			for(int j=0; j<K; j++) {
				value *= ((double)(nums[i]-j)/(N-j));
			}
			result += value;
		}
		
		System.out.println(result);
	}

}
