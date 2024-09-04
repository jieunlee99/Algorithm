
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, M;

    static List<Point> houses = new ArrayList<>(); // 집 저장
    static List<Point> chickens = new ArrayList<>(); // 치킨집 저장

    static boolean[] selected; // M개의 치킨집을 고르기 위해 필요함

    static int minChickenDistance = Integer.MAX_VALUE; // 결과값 저장

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("src/P15686/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int value = Integer.parseInt(st.nextToken());
                if (value == 1) { // 집 저장
                    houses.add(new Point(i, j));
                } else if (value == 2) { // 치킨집 저장
                    chickens.add(new Point(i, j));
                }
            }
        }

        selected = new boolean[chickens.size()]; // 선택된 치킨집 저장

        backtracking(0, 0);
        System.out.println(minChickenDistance);

    }


    public static void backtracking(int start, int count) {
        // 목적지라면? => "도시 치킨 거리" 계산 후 최소값인지 확인
        if (count == M) {
            minChickenDistance = calculateMinChickenDistance();
            return;
        }

        // 순회
        for (int i = start; i < chickens.size(); i++) {
            selected[i] = true; // 체크인
            backtracking(i + 1, count + 1); // 간다
            selected[i] = false; // 체크아웃
        }
    }

    public static int calculateMinChickenDistance() {
        int cityChickenDistance = 0;
        for (Point house : houses) {
            int houseChickenDistance = Integer.MAX_VALUE;
            for (int i = 0; i < chickens.size(); i++) {
                if (selected[i]) {
                    Point chicken = chickens.get(i);
                    int distance = house.distance(chicken);
                    houseChickenDistance = Math.min(houseChickenDistance, distance);
                }
            }
            cityChickenDistance += houseChickenDistance;
        }
        return Math.min(minChickenDistance, cityChickenDistance);
    }
}

class Point {
    int y;
    int x;

    public Point(int y, int x) {
        this.y = y;
        this.x = x;
    }

    public int distance(Point other) {
        return Math.abs(this.x - other.x) + Math.abs(this.y - other.y);
    }
}