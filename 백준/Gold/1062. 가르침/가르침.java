import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

// 1. 문제 파악
// 2. 요약 : 입력, 출력, 제한 ...
// 3. 그림
// 4. 1~6단계 작성 후 그림과 비교
// 5. 코딩

public class Main {
	
	static int n, k; // 대문자로
	static boolean[] isVisited = new boolean[26];
	static String[] words;
	static int max = 0;
	static int cnt;

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt(); // 단어 개수
		k = sc.nextInt(); // 알고 있는 알파벳 개수
		
		sc.nextLine();
		
		words = new String[n];
		
		for(int i=0; i<n; i++) {
			words[i]=sc.nextLine();
		}
		
		for(int i=0;i<26;i++) {
			if(isVisited[i] == false) {
				dfs(i);
			}
		}
		
		System.out.println(max);
	}
	
	static int countWords() {
		int count = 0;
		
		for(int i=0; i<n; i++) {
			boolean isPossible = true;
			String word= words[i];
			for(int j=0; j<word.length(); j++) {
				if(isVisited[word.charAt(j)-'a']==false) {
					isPossible = false;
					break;
				}
			}
			if(isPossible== true) {
				count++;
			}
		}
		
		return count;
	}

	static void dfs(int depth) {
		
		// 1. 체크인
		isVisited[depth]=true;
		cnt++;
		
		// 2. 목적지인가?
		if(cnt == k) {
			//  읽을 수 있는 단어 개수 파악
			max = Math.max(countWords(), max);
			
		} else {
			// 3. 연결된 곳을 순회
			for(int next=depth+1; next<26; next++) {
				// 4. 갈 수 있는가?
				if(isVisited[next]==false) {
					// 5. 간다.
					dfs(next);
				}
			}
		}
		
		// 6. 체크아웃
		isVisited[depth]=false;
		cnt--;
	}
}