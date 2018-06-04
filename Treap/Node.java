public class Node
{
  int p;
  char data;
  Node parent;
  Node left;
  Node right;
  Node(char data, int priority)
  {
    this.data = data;
    this.p = priority;
  }
  public Node getChild()
  {
      if(this.numChildren() == 2)
      {
          if(this.left.p>this.right.p)
            return this.left;
          return this.right;
      }
      if(this.numChildren() == 1)
      {
          if(this.hasRight())
              return this.right;
          return this.left;
      }
      return null;
  }
  public boolean isRight()
  {
      if(this.parent!=null)
      {
        if(this.parent.right == this)
              return true;
      }
      return false;
  }
  public boolean isLeft()
  {
      if(this.parent!=null)
      {
          if(this.parent.left == this)
              return true;
      }
      return false;
  }
  public boolean hasSibling()
  {
      if(this.parent!=null)
      {
        if(this.isRight())
        {
            if(this.parent.left!=null)
                return true;
        }
        else
        {
            if(this.parent.right!=null)
                return true;
        }
      }
      return false;
  }
  public boolean hasRight()
  {
    if(this.right!=null)
      return true;
    return false;
  }
  public boolean hasLeft()
  {
    if(this.left!=null)
      return true;
    return false;
  }
  public Node getSibling()
  {
      if(this.hasSibling())
      {
          if(this.isRight())
              return this.parent.left;
          else
              return this.parent.right;
      }
      return null;
  }
  public String toString()
  {
      if(this.parent == null)
          return "" + this.data + "[" + this.p + "] is the root" + "\n";
      if(this.isRight())
        return "" + this.data + "[" + this.p + "] is right child of " 
      + this.parent.data + "\n";
      else if(this.isLeft())
        return "" + this.data + "[" + this.p + "] is left child of "
      + this.parent.data + "\n";
      return null;
  }
  public int numChildren()
  {
    if(this.left!=null && this.right!=null)
      return 2;
    if((this.left == null && this.right !=null) ||
    		(this.left!=null && this.right == null))
      return 1;
    return 0;
  }
}

