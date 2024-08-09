
//
//import java.io.BufferedReader;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.LinkedList;
//import java.util.Queue;
//import java.util.StringTokenizer;
//
//// 섬 -> bfs
//// 다리 -> 
//
//public class Main {
//
//	static int N, M;
//	static int[][] map;
//	static boolean[][] visited;
//
//	static int num = 1;
//
//	static int[] MY = { 0, 0, -1, 1 };
//	static int[] MX = { -1, 1, 0, 0 };
//
//	static ArrayList<Edge> edges;
//	static Queue<Point> queue;
//
//	public static void main(String[] args) throws IOException {
//		// TODO Auto-generated method stub
//		System.setIn(new FileInputStream("src/DAY08/P17472/input.txt"));
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//		StringTokenizer st = new StringTokenizer(br.readLine());
//		N = Integer.parseInt(st.nextToken());
//		M = Integer.parseInt(st.nextToken());
//
//		map = new int[N][M];
//		visited = new boolean[N][M];
//
//		queue = new LinkedList<>();
//
//		for (int n = 0; n < N; n++) {
//			st = new StringTokenizer(br.readLine());
//			for (int m = 0; m < M; m++) {
//				// 0은 바다, 1은 땅을 의미한다.
//				map[n][m] = Integer.parseInt(st.nextToken());
////				if(map[n][m] == 1) {
////					queue.offer(new Point(n, m));
////				}
//			}
//		}
//
//		queue.offer(new Point(0, 0));
//
//		while (!queue.isEmpty()) {
//			// 큐에서 꺼냄
//			Point p = queue.poll();
//
//			// 목적지인가?
////			if(dp[p.y][p.x] != 0) {
////				num++;
////			} 
//
//			// 순회
//			for (int i = 0; i < 4; i++) {
//				int ty = p.y + MY[i];
//				int tx = p.x + MX[i];
//
//				// 갈 수 있는가? - 범위, 방문 false, 1
//				if (0 <= ty && ty < N && 0 <= tx && tx < M) {
//					if (dp[ty][tx] == 0 && map[ty][tx] == 1) {
//						queue.offer(new Point(ty, tx));
//						System.out.println(ty + " " + tx);
//						dp[ty][tx] = num;
//					}
//				}
//			}
//
//			for (int i = 0; i < N; i++)
//				System.out.println(Arrays.toString(dp[i]));
//		}
//	}
//
//}
//
//class Point {
//	int y;
//	int x;
//	int type;
//
//	public Point(int y, int x) {
//		this.y = y;
//		this.x = x;
//	}
//}
//
//class Edge {
//	int from, to, cost;
//
//	public int getFrom() {
//		return from;
//	}
//
//	public int getTo() {
//		return to;
//	}
//
//	public int getCost() {
//		return cost;
//	}
//
//	public Edge(int from, int to, int cost) {
//		super();
//		this.from = from;
//		this.to = to;
//		this.cost = cost;
//	}
//
//	@Override
//	public String toString() {
//		return "Edge [from=" + from + ", to=" + to + ", cost=" + cost + "]";
//	}
//}

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// Gold 1
public class Main {
    static int N, M;
    static int[][] map;
    
    static final int[] dx = { 0, 0, -1, 1};
    static final int[] dy = { 1, -1, 0, 0 };
    static boolean[][] visited;

    static class Node{
        int x;
        int y;
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    // 섬의 개수: max 6개
    static ArrayList<Node>[] islands = new ArrayList[7];

    static class Bridge implements Comparable<Bridge> {
        Node start;
        Node end;

        int from;
        int to;
        int length;

        public Bridge(int from, int to, int length){
            this.from = from;
            this.to = to;
            this.length = length;
        }

        public Bridge(Node start, Node end, int from, int to, int length){
            this.start = start;
            this.end = end;

            this.from = from;
            this.to = to;
            this.length = length;
        }

        // ascending order by length
        @Override
        public int compareTo(Bridge o) {
            return this.length - o.length;
        }

        @Override
        public String toString() {
            return "Bridge [" +
                "start.x=" + start.x + 
                ", start.y=" + start.y +
                ", end.x=" + end.x + 
                ", end.y=" + end.y +
                ", from=" + from + ", to=" + to + ", length=" + length
                    + "]";
        }
    }
    static ArrayList<Bridge> bridges = new ArrayList<>();

    static int[] parent;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];

        for(int n=0; n<N; n++){
            st = new StringTokenizer(br.readLine());
            for(int m=0; m<M; m++){
                map[n][m] = Integer.parseInt(st.nextToken());
            }
        }
        
        // 전체 칸에 대해 bfs를 돌려서 map에 섬번호 붙이기
        int islandCount = findIslandsByBfs();

        // print map
        /* System.out.println("print map &islandCount : " + islandCount);
        for(int n=0; n<N; n++){
            for(int m=0; m<M; m++){
                System.out.print(map[n][m] +" ");
            }
            System.out.println();
        } */
        
        // islands에서 멤버 Node 들을 꺼내서 섬들을 연결할 다리 구하기
        findBridges(islandCount);
        
        // print bridges
        /* System.out.println("print bridges &bridgesCount : " + bridges.size());
        Collections.sort(bridges);
        for (Bridge bridge : bridges) {
            System.out.println(bridge.toString());
        } */

        initUnionFind(islandCount);
        System.out.println(kruscal(islandCount));
    }

    static int findIslandsByBfs(){
        int islandNo = 1;
        for(int n=0; n<N; n++){
            for(int m=0; m<M; m++){
                if(visited[n][m] || map[n][m] == 0){
                    continue;
                }

                islands[islandNo] = new ArrayList<>();
                bfs(n, m, islandNo);
                islandNo++;
            }
        }

        // 섬의 수 리턴
        return islandNo-1;
    }

    static void bfs(int x, int y, int islandNo){
        ArrayDeque<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(x, y));
        visited[x][y] = true;

        while(!queue.isEmpty()){
            Node current = queue.poll();

            map[current.x][current.y] = islandNo;
            islands[islandNo].add(new Node(current.x, current.y));

            for(int i=0; i<4; i++){
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];

                if(nx < 0 || ny < 0 || nx >= N || ny >= M){
                    continue;
                }

                if(visited[nx][ny] || map[nx][ny] == 0){
                    continue;
                }

                visited[nx][ny] = true;
                queue.offer(new Node(nx, ny));
            }
        }
    }

    static void findBridges(int islandCount){
        // 각 번호의 섬의 모든 Node 에서 상하좌우로 끝까지 탐색해서 다른 번호의 섬을 만나면 다리
        for(int islandNo=1; islandNo<=islandCount; islandNo++){

            for (Node current : islands[islandNo]) {
                // 상하좌우
                for(int i=0; i<4; i++){
                    int bridgeLength = 0;
                    int nx = current.x + dx[i];
                    int ny = current.y + dy[i];

                    while(true){
                        if(nx < 0 || ny < 0 || nx >= N || ny >= M){
                            break;
                        }

                        // 같은 섬
                        if(map[nx][ny] == islandNo){
                            break;
                        }

                        // 다른 섬에 도착
                        if(map[nx][ny] > 0){
                            // + 다리 길이가 2 이상일 경우
                            if(bridgeLength >= 2){
                                // 출발섬 번호, 도착섬 번호, 다리 길이
                                // bridges.add(new Bridge(islandNo, map[nx][ny], bridgeLength));
                                bridges.add(
                                    new Bridge(
                                        new Node(current.x, current.y),
                                        new Node(nx, ny),
                                        islandNo, map[nx][ny], bridgeLength
                                    )
                                );
                            }
                            // + 다리 길이가 2가 안되면 버림
                            break;
                        }

                        // 다음 칸으로 이동 가능할 경우 이동
                        nx += dx[i];
                        ny += dy[i];
                        bridgeLength++;
                    }
                }
            }
        }
    }

    static int kruscal(int islandCount){
        int mstCost = 0;
        int selectedEdgeCount = 0;

        Collections.sort(bridges);

        for(int i=0; i<bridges.size(); i++){
            Bridge currentBridge = bridges.get(i);

            // from과 to 정점이 서로 다른 트리(=서로 다른 subset에 속함)일때만 연결
            if(find(currentBridge.from) != find(currentBridge.to)){
                mstCost += currentBridge.length;
                selectedEdgeCount++;

                /* System.out.print("from:" + find(currentBridge.from));
                System.out.print(", to:" + find(currentBridge.to));
                System.out.println(); */

                // 같은 subset으로 union
                union(currentBridge.from, currentBridge.to);

                // print parent and selected bridge
                /* System.out.println("selected Bridge: " + currentBridge.toString());
                for(int j=1; j<parent.length; j++){
                    System.out.print(parent[j] + " ");
                }
                System.out.println(); */
            }

            if(selectedEdgeCount == islandCount-1){
                return mstCost;
            }
        }

        // MST 만들기 실패했을 경우(연결 불가능한 정점이 존재)
        return -1;
    }

    // Union-Find
    static void initUnionFind(int islandCount){
        parent = new int[islandCount+1];

        // init parent
        for(int i=1; i<islandCount+1; i++){
            parent[i] = i;
        }
    }

    static void union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);

        if(aRoot != bRoot){
            parent[bRoot] = aRoot;
        }
    }

    static int find(int a){
        if(parent[a] == a){
            return a;
        }
        return parent[a] = find(parent[a]);
    }
}

