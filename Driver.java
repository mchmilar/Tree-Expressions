
public class Driver {

	public static void main(String[] args) {
		
		
		
		
		// equation:  (3*2) + (4/1)
		Tree<String> tree1 = new Tree<String>();
		Position<String> a = tree1.assignRoot("+");
		Position<String> b = tree1.assignLeftChild("*", a);
		Position<String> c = tree1.assignRightChild("/", a);
		Position<String> d = tree1.assignLeftChild("3", b);
		Position<String> e = tree1.assignRightChild("2", b);
		Position<String> f = tree1.assignLeftChild("4", c);
		Position<String> g = tree1.assignRightChild("1", c);
		
		// root()
		System.out.println("Tree root: " + tree1.root());
		
		//parent() and right()
		System.out.println("Parent of root's right child: " + tree1.parent(tree1.right(tree1.root())));
		
		//children() and numChildren()
		Position<String> children[] = new Position[tree1.numChildren(b)];
		System.out.print("The Children of " + b + " are: ");
		for (int i = 0; i < children.length; i++) {
			System.out.print(children[i]);
		}
		System.out.println();
		
		//siblings
		System.out.println("The sibling of " + e + ": " + tree1.siblings(e));
		
		//isInternal()
		System.out.println("isInternal(root()): " + tree1.isInternal(tree1.root()));
		System.out.println("isInternal(leaf): " + tree1.isInternal(g));
		
		//isExternal()
		System.out.println("isExternal(root()): " + tree1.isExternal(tree1.root()));
		System.out.println("isExternal(leaf): " + tree1.isExternal(g));
		
		//isRoot()
		System.out.println("isRoot(root()): " + tree1.isRoot(tree1.root()));
		System.out.println("isRoot(leaf): " + tree1.isRoot(g));
				
		//left()
		System.out.println("Left Child of the root: " + tree1.left(a));
		
		//size()
		System.out.println("Tree Size: " + tree1.size());
		
		//isEmpty()
		System.out.println("tree1.isEmpty(): " + tree1.isEmpty());
		Tree<String> emptyTree = new Tree<String>();
		System.out.println("emptyTree.isEmpty(): " + emptyTree.isEmpty());
		
		//height()
		System.out.println("Tree height: " + tree1.height());
		
		//numLeaf()
		System.out.println("tree1.numLeaf(): " + tree1.numleaf());
		
		//inorder
		System.out.println("Equation in tree evaluates to: " + tree1.evaluate(tree1.root()));
		
		//clone()
		Tree<String> clonedTree = tree1.clone();
		System.out.println("evaluation of cloned tree: " + clonedTree.evaluate(clonedTree.root()));
		
		//equation (3! * 2) + (4 / 1)
		Tree<String> tree2 = new Tree<String>();
		Position<String> h = tree2.assignRoot("+");
		Position<String> i = tree2.assignLeftChild("*", h);
		Position<String> j = tree2.assignRightChild("/", h);
		Position<String> k = tree2.assignLeftChild("!", i);
		Position<String> l = tree2.assignRightChild("2", i);
		Position<String> m = tree2.assignLeftChild("4", j);
		Position<String> n = tree2.assignRightChild("1", j);
		Position<String> o = tree2.assignLeftChild("3", k);
		
		System.out.println("tree2 = " + tree2.evaluate(tree2.root()));
		
		/*
		 * This is a more complex equation but the above two trees must be commented out first 
		 * 
		 * // 2*(5 +1)- 3 * 2 == 6^0 + 5 - !4
		// you can change position j from 3 to 15 to make the equation true
		Tree<String> tree3 = new Tree<String>();
		Position<String> a = tree3.assignRoot("==");
		Position<String> b = tree3.assignLeftChild("-", a);
		Position<String> c = tree3.assignRightChild("-", a);
		Position<String> d = tree3.assignLeftChild("*", b);
		Position<String> e = tree3.assignRightChild("*", b);
		Position<String> f = tree3.assignLeftChild("+", c);
		Position<String> g = tree3.assignRightChild("!", c);
		Position<String> h = tree3.assignLeftChild("2", d);
		Position<String> i = tree3.assignRightChild("+", d);
		Position<String> j = tree3.assignLeftChild("3", e);
		Position<String> k = tree3.assignRightChild("2", e);
		Position<String> l = tree3.assignLeftChild("^", f);
		Position<String> m = tree3.assignRightChild("5", f);
		Position<String> n = tree3.assignLeftChild("4", g);
		Position<String> o = tree3.assignLeftChild("5", i);
		Position<String> p = tree3.assignRightChild("1", i);
		Position<String> q = tree3.assignLeftChild("6", l);
		Position<String> r = tree3.assignRightChild("0", l);
		
		System.out.println("tree3 = " + tree3.evaluate(tree3.root()));*/
	}
}
