package tree;

public class TreeNode 
{
	protected String item;
	protected TreeNode leftChild;
	protected TreeNode rightChild;
	protected String animal;
	public TreeNode(String newItem, String animal)
	{
		item=newItem;
		leftChild=null;
		rightChild=null;
		this.animal=animal;
	}
	public TreeNode(String newItem, TreeNode left, TreeNode right, String animal)
	{
		item=newItem;
		leftChild=left;
		rightChild=right;
		this.animal=animal;
	}
	public TreeNode(String newItem, TreeNode left, TreeNode right)
	{
		item=newItem;
		leftChild=left;
		rightChild=right;
		this.animal=null;
	}
	public TreeNode(String newItem, TreeNode left, String animal)
	{
		item=newItem;
		leftChild=left;
		rightChild=null;
		this.animal=animal;
	}
	public void setRight(TreeNode root, TreeNode Rightchild)
	{
		root.rightChild=Rightchild;
	}
	public void setLeft(TreeNode root, TreeNode Leftchild)
	{
		root.leftChild=Leftchild;
	}


}
