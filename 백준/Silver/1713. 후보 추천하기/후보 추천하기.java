import java.io.*;
import java.util.*;

public class Main {

	static Student[] students = new Student[101];
	static ArrayList<Student> frames = new ArrayList<>();
	static int N, K;
	static int timestamp = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			int num = Integer.parseInt(st.nextToken());

			// 처음 추천 받는 학생이라면
			if (students[num] == null) {
				students[num] = new Student(num, 0, timestamp);
			}

			if (students[num].cnt == 0) {
				// 사진틀이 부족하면 정렬하고 가장 앞의 학생 제거
				if (frames.size() >= N) {
					frames.sort(new RecommendComparator());
					Student removed = frames.remove(0);
					students[removed.num].cnt = 0; // 초기화
				}
				students[num].cnt = 1;
				students[num].timestamp = timestamp++;
				frames.add(students[num]);
			} else {
				students[num].cnt++;
			}
		}

		// 번호순 출력
		frames.sort(Comparator.comparingInt(s -> s.num));
		for (Student frame : frames) {
			System.out.print(frame.num + " ");
		}
	}

	static class RecommendComparator implements Comparator<Student> {
		@Override
		public int compare(Student s1, Student s2) {
			if (s1.cnt != s2.cnt) {
				return s1.cnt - s2.cnt; // 추천 수 오름차순
			}
			return s1.timestamp - s2.timestamp; // 시간 오름차순
		}
	}
}

class Student {
	int num;
	int cnt;
	int timestamp;

	public Student(int num, int cnt, int timestamp) {
		this.num = num;
		this.cnt = cnt;
		this.timestamp = timestamp;
	}
}
