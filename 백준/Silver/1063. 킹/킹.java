import java.io.*;
import java.util.*;

public class Main {

	static class Pos {
		int row; // 1~8
		int col; // 0~7 (A~H)

		public Pos(int row, char col) {
			this.row = row;
			this.col = col - 'A';
		}

		public void move(int dr, int dc) {
			this.row += dr;
			this.col += dc;
		}

		public boolean isInRange() {
			return row >= 1 && row <= 8 && col >= 0 && col <= 7;
		}

		public String toString() {
			return (char) (col + 'A') + Integer.toString(row);
		}
	}

	static Map<String, int[]> moveMap = new HashMap<>();

	static {
		moveMap.put("R", new int[] { 0, 1 });
		moveMap.put("L", new int[] { 0, -1 });
		moveMap.put("B", new int[] { -1, 0 });
		moveMap.put("T", new int[] { 1, 0 });
		moveMap.put("RT", new int[] { 1, 1 });
		moveMap.put("LT", new int[] { 1, -1 });
		moveMap.put("RB", new int[] { -1, 1 });
		moveMap.put("LB", new int[] { -1, -1 });
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		String strKing = st.nextToken();
		String strStone = st.nextToken();
		int N = Integer.parseInt(st.nextToken());

		Pos king = new Pos(strKing.charAt(1) - '0', strKing.charAt(0));
		Pos stone = new Pos(strStone.charAt(1) - '0', strStone.charAt(0));

		for (int i = 0; i < N; i++) {
			String cmd = br.readLine();
			int[] move = moveMap.get(cmd);

			int nextKingRow = king.row + move[0];
			int nextKingCol = king.col + move[1];

			// 킹 이동 시도
			if (nextKingRow >= 1 && nextKingRow <= 8 && nextKingCol >= 0 && nextKingCol <= 7) {
				// 돌과 충돌하는 경우
				if (stone.row == nextKingRow && stone.col == nextKingCol) {
					int nextStoneRow = stone.row + move[0];
					int nextStoneCol = stone.col + move[1];
					if (nextStoneRow >= 1 && nextStoneRow <= 8 && nextStoneCol >= 0 && nextStoneCol <= 7) {
						stone.move(move[0], move[1]);
						king.move(move[0], move[1]);
					}
				} else {
					king.move(move[0], move[1]);
				}
			}
		}

		System.out.println(king);
		System.out.println(stone);
	}
}
