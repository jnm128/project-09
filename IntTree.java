package lab09;

// Chapter 17.2 - page 1035 (modified)
// Simple binary tree class that includes methods to construct a tree of ints, to print the data using an
// inorder traversal. The trees build have nodes numbered starting with 1 and numbered
// sequentially level by level with no gaps in the trees.
public class IntTree {
	private IntNode root;

	public IntTree(int[] arr){
		if (arr.length == 0)
			throw new IllegalArgumentException("empty array");
		root = builTreeArray(1, arr);
	}

	private IntNode builTreeArray(int n, int[] arr){
		if (n > arr.length){
			return null;
		}else{
			if (arr[n-1] != -1){
				return new IntNode(arr[n-1], builTreeArray(2*n, arr), builTreeArray(2*n+1, arr));
			}else{
				return null;
			}
		}
	}

	// pre: max >= 0 (throws IllegalArgumentException if not)
	// post: constructs a sequential tree with given number of nodes
	public IntTree(int max){
		if (max < 0){
			throw new IllegalArgumentException("max: " + max);
		}
		root = buildTree(1, max);
	}

	// post: returns a sequential tree with n as its root unless n is greater than max, in which case it
	// returns an empty tree
	private IntNode buildTree(int n, int max){
		if (n > max){
			return null;
		}else{
			return new IntNode(n, buildTree(2*n, max), buildTree(2*n+1, max));
		}
	}

	public String getInorder(){
		return getInorder(root);
	}

	private String getInorder(IntNode root){
		if (root != null){
			return (getInorder(root.left) + " " + root.data + " " + getInorder(root.right));
		}else{
			return "";
		}
	}

	// post: prints the tree contents using an inorder traversal
	public void printInorder(){
		System.out.print("inorder:");
		printInorder(root);
		System.out.println();
	}

	// post: prints in inorder the tree with given root
	private void printInorder(IntNode root){
		if (root != null){
			printInorder(root.left);
			System.out.print(" " + root.data);
			printInorder(root.right);
		}
	}


	// post: prints the tree contents, one per line, following an inorder traversal and using indentation to
	//indicate node depth; prints right to left so that it looks correct when the output is rotated.
	public void printSideways(){
		printSideways(root, 0);
	}

	// post: prints in reversed preorder the tree with given root, indenting each line to the given level
	private void printSideways(IntNode root, int level){
		if (root != null){
			printSideways(root.right, level+1);
			for (int i = 0; i < level; i++){
				System.out.print("       ");
			}
			System.out.println(root.data);
			printSideways(root.left, level+1);
		}
	}

	//==================================================

	// A binary tree is "lucky" if it contains at least 3 occurrences of some value. Write a method
	// called luckyTree that checks to see if the given value occurs at least three times. If the
	// tree is "lucky", return true; otherwise, return false.
	
	public boolean luckyTree(int value) {
		int occurrence= luckyTreeHelper(root,value);
		if (occurrence>=3) {
			return true;
		}
		return false;

	}
	private int luckyTreeHelper(IntNode root,int value) {
		int count=0;
		if(root==null) {
			return 0;
		}
		if(root.data==value) {
			count=1;
		}
		count=count+luckyTreeHelper(root.left,value)+luckyTreeHelper(root.right,value);
			return count;
			
		
	}

	// Write a method called perfectify that adds nodes until the binary tree is a perfect tree. A perfect
	// binary tree is one where all leaves are at the same level. Another way of thinking of it is that you
	// are adding dummy nodes to the tree until every path from the root to a leaf is the same length. A
	// perfect tree's shape is triangular and every branch node has exactly two children, and all of the
	// leaves are at the same level. Each new node you add to the tree should store the value -1.
	public void perfectify(){
		int height=getheight(root);
		root=perfectify(root,height);{
			
		}
	}
	private IntNode perfectify(IntNode root, int height) {
		if(root ==null) {
			root=new IntNode(-1);
		}
		if (height ==0) {
			return null;
		}
		else {
			root.left=perfectify(root.left,height-1);
			root.right=perfectify(root.right,height-1);
			return root;
		}
	}
	
	public int getheight() {
		return getheight(root);
		
	}
	public int getheight(IntNode root) {
		if(root==null) {
			return 0;
		}else {
			int k=getheight(root.left);
			int j=getheight(root.right);
			return Math.max(k,j)+1;
		}
	}


	// This method returns the depth of the tree as an integer.
	// We will use the same convention as in printLevel where the top root is at depth 1,
	// its children are at depth 2, and so on.
	// If the tree is empty, the method should return 0.
	public int getDepth(){
		return getDepthHelper(root);
	}
	private int getDepthHelper(IntNode root){
		if(root==null) {
			return 0;
		}
		else {
			int leftdepth=getDepthHelper(root.left);
			int rightdepth=getDepthHelper(root.right);
		
		 if (leftdepth > rightdepth) 
             return (leftdepth + 1); 
          else 
             return (rightdepth + 1);
	}}

	// Write a method called printLevel that accepts an integer parameter n and returns the values at level n
	// as a string, from left to right, one per line . We will use the convention that the overall root is at level 1, its
	// children are at level 2, and so on. If there are no values at the level, your method should return an
	// empty string. Your method should return an empty string if it is passed a value for a level that
	// is less than 1. See the instruction for example output.

	
	public String printLevel(int level) {
		return printLevelhelper(root,1,level);
		
	}

	private String printLevelhelper(IntNode root,int currlevel, int goalLevel) {
		if(root==null) {
			return "";
		}
		if(currlevel==goalLevel) {
			return root.data+"\n";
		}
		else
		return printLevelhelper(root.left,currlevel+1,goalLevel)+printLevelhelper(root.right,currlevel+1,goalLevel);
	    
		 }  
	 
	    
	    
		
	

	// Write a method called numEmpty that returns the number of empty branches in a tree. An empty tree is
	// considered to have one empty branch (the tree itself). For nonempty trees, your methods should count the
	// total number of empty branches among the nodes of the tree. A leaf node has two empty branches, a node
	// with one nonempty child has one empty branch, and a node with two nonempty children has no empty branches.
	// See the instruction for example output.
	public int numEmpty() {
		return numEmptyhelper(root);
	}
	private int numEmptyhelper(IntNode root){
		if(root==null) {
			return 1;
		}
		else {
			return numEmptyhelper(root.left) + numEmptyhelper(root.right);
			
		}
	
	}

	// write a toString method for a binary tree of integers. The method should return "empty" for an empty tree.
	// For a leaf node, it should return the data in the node as a string. For a branch node, it should return a
	// parenthesized String that has three elements separately by commas: the data at the root, a string
	// representation of the left subtree, and then a string representation of the right subtree.
	// See the instruction for example output.
	public String toString(){
		if(root==null) {
			return "";
		}
		else {
		return toStringHelper(root);
		}
	}
	private String toStringHelper(IntNode root) {
		 //if empty
		 if(root==null) {
			 return "empty";
		 }
		 //branch node
		 if (root.left != null && root.right != null) { 	         
			 return "(" + root.data + ", " + toStringHelper(root.left)+ ", " + toStringHelper(root.right) + ")";  
	     } 
		 //left  
		 else if (root.left != null && root.right == null) { 
	         return "(" + root.data + ", " + toStringHelper(root.left) + ", empty)";
	     } 
		 //right 
		 else if (root.left == null && root.right != null) { 
			 
	         return"(" + root.data + ", empty, " + toStringHelper(root.right) + ")";
	     }
		 //leaf 
		 else {
	         return "" + root.data;
	     }
	     
	     
	}

}
