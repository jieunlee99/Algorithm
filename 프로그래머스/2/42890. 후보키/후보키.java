import java.util.*;

class Solution {

    String[][] relation;
    int row, col;

    List<Integer> candidateKey = new ArrayList<>();

    public int solution(String[][] relation) {
        this.relation = relation;
        this.row = relation.length;
        this.col = relation[0].length;

        // 컬럼 1개 조합부터 col개 조합까지 순서대로 검사
        for (int size = 1; size <= col; size++) {
            dfs(0, 0, 0, size);
        }

        return candidateKey.size();
    }

    public void dfs(int start, int depth, int mask, int targetSize) {
        if (depth == targetSize) {
            if (!isMinimal(mask)) {
                return;
            }

            if (isUnique(mask)) {
                candidateKey.add(mask);
            }

            return;
        }

        for (int i = start; i < col; i++) {
            dfs(i + 1, depth + 1, mask | (1 << i), targetSize);
        }
    }

    public boolean isMinimal(int mask) {
        for (int key : candidateKey) {
            // 기존 후보키가 현재 조합에 포함되어 있으면 최소성 위반
            if ((key & mask) == key) {
                return false;
            }
        }

        return true;
    }

    public boolean isUnique(int mask) {
        Set<String> set = new HashSet<>();

        for (int i = 0; i < row; i++) {
            StringBuilder sb = new StringBuilder();

            for (int j = 0; j < col; j++) {
                if ((mask & (1 << j)) != 0) {
                    sb.append(relation[i][j]).append("|");
                }
            }

            if (!set.add(sb.toString())) {
                return false;
            }
        }

        return true;
    }
}