import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		char game = st.nextToken().charAt(0);

		Set<String> set = new HashSet<>();
		for (int n = 0; n < N; n++) {
			set.add(br.readLine());
		}
		
		if(game == 'Y') {
			System.out.println(set.size());
		} else if(game=='F') {
			System.out.println(set.size()/2);
		} else if(game=='O' ) {
			System.out.println(set.size()/3);
		}
	}

}