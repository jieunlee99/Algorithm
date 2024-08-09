import java.util.*;

public class Main {

	// 2 pointer (시간 복잡도 : n^2 -> n)

	// low가 움직이면 감소, high를 움직이면 증가
	// -> 원하는 값보다 작으면 high를 올리고 원하는 값보다 크면 low를 올린다.

	// sum>M low++
	// sum=M count++
	// sum<M high++

	// high가 범위 벗어나면 종료.
	// high가 범위 넘어가면 그때 값을 0으로 하면.. ? 아웃오브인덱스 일어나지 않음

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int M = sc.nextInt();

		int[] array = new int[10001];
        Arrays.fill(array, 0);
		for (int i = 0; i < N; i++) {
			array[i] = sc.nextInt();
		}

		// 2 pointer
		int high = 0;
		int low = 0;
		
		int sum = array[0]; 
		
		int cnt = 0; // 경우의 수
		
		while(high<N && low<N) {
//			System.out.println("low: "+low+", high: "+high+", sum: "+sum);
			
			if(sum > M) {
				sum-=array[low++];
			} else if (sum < M) {
				sum+=array[++high];
			} else { // sum == M
				cnt+=1;
				sum+=array[++high];
			}

		}
		
		System.out.println(cnt);
	}

}
