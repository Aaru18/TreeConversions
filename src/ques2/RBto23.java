package ques2;

import java.util.*;
public class RBto23 {

	public static void main(String args[])
	{
		
	}
	
	void insertTo23(Node root,int value)
	{
		int diff = root.dataOne - value;
		
		//Node is a leaf
		if(((root.left == null) && (root.mid == null)) && (root.right==null))
		{
			if(root.dataTwo == 0)			// leaf has only one data
			{
				if(diff <= 0)				// new value is larger than the previous
				{
					root.dataTwo = value;
				}
				else						// new value is smaller
				{
					root.dataTwo = root.dataOne;
					root.dataOne = value;
				}
			}
			else							// leaf already has two items so splitUp
			{
				splitLeaf(root,value);
				if(root.parent != null)
				{
					root.parent.pushUp(root);
				}
				
			}
			
			return;
		}
		
		//Node not a leaf, continue traversal
		if(diff >0)							//new value smaller than dataOne
		{
			insertTo23(root.left,value);
		}
		// new value is lager or equal to the dataOne
		else if(root.dataTwo == 0)			// it is 2-node
		{
			insertTo23(root.right,value);
		}
		else								//it is 3-node
		{
			diff = root.dataTwo - value;
			
			if(diff >0)						//new value is smaller than dataTwo
			{
				insertTo23(root.mid,value);
				
			}
			else							//new value is lager than dataTwo
			{
				insertTo23(root.right,value);
			}
		}
		
	}
	
	void splitLeaf(Node root,int value)
	{
		int diff = root.dataOne - value;
		
		if(diff > 0)
		{
			root.left = Node23(value,root);
			root.right = Node23(root.dataTwo,root);
			root.dataTwo = 0;
		}
		else
		{
			diff = root.dataTwo - value;
			if(diff > 0)
			{
				root.left = Node23(root.dataOne,root);
				root.right = Node23(root.dataTwo,root);
				root.dataOne = value;
				root.dataTwo = 0;
			}
			else
			{
				root.left = Node23(root.dataOne,root);
				root.right = Node23(value,root);
				root.dataOne = root.dataTwo;
				root.dataTwo = 0;
			}
		}
		
	}
	
	Node Node23(int value, Node root)
	{
		Node n = new Node();
		n.parent = root;
		n.dataOne = value;
		return n;
		
	}
}
