import java.util.Scanner;   
class LeftHeapNode
{
    int element, sValue;     
    LeftHeapNode left, right;             
 
    public LeftHeapNode(int ele)
    {
        this(ele, null, null);
    }
    public LeftHeapNode(int ele, LeftHeapNode left, LeftHeapNode right)
    {
        this.element = ele;
        this.left = left;
        this.right = right;
        this.sValue = 0;
    }    
}
class LeftistHeap
{
    public LeftHeapNode root; 
    public LeftistHeap() 
    {
        root = null;
    }
    public boolean isEmpty() 
    {
        return root == null;
    } 
    public void clear( )
    {
        root = null;
    }
    public void insert(int x )
    {
        root = merge(new LeftHeapNode( x ), root);
    }
    public void merge(LeftistHeap rhs)
    {
        if (this == rhs)    
            return;
        root = merge(root, rhs.root);
        rhs.root = null;
    }
   private LeftHeapNode merge(LeftHeapNode x, LeftHeapNode y)
    {
        if (x == null)
            return y;
        if (y == null)
            return x;
        if (x.element > y.element)
        {
            LeftHeapNode temp = x;
            x = y;
            y = temp;
        }
 
        x.right = merge(x.right, y);
 
          if(x.left == null) 
          {
            x.left = x.right;
            x.right = null;         
        } 
        else 
        {
            if(x.left.sValue < x.right.sValue) 
            {
                LeftHeapNode temp = x.left;
                  x.left = x.right;
                  x.right = temp;
            }
            x.sValue = x.right.sValue + 1;
        }        
        return x;
    }
 
    public int deleteMin( )
    {
        if (isEmpty() )
            return -1;
        int minItem = root.element;
        root = merge(root.left, root.right);
        return minItem;
    }
    public void printTree(LeftHeapNode currPtr, String indent, boolean last) {
           if (currPtr != null) {
              System.out.print(indent);
           if (last) {
              System.out.print("R----");
               indent += "   ";
      }    else {
               System.out.print("L----");
               indent += "|  ";
        }
      System.out.println(currPtr.element);
      printTree(currPtr.left, indent, false);
      printTree(currPtr.right, indent, true);
    }
  }    
}
 
public class leftistheaptree
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Leftist Heap Implementation");        
        LeftistHeap lh = new LeftistHeap();
 
        char ch;
        do    
        {
            System.out.println("\nLeftist Heap Operations");
            System.out.println("1. Insert ");
            System.out.println("2. Delete min");
            System.out.println("3. Check empty");            
            System.out.println("4. Clear");
            System.out.println("5. Print Tree");
            System.out.println("Enter your choice :: ");
 
            int choice = scan.nextInt();            
            switch (choice)
            {
            case 1 : 
                System.out.println("Enter integer element to insert");
                lh.insert( scan.nextInt() );                                    
                break;                          
            case 2 : 
                lh.deleteMin();
                System.out.println("The Tree After Delete Min");
                lh.printTree(lh.root,"",true);  
                break;                         
            case 3 : 
                System.out.println("Empty status = "+ lh.isEmpty());
                break;   
            case 4 : 
                lh.clear();
                break;
            case 5:
                lh.printTree(lh.root,"",true);
                break;
            default : 
                System.out.println("Wrong Entry \n ");
                break;   
            } 
            System.out.print("Do you want to continue (Type y or n)::   ");
            ch = scan.next().charAt(0);                        
        } while (ch == 'Y'|| ch == 'y');  
    }
}