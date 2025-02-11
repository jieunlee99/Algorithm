import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		String input = br.readLine().toUpperCase();
		int[] count = new int[26];
		
		// 알파벳 개수 체크
		for(int i=0; i<input.length(); i++) {
			count[input.charAt(i)-'A']++;
		}
		
		// 기본값
		int max = 0;
		char answer = '?';
		
		// max 값 체크 
		for(int i=0; i<26; i++) {
			if(max < count[i]) {
				max = count[i];
				answer = (char) (i+'A');
			} else if(max == count[i]) {
				answer = '?';
			}
		}
		
		System.out.println(answer);
	}
}
