import java.io.*;
import java.util.*;

public class Main {

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		Node root = new Node('A', null, null);

		while (N-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			char value = st.nextToken().charAt(0);
			char left = st.nextToken().charAt(0);
			char right = st.nextToken().charAt(0);
			insert(root, value, left, right);
		}

		preOrder(root);
		sb.append("\n");
		inOrder(root);
		sb.append("\n");
		postOrder(root);
		System.out.println(sb);
	}

	static void insert(Node node, char value, char left, char right) {

		// 바로 삽입할 위치라면
		if (node.value == value) {
			node.left = (left == '.' ? null : new Node(left, null, null));
			node.right = (right == '.' ? null : new Node(right, null, null));
		}

		// 삽입할 위치를 찾으러 내려감
		else {

			if (node.left != null) {
				insert(node.left, value, left, right);
			}

			if (node.right != null) {
				insert(node.right, value, left, right);
			}
		}
	}

    // 전위
	static void preOrder(Node node) {
		if (node == null) {
			return;
		}
		sb.append(node.value);
		preOrder(node.left);
		preOrder(node.right);
	}

    // 중위
	static void inOrder(Node node) {
		if (node == null) {
			return;
		}
		inOrder(node.left);
		sb.append(node.value);
		inOrder(node.right);
	}

    // 후위
	static void postOrder(Node node) {
		if (node == null) {
			return;
		}
		postOrder(node.left);
		postOrder(node.right);
		sb.append(node.value);
	}

	static class Node {
		char value;
		Node left, right;

		public Node(char value, Node left, Node right) {
			this.value = value;
			this.left = left;
			this.right = right;
		}
	}
}
