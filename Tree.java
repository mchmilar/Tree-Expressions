import java.util.NoSuchElementException;

public class Tree<T> {

	private class TreeNode {
		private Position<T> position;
		private TreeNode next;
		
		private TreeNode() {
			// Intentionally left empty
		}
		
		private TreeNode(TreeNode next, Position<T> position) {
			this.next = next;
			this.position = position;
		}
		
		private TreeNode(TreeNode nodeToCopy) {
			next = nodeToCopy.next;
			position = new Position<T>(nodeToCopy.getPosition());
		}
		
		private Position<T> getPosition() {
			return position;
		}
		
		
	}
	
	//
	// Begin Tree Class
	//
	private final int ROOT_ID = 1;
	private TreeNode head;
	private TreeIterator treeIterator = new TreeIterator();
	
		
	public Tree() {
		head = null;
	}
	
	public Tree(TreeNode root) {
		head = root;
	}
	
	
	//
	// Required Methods
	//
	
	// Returns position of the root
	public Position<T> root() {
		return findTreeNode(ROOT_ID).getPosition();		
	}
	
	// Returns the position of the parent of position p or null if p is the root
	public Position<T> parent(Position<T> p) {

		int parentId = p.getId() / 2;
		return findTreeNode(parentId).getPosition();
		
	}
	
	// Returns an iterable collection containing the children of position p
	// Returns null if no children
	public Position<T>[] children(Position<T> p) {
		
		if (isExternal(p))
			return null;
		
		Position<T>[] temp = new Position[numChildren(p)];
		
		if (numChildren(p) == 1 && right(p) == null) {
			temp[0] = left(p);
			return temp;
		} else if (numChildren(p) == 1){
			temp[0] = right(p);
			return temp;
		} else {
			temp[0] = left(p);
			temp[1] = right(p);
			return temp;
		}
		
	}
	
	// Returns the number of children of position p
	public int numChildren(Position<T> p) {
				
		if (isExternal(p))
			return 0;
		else if (left(p) == null || right(p) == null)
			return 1;
		else 
			return 2;
		
	}
	
	// Returns the position of the left child of p
	public Position<T> left(Position<T> p) {
		if (findTreeNode(p.getId() * 2) == null)
			return null;
		return findTreeNode(p.getId() * 2).getPosition();
	}
	
	// Returns the position of the right child of p
	public Position<T> right(Position<T> p) {
		if (findTreeNode(p.getId() * 2 + 1) == null)
			return null;
		return findTreeNode( (p.getId() * 2) + 1 ).getPosition();
	}
	
	// Returns the position of the siblings of p, returns null if p has no sibling or p is root
	public Position<T> siblings(Position<T> p) {
		if (isRoot(p))
			return null;
		// If p's id is even then its sibling's id is one greater than p
		else if (p.getId() % 2 == 0)
			return findTreeNode(p.getId() + 1).getPosition();
		// If p's id isn't even, then its odd and its sibling's id is one less than p
		else
			return findTreeNode(p.getId() - 1).getPosition();
	}
	
	// Returns true if position p has at least one child
	public boolean isInternal(Position<T> p) {
		return (left(p) != null || right(p) != null);
	}
	
	// Returns true if position p does not have any children
	public boolean isExternal(Position<T> p) {
		return (left(p) == null && right(p) == null);
	}
		
	// Returns true if position p is the root of the tree
	public boolean isRoot(Position<T> p) {
		return p.getId() == ROOT_ID;
	}
	
	// Returns the number of positions that are contained in the tree
	public int size() {
		int size = 0;
		while (treeIterator.hasNext()) {
			size++;
			treeIterator.next();
		}
		treeIterator.reset();
		return size;
	}
	
	// Returns true if the tree does not contain any positions
	public boolean isEmpty() {
		return head == null;
	}
	
	// Recursive method that returns the height of the tree
	public int height() {
		return height(root());
	}
	
	private int height(Position<T> root) {
		if (root == null)
			return 0;
		return Math.max(height(left(root)), height(right(root))) + 1;
	}
	
	// Recursive method that returns a count of the number of leaf nodes in a binary tree
	public int numleaf() {
		return numleaf(root());
	}
	
	private int numleaf(Position<T> root) {
		if (root == null)
			return 0;
		else if (isExternal(root))
			return 1;
		else 
			return numleaf(left(root)) + numleaf(right(root));
	}
	
	
	// Recursive method that returns a clone of a binary tree
	public Tree<T> clone() {
		return new Tree<T>(clone(head));
	}
	
	private TreeNode clone(TreeNode treeHead) {
		if (treeHead == null)
			return null;
		if (treeHead.next == null)
			return new TreeNode(treeHead);
		TreeNode temp = new TreeNode(treeHead);
		temp.next = clone(temp.next);
		return temp;
		
	}
	
	public String evaluate(Position<T> p) {
		if (isExternal(p))
			return (String)p.getElement();
		else {
			String operator = (String)p.getElement();
			if (operator == "!")
				return factorial(Double.parseDouble(evaluate(left(p))));
			else if (operator == "^")
				return Math.pow(Double.parseDouble(evaluate(left(p))), Double.parseDouble(evaluate(right(p)))) + "";
			else if (operator == "*")
				return Double.parseDouble(evaluate(left(p))) * Double.parseDouble(evaluate(right(p))) + "";
			else if (operator == "/")
				return Double.parseDouble(evaluate(left(p))) / Double.parseDouble(evaluate(right(p))) + "";
			else if (operator == "+")
				return Double.parseDouble(evaluate(left(p))) + Double.parseDouble(evaluate(right(p))) + "";
			else if (operator == "-")
				return Double.parseDouble(evaluate(left(p))) - Double.parseDouble(evaluate(right(p))) + "";
			else if (operator == "==")
				return (Double.parseDouble(evaluate(left(p))) == Double.parseDouble(evaluate(right(p)))) + "";
			else if (operator == "!=")
				return (Double.parseDouble(evaluate(left(p))) != Double.parseDouble(evaluate(right(p)))) + "";
			else if (operator == ">")
				return (Double.parseDouble(evaluate(left(p))) > Double.parseDouble(evaluate(right(p)))) + "";
			else if (operator == ">=")
				return (Double.parseDouble(evaluate(left(p))) >= Double.parseDouble(evaluate(right(p)))) + "";
			else if (operator == "<")
				return (Double.parseDouble(evaluate(left(p))) < Double.parseDouble(evaluate(right(p)))) + "";
			else
				return (Double.parseDouble(evaluate(left(p))) <= Double.parseDouble(evaluate(right(p)))) + "";
		}
	}
	
	
	
	private TreeNode findTreeNode(int id) {
		TreeNode temp = null;
		boolean nodeFound = false;
		treeIterator.reset();
		// Iteration goes in try block to catch NoSuchElementException
		try {
			while (!nodeFound) {
				temp = treeIterator.next();
				if (temp.getPosition().getId() == id)
					nodeFound = true;
				else temp = null;
			}
		} catch (NoSuchElementException e) {
		}
		
		treeIterator.reset();
		return temp;
	}
	
	
	
	
	
	//
	// Methods to create TreeNodes
	//
	
	public Position<T> assignRoot(T element) {
		head = new TreeNode(head, new Position<T>(ROOT_ID, element));
		return head.getPosition();
	}
	
	public Position<T> assignLeftChild(T element, Position<T> parentPosition) {
		head = new TreeNode(head, new Position<T>(parentPosition.getId() * 2, element));
		return head.getPosition();
	}
	
	public Position<T> assignRightChild(T element, Position<T> parentPosition) {
		head = new TreeNode(head, new Position<T>(parentPosition.getId() * 2 + 1, element));
		return head.getPosition();
	}
	
	
	private String factorial (double x) {
		if (x == Math.floor(x)) {
			double product = 1;
			for (int i = 2; i <= x; i++)
				product *= i;
			return String.valueOf(product);
		} else
			return String.valueOf(gamma(x));
	}
	
	
	// Stirling approximation for real gamma function
	// Used when factorial is called on a real number
	private double gamma(double x) {
		double stirlingSeries = 1 + (1/(12*x)) + 1/(288*Math.pow(x,2)) - 139/(51840*Math.pow(x,3)) - 571/(2488320*Math.pow(x,4));
		return Math.sqrt(2 * Math.PI * x) * Math.pow(x/Math.E,x) * stirlingSeries ;
		
	}
	
	private class TreeIterator {
		private TreeNode position;
		
		private TreeIterator() {
			position = head;
		}
		
		private void reset() {
			position = head;
		}
		
		private TreeNode next() {
			if (!hasNext())
				throw new NoSuchElementException();
			TreeNode toReturn = position;
			position = position.next;
			
			return toReturn;
		}
		
		private boolean hasNext() {
			return (position != null);
		}
	}
	
	
}
