import java.io.*;
import java.util.*;

public class Main {

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		Node root = new Node(Integer.parseInt(br.readLine()));

		while (true) {
			String input = br.readLine();
			if (input == null || input.isEmpty()) {
				break;
			}
			root.insert(Integer.parseInt(input));
		}

		postOrder(root);

		System.out.print(sb);
	}

	static void postOrder(Node current) {
		if (current == null) {
			return;
		}
		postOrder(current.left);
		postOrder(current.right);
		sb.append(current.value).append("\n");
	}

	static class Node {
		int value;
		Node left, right;

		public Node(int value) {
			this.value = value;
		}

		void insert(int value) {
			if (value < this.value) {
				if (this.left == null) {
					this.left = new Node(value);
				} else {
					this.left.insert(value);
				}
			} else { // value >= this.value
				if (this.right == null) {
					this.right = new Node(value);
				} else {
					this.right.insert(value);
				}
			}
		}
	}

}
