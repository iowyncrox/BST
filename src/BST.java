import java.util.Iterator;

public class BST<T extends Comparable<T>> implements Iterable<T> {

	private Node<T> root;

	public BST() {
		root = null;
	}

	private int compare(T x, T y) {
		return x.compareTo(y);
	}

	private class Node<T> {
		private T data;
		private Node<T> left, right;

		public Node(T data, Node<T> left, Node<T> right) {
			this.left = left;
			this.right = right;
			this.data = data;
		}

		public Node(T data) {
			this(data, null, null);
		}

		@Override
		public String toString() {
			return data.toString();
		}
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	/*****************************************************
	 *
	 * INSERT
	 *
	 ******************************************************/
	public void insert(T data) {
		root = insert(data, root);
	}

	private Node<T> insert(T data, Node<T> currentPointer) {
		if (currentPointer == null)
			return new Node<T>(data);

		if (compare(data, currentPointer.data) == 0)
			return currentPointer;

		if (compare(data, currentPointer.data) < 0)
			currentPointer.left = insert(data, currentPointer.left);
		else
			currentPointer.right = insert(data, currentPointer.right);

		return currentPointer;
	}

	/*****************************************************
	 *
	 * SEARCH
	 *
	 ******************************************************/
	public boolean search(T dataToSearch) {
		return search(dataToSearch, root);
	}

	private boolean search(T dataToSearch, Node<T> currentPointer) {
		if (currentPointer == null)
			return false;
		else if (compare(dataToSearch, currentPointer.data) == 0)
			return true;
		else if (compare(dataToSearch, currentPointer.data) < 0)
			return search(dataToSearch, currentPointer.left);
		else
			return search(dataToSearch, currentPointer.right);
	}

	/*****************************************************
	 *
	 * DELETE
	 *
	 ******************************************************/

	public void delete(T dataToDelete) {
		root = delete(dataToDelete, root);
	}

	private Node<T> delete(T toDelete, Node<T> p) {
		if (p == null)
			throw new RuntimeException("cannot delete.");
		else if (compare(toDelete, p.data) < 0)
			p.left = delete(toDelete, p.left);
		else if (compare(toDelete, p.data) > 0)
			p.right = delete(toDelete, p.right);
		else {
			if (p.left == null)
				return p.right;
			else if (p.right == null)
				return p.left;
			else {
				// get data from the rightmost node in the left subtree
				p.data = retrieveData(p.left);
				// delete the rightmost node in the left subtree
				p.left = delete(p.data, p.left);
			}
		}
		return p;
	}

	private T retrieveData(Node<T> p) {
		while (p.right != null)
			p = p.right;

		return p.data;
	}

	/*************************************************
	 *
	 * toString
	 *
	 **************************************************/

	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (T data : this)
			sb.append(data.toString());

		return sb.toString();
	}

	/*************************************************
	 *
	 * TRAVERSAL
	 *
	 **************************************************/

	public void preOrderTraversal() {
		preOrderHelper(root);
	}

	private void preOrderHelper(Node r) {
		if (r != null) {
			System.out.print(r + " ");
			preOrderHelper(r.left);
			preOrderHelper(r.right);
		}
	}

	public void inOrderTraversal() {
		inOrderHelper(root);
	}

	private void inOrderHelper(Node r) {
		if (r != null) {
			inOrderHelper(r.left);
			System.out.print(r + " ");
			inOrderHelper(r.right);
		}
	}

	public void postOrderTraversal() {
		postOrderHelper(root);
	}

	private void postOrderHelper(Node r) {
		if (r != null) {
			postOrderHelper(r.left);
			postOrderHelper(r.right);
			System.out.print(r + " ");
		}
	}

	public static void main(String[] args) {
		Integer[] a = { 7, 1, 0, 3, 2, 5, 4, 6, 9, 8, 10 };
		BST<Integer> bst = new BST<Integer>();
		for (Integer n : a)
			bst.insert(n);

		bst.preOrderTraversal();
		System.out.println();

		bst.postOrderTraversal();
		System.out.println();

		bst.inOrderTraversal();
		System.out.println();
	}

}