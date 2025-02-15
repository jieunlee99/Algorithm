import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 
		 StringTokenizer st = new StringTokenizer(br.readLine());
		 
		 int N = Integer.parseInt(st.nextToken()); // 리스트에 있는 점수
		 int newScore = Integer.parseInt(st.nextToken()); // 새로운 점수
		 int P = Integer.parseInt(st.nextToken()); // 랭킹 리스트에 올라갈 수 있는 점수 개수
		 
		 if(N == 0) {
			 System.out.println(1);
			 return;
		 }
		 
		 // 입력 받기
		 int[] arr = new int[N];
		 st = new StringTokenizer(br.readLine());
		 for(int i=0; i<N; i++) {
			 arr[i] = Integer.parseInt(st.nextToken());
		 }
		 
		 // 랭킹 리스트의 가장 작은 순위보다 작다면 랭킹에 진입할 수 없음
		 if(N == P && newScore <= arr[arr.length-1]) {
			 System.out.println(-1);
		 } else {
			 // 랭킹 계산
			 int rank = 1;
			 for(int i=0; i<arr.length; i++) {
				 if(newScore < arr[i]) {
					 rank++;
				 } else {
					 break;
				 }
			 }
			 
			 System.out.println(rank);
		 }
	}
}