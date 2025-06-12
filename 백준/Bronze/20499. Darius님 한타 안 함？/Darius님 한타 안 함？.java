import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String S = br.readLine();
		//split은 지정해준 문자를 기준으로 배열에 나눠서 저장하는 것이다.
		//K/D/A가 입력되고 /기준으로 배열에 나눠 저장하는 것이기 떄문에 배열에는
		//string 배열에 {K, D, A}가 들어간다.
		String[] arr = S.split("/");
		int K = Integer.parseInt(arr[0]);
		int D = Integer.parseInt(arr[1]);
		int A = Integer.parseInt(arr[2]);
		
		if((K + A) < D || D == 0) {
			System.out.println("hasu");
		}else {
			System.out.println("gosu");
		}
	}

}