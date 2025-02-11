import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		String[] channels = new String[n];
		for (int i = 0; i < n; i++) {
			channels[i] = br.readLine();
		}

		StringBuilder sb = new StringBuilder();

		moveToTop(channels, "KBS1", sb, 0); // KBS1을 0번으로 이동
		moveToTop(channels, "KBS2", sb, 1); // KBS2를 1번으로 이동

		System.out.println(sb);
	}

	static void moveToTop(String[] arr, String target, StringBuilder sb, int targetIdx) {
		int idx = findIndex(arr, target);

		// 화살표를 target까지 이동
		for (int i = 0; i < idx; i++)
			sb.append("1");

		// 채널을 targetIdx까지 올리기 (swap 반복)
		for (int i = idx; i > targetIdx; i--) {
			swap(arr, i, i - 1);
			sb.append("4");
		}
	}

	static int findIndex(String[] arr, String target) {
		
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].equals(target))
				return i;
		}
		return -1;
	}

	static void swap(String[] arr, int i, int j) {
		String temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
