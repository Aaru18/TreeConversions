package ques2;


import java.util.*;
public class BSTtoAVL {
	
	
	
	public static void main(String[] args) {
		BSTtoAVL obj = new BSTtoAVL();
		System.out.println("Enter the values");
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine().trim();
		String split[] = input.split(" ");
		Node root = null;
		System.out.println("Creating a BST!!");
		for(int i=0;i<split.length;i++)
		{
			root = obj.insertToBST(root,Integer.valueOf(split[i]));
		}
		TreeTraversals tt = new TreeTraversals();
		ArrayList<Integer> preorder = new ArrayList<Integer>();
		preorder = tt.preOrder(root, preorder);
		for(Integer pre : preorder)
		{
			root = obj.insert(root, pre);
		}
//		System.out.println("Created BST....");
//		System.out.println("balance the root...");
//		root = obj.doBalancing(root);
//		System.out.println("Root balanced...");
//		System.out.println(root.data);
//		System.out.println("balance the left sub tree...");
//		root.left = obj.doBalancing(root.left);
//		System.out.println("Left SubTree Balanced...");
//		System.out.println("Balancing the right sub tree.....");
//		System.out.println(root.right.data);
//		root.right = obj.doBalancing(root.right);
//		System.out.println("Right Sub Tree Balanced...");
		System.out.print("BST converted to AVL with the root node....");
		System.out.println(root.data);
		sc.close();
		
	}
	
	private Node leftRotate(Node root){
        Node newRoot = root.right;
        root.right = root.right.left;
        newRoot.left = root;
        root.height = setHeight(root);
        root.size = setSize(root);
        newRoot.height = setHeight(newRoot);
        newRoot.size = setSize(newRoot);
        return newRoot;
    }
    
    private Node rightRotate(Node root){
        Node newRoot = root.left;
        root.left = root.left.right;
        newRoot.right = root;
        root.height = setHeight(root);
        root.size = setSize(root);
        newRoot.height = setHeight(newRoot);
        newRoot.size = setSize(newRoot);
        return newRoot;
    }

    private int setHeight(Node root){
        if(root == null){
            return 0;
        }
        return 1 + Math.max((root.left != null ? root.left.height : 0), (root.right != null ? root.right.height : 0));
    }
    
    private int height(Node root){
        if(root == null){
            return 0;
        }else {
            return root.height;
        }
    }
    
    /*
     * 
     * sets the 
     */
    private int setSize(Node root){
        if(root == null){
            return 0;
        }
        return 1 + Math.max((root.left != null ? root.left.size : 0), (root.right != null ? root.right.size : 0));
    }
    
    /*
     * 
     * Insertion function for AVL Trees
     */
    public Node insert(Node root, int data){
        if(root == null){
            return Node.newNode(data);
        }
        if(root.data <= data){
            root.right = insert(root.right,data);
        }
        else{
            root.left = insert(root.left,data);
        }
        int balance = balance(root.left, root.right);
        if(balance > 1){
            if(height(root.left.left) >= height(root.left.right)){
                root = rightRotate(root);
            }else{
                root.left = leftRotate(root.left);
                root = rightRotate(root);
            }
        }else if(balance < -1){
            if(height(root.right.right) >= height(root.right.left)){
                root = leftRotate(root);
            }else{
                root.right = rightRotate(root.right);
                root = leftRotate(root);
            }
        }
        else{
            root.height = setHeight(root);
            root.size = setSize(root);
        }
        return root;
    }
    
    
    /*
     * Check the balance of a node
     */
    private int balance(Node rootLeft, Node rootRight){
        return height(rootLeft) - height(rootRight);
    }
	
    /*
     * 
     * balances the node
     */
    Node doBalancing(Node root) {
    	int balance = balance(root.left, root.right);
    	System.out.println("Balance is "+balance);
        if(balance > 1){
            if(height(root.left.left) >= height(root.left.right)){
                root = rightRotate(root);
            }else{
                root.left = leftRotate(root.left);
                root = rightRotate(root);
            }
        }else if(balance < -1){
            if(height(root.right.right) >= height(root.right.left)){
                root = leftRotate(root);
            }else{
                root.right = rightRotate(root.right);
                root = leftRotate(root);
            }
        }
        else{
            root.height = setHeight(root);
            root.size = setSize(root);
        }
    	return root;
    }
    /*
     * 
     * This function creates a Binary Search Tree
     */
	Node insertToBST(Node root,int value) {
		if(root == null){
            return Node.newNode(value);
        }
        if(root.data <= value){
            root.right = insertToBST(root.right,value);
        }
        else{
            root.left = insertToBST(root.left,value);
        }
        root.height = setHeight(root);
        root.size = setSize(root);
		return root;
	}
}


