import java.io.*;
import java.util.*;

public class Main {

	static int K, N;
	static int[] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		arr = new int[K];
		for (int i = 0; i < K; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(arr);
		
		System.out.print(binarySearch());

	}

	static long binarySearch() { 
		long start = 1, end = arr[K-1];
		
		while(start <= end) {
			long mid = (start+end)/2;
		
			long cnt = 0;
			for(int i=0; i<K; i++) {
				cnt += arr[i]/mid;
			}
			
			if(cnt >= N) {
				start = mid+1;
			} else {
				end = mid-1;
			}
		}
		
		return end;
	}
}
