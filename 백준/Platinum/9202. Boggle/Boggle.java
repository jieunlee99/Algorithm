import java.io.*;
import java.util.*;

public class Main {
    static int W, B;
    static char[][] board;
    static boolean[][] visited;
    static TrieNode root;
    static StringBuilder sb;

    static int[] dx = {0, 0, -1, 1, -1, -1, 1, 1};
    static int[] dy = {-1, 1, 0, 0, -1, 1, -1, 1};

    static int score;
    static String maxWord;
    static int cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 사전 단어 개수
        W = Integer.parseInt(br.readLine());
        root = new TrieNode();

        for (int i = 0; i < W; i++) {
            insert(br.readLine());
        }

        br.readLine(); // 빈 줄

        // 보드 개수
        B = Integer.parseInt(br.readLine());
        StringBuilder result = new StringBuilder();

        for (int b = 0; b < B; b++) {
            board = new char[4][4];
            for (int i = 0; i < 4; i++) {
                String str = br.readLine();
                for (int j = 0; j < 4; j++) {
                    board[i][j] = str.charAt(j);
                }
            }

            // 초기화
            score = 0;
            cnt = 0;
            maxWord = "";
            visited = new boolean[4][4];
            sb = new StringBuilder();

            // DFS 탐색 시작
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    int idx = board[i][j] - 'A';
                    if (root.children[idx] != null) {
                        dfs(i, j, root.children[idx]);
                    }
                }
            }

            // Hit 초기화 (다음 보드 탐색 위해)
            root.clearHit();

            result.append(score).append(" ").append(maxWord).append(" ").append(cnt).append("\n");

            if (b < B - 1) br.readLine(); // 보드 사이 빈 줄 처리
        }

        System.out.print(result.toString());
    }

    // Trie 삽입
    private static void insert(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            int idx = word.charAt(i) - 'A';
            if (node.children[idx] == null) {
                node.children[idx] = new TrieNode();
            }
            node = node.children[idx];
        }
        node.isWord = true;
    }

    // DFS
    private static void dfs(int y, int x, TrieNode node) {
        visited[y][x] = true;
        sb.append(board[y][x]);

        if (node.isWord && !node.isHit) {
            score += getScore(sb.length());
            cnt++;
            if (maxWord.length() < sb.length() ||
                (maxWord.length() == sb.length() && sb.toString().compareTo(maxWord) < 0)) {
                maxWord = sb.toString();
            }
            node.isHit = true;
        }

        for (int d = 0; d < 8; d++) {
            int ny = y + dy[d];
            int nx = x + dx[d];
            if (ny < 0 || ny >= 4 || nx < 0 || nx >= 4) continue;
            if (visited[ny][nx]) continue;

            int idx = board[ny][nx] - 'A';
            if (node.children[idx] != null) {
                dfs(ny, nx, node.children[idx]);
            }
        }

        visited[y][x] = false;
        sb.deleteCharAt(sb.length() - 1);
    }

    // 점수 계산
    private static int getScore(int len) {
        if (len <= 2) return 0;
        if (len <= 4) return 1;
        if (len == 5) return 2;
        if (len == 6) return 3;
        if (len == 7) return 5;
        return 11;
    }
}

class TrieNode {
    TrieNode[] children = new TrieNode[26];
    boolean isWord;
    boolean isHit;

    void clearHit() {
        isHit = false;
        for (TrieNode child : children) {
            if (child != null) child.clearHit();
        }
    }
}
