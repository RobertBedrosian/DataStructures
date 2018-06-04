public class Main
{
    public static void main(String[] args)
    {
        Treap t = new Treap();
        t.insert('c',50);
        t.insert('a',40);
        t.insert('f',29);
        t.insert('g',35);
        t.insert('d',25);
        t.insert('e',24);
        t.insert('s',33);
        t.insert('z',21);
        t.insert('b',8);
        t.insert('q',16);
        t.insert('r',13);
        t.insert('t',9);
        Treap[] arr = t.split('g');
        System.out.println("First treap:");
        arr[0].preOrder(arr[0].root);
        System.out.println("Second treap:");
        arr[1].preOrder(arr[1].root);
        System.out.println();
        Treap t3 = t.merge(arr[0],arr[1]);
        t3.preOrder(t3.root);
    }
}