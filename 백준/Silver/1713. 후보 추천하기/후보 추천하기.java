import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
	
	static Person[] people = new Person[101];
	static ArrayList<Person> frames = new ArrayList<>();
	static int N, G;
	static int timestamp = 0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		G = sc.nextInt(); 
		
		for(int i=0; i<G; i++) {
			int num = sc.nextInt();
			
			if(people[num] == null) {
				people[num] = new Person(num, 0, timestamp++);
			}
			
			// 사진틀 안에 학생이 없을 때
			if(people[num].count == 0) {
				if(frames.size() >= N) {
					// 추천수 작고 타임스탬프 작은거 삭제
					// 둘다 내림차순으로 정렬한 뒤 가장 뒷순위인 학생 삭제
					Collections.sort(frames, new Comparator<Person>() {

						@Override
						public int compare(Person p1,Person p2) {
							// TODO Auto-generated method stub
							int cnt = p2.count - p1.count;
							if(cnt ==  0) {
								return p2.timestamp -p1.timestamp;
							}
							return cnt;
						}
					});
					
					// 삭제되는 학생 번호 저장
					int temp = frames.remove(N-1).num;
					people[temp].count = 0;
				}
				
				people[num].count += 1;
				people[num].timestamp = timestamp++;
				frames.add(people[num]);
			}
			// 사진틀 안에 학생이 있을 때
			else {
				people[num].count+= 1;
			}
			
//			for(int j=0; j<frames.size(); j++) {
//				System.out.print(frames.get(j).num+" ");
//			}
//			System.out.println();
		}
		
		Collections.sort(frames, Comparator.comparing(Person::getNum));
		for(int i=0; i<frames.size(); i++) {
			System.out.print(frames.get(i).num+" ");
		}
		System.out.println();
		
	}

}

class Person implements Comparable<Person>{

	int num; // 학생 번호
	int count; // 추천 수 
	int timestamp; // 추천받은 시간
	
	public Person(int num, int count, int timestamp) {
		this.num = num;
		this.count = count;
		this.timestamp = timestamp;
	}
	
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(int timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public int compareTo(Person p) {
		// TODO Auto-generated method stub
		return num-p.num;
	}
}