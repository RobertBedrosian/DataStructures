public class Treap
{
    Node root;
    Treap()
    {
        root = null;
    }
    Treap(Node n)
    {
        this.root = n;
        this.root.parent = null;
    }
  public Node insert(char d, int p)
  {
    Node curr = root;
    Node n = new Node(d,p);
    if(curr == null)
    {
      root = n;
      preOrder(root);
      System.out.println();
      return n;
    }
    while(true)
    {
      if(d>curr.data)
      {
        if(curr.right == null)
        {
          curr.right = n;
          n.parent = curr;
          break;
        }
        else
          curr = curr.right;
      }
      else  //if(d<=curr.data)
      {
        if(curr.left == null)
        {
          curr.left = n;
          n.parent = curr;
          break;
        }
        else
          curr = curr.left;
      }
    }
    bubbleUp(n);
    System.out.println("Insert " + n.data + ":");
    preOrder(root);
    System.out.println();
    return n;
  }
  public Node insert(Node n)
  {
    char d = n.data;
    Node curr = root;
    if(curr == null)
    {
      root = n;
      preOrder(root);
      System.out.println();
      return n;
    }
    while(true)
    {
      if(d>curr.data)
      {
        if(curr.right == null)
        {
          curr.right = n;
          n.parent = curr;
          break;
        }
        else
          curr = curr.right;
      }
      else  //if(d<=curr.data)
      {
        if(curr.left == null)
        {
          curr.left = n;
          n.parent = curr;
          break;
        }
        else
          curr = curr.left;
      }
    }
    bubbleUp(n);
    System.out.println("Insert " + n.data + ":");
    preOrder(root);
    System.out.println();
    return n;
  }
  public Node bubbleUp(Node n)
  {
      Node p = n.parent;
      while(p!=null && (p.p < n.p))
      {
          if(n.isRight())
              leftRotate(n);
          else if(n.isLeft())
              rightRotate(n);
          p = n.parent;
      }
      return n;
  }
  public Node trickleDown(Node n)
  {
      Node c = n.getChild();
      while(c!=null && c.p>n.p)
      {
          if(c.isRight())
              leftRotate(c);
          else if(c.isLeft())
              rightRotate(c);
          c = n.getChild();
      }
      return n;
  }
  public void preOrder(Node n)
  {
    if(n!=null)
    {
      System.out.print(n);
      if(n.left!=null)
        preOrder(n.left);
      if(n.right!=null)
        preOrder(n.right);
    }
  }
  public Node leftRotate(Node x)
  {
    Node p = x.parent; Node temp = p.parent;
    if(x.left!=null)
    {
      p.right = x.left;
      p.right.parent = p;
    }
    else //else needed to reassign right child of p from x to null
      p.right = null;
    if(temp != null)
    {
      if(temp.left == p)
      {
        temp.left = x;
      }
      if(temp.right == p)
      {
        temp.right = x;
      }
      x.parent = temp;
    }
    else //in case there's no temp, x becomes root
    {
      root = x;
      x.parent = null;
    }
    x.left = p;
    p.parent = x;
    return x;
  }
  public Node rightRotate(Node x)
  {
    Node p = x.parent;
    Node temp;
    if(p == null || p.parent == null)
        temp = null;
    else
        temp = p.parent;
    if(x.right != null)
    {
      p.left = x.right;
      p.left.parent = p;
    }
    else
      p.left = null;
    if(temp != null)
    {
      if(temp.left == p)
        temp.left = x;
      if(temp.right == p)
        temp.right = x;
    x.parent = temp;
    }
    else
    {
      root = x;
      x.parent = null;
    }
    x.right = p;
    p.parent = x;
    return x;
  }
  public void delete(Node n) 
  {
      boolean trickle = false;
      Node t = null;
    if(n.numChildren() == 2)
    {
      t = n;
      trickle = true;
      n = swap(n,getSuccessor(n));
    }
    if(n.numChildren() == 1)
    {
      Node p = n.parent;
      if(n.hasRight())
      {
        if(n.isRight())
        {
          p.right = n.right;
          p.right.parent = p;
        }
        if(n.isLeft())
        {
          p.left = n.right;
          p.left.parent = p;
        }
      }
      if(n.hasLeft())
      {
        if(n.isRight())
        {
          p.right = n.left;
          p.right.parent = p;
        }
        if(n.isLeft())
        {
          p.left = n.left;
          p.left.parent = p;
        }
      }
      if(n == root)
      {
          if(n.hasRight())
          {
              root = n.right;
              n.right.parent = null;
          }
          else
          {
              root = n.left;
              n.left.parent = null;
          }
      }
      if(trickle)
          trickleDown(t);
      if(root!=null)
      {
          System.out.println("Delete " + n.data + ":");
          preOrder(root);
      }
      System.out.println();
      return;
    }
    if(n.numChildren() == 0)
    {
      Node p = n.parent;
      if(n.isRight())
        p.right = null;
      if(n.isLeft())
        p.left = null;
      if(n == root)
          root = null;
      if(trickle)
          trickleDown(t);
      if(root!=null)
      {
          System.out.println("Delete " + n.data + ":");
          preOrder(root);
      }
      System.out.println();      
    }
  }
  public Node swap(Node a, Node b)
  {
    char temp = a.data;
    int temp1 = a.p;
    a.p = b.p;
    b.p = temp1;
    a.data = b.data;
    b.data = temp;
    return b;
  }
  public Node getSuccessor(Node n)
  {
    n = n.left;
    while(n.right!=null)
      n = n.right;
    return n;
  }
  public Treap[] split(char c)
  {
      this.insert(c, Integer.MAX_VALUE);
      System.out.println("Split:");
      Treap t1 = new Treap(root.left);
      Treap t2 = new Treap(root.right);
      Treap[] arr = {t1,t2};
      return arr;
  }
  public Treap merge(Treap t1, Treap t2)
  {
      Node n = new Node('z',Integer.MAX_VALUE);
      Treap t3 = new Treap(n);
      t3.insert(t1.root);
      t3.insert(t2.root);
      t3.delete(n);
      return t3;
  }
}