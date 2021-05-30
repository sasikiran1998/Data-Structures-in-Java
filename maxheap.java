
import java.util.Scanner;
import java.util.Arrays;
import java.util.NoSuchElementException;
 
class BinaryHeap    
{
    private static final int d = 2;
    private int heapSize;
    private int[] heap;  
    public BinaryHeap(int capacity)
    {
        heapSize = 0;
        heap = new int[capacity + 1];
        Arrays.fill(heap, -1);
    }
    public boolean isEmpty( )
    {
        return heapSize == 0;
    }
    public boolean isFull( )
    {
        return heapSize == heap.length;
    }
    public void makeEmpty( )
    {
        heapSize = 0;
    }
    private int parent(int i) 
    {
        return (i - 1)/d;
    }
    private int kthChild(int i, int k) 
    {
        return d * i + k;
    }
    public void insert(int x)
    {
        if (isFull( ) )
            throw new NoSuchElementException("Overflow Exception");
        heap[heapSize++] = x;
        heapifyUp(heapSize - 1);
    }
    public int findMax( )
    {
        if (isEmpty() )
            throw new NoSuchElementException("Underflow Exception");           
        return heap[heapSize];
    }
    public int deleteMin()
    {
        int keyItem = heap[0];
        delete(0);
        return keyItem;
    }
    public int delete(int ind)
    {
        if (isEmpty() )
            throw new NoSuchElementException("Underflow Exception");
        int keyItem = heap[ind];
        heap[ind] = heap[heapSize - 1];
        heapSize--;
        heapifyDown(ind);        
        return keyItem;
    }
    private void heapifyUp(int childInd)
    {
        int tmp = heap[childInd];    
        while (childInd > 0 && tmp > heap[parent(childInd)])
        {
            heap[childInd] = heap[ parent(childInd) ];
            childInd = parent(childInd);
        }                   
        heap[childInd] = tmp;
    }
    private void heapifyDown(int ind)
    {
        int child;
        int tmp = heap[ ind ];
        while (kthChild(ind, 1) < heapSize)
        {
            child = maxChild(ind);
            if (heap[child] > tmp)
                heap[ind] = heap[child];
            else
                break;
            ind = child;
        }
        heap[ind] = tmp;
    }
    private int maxChild(int ind) 
    {
        int bestChild = kthChild(ind, 1);
        int k = 2;
        int pos = kthChild(ind, k);
        while ((k >= d) && (pos < heapSize)) 
        {
            if (heap[pos] > heap[bestChild]) 
                bestChild = pos;
            pos = kthChild(ind, k++);
        }    
        return bestChild;
    }
    public void printHeap()
    {
        System.out.print("\nHeap = ");
        for (int i = 0; i < heapSize; i++)
            System.out.print(heap[i] +" ");
        System.out.println();
    }     
}
public class maxheap
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("---MAX HEAP IMPLEMENTATION---\n\n");
        System.out.println("Enter size of Max heap");
        BinaryHeap bh = new BinaryHeap(scan.nextInt() );
 
        char ch;
        do    
        {
            System.out.println("\nMax Heap Operations\n");
            System.out.println("1. insert ");
            System.out.println("2. delete max");
            System.out.println("3. check full");            
            System.out.println("4. check empty");
            System.out.println("5. clear");
            System.out.println("6. print heap");
            System.out.println("Enter your choice ::  ");
 
            int choice = scan.nextInt();            
            switch (choice)
            {
            case 1 : 
                try
                {
                    System.out.println("Enter integer element to insert");
                    bh.insert( scan.nextInt() ); 
                }
                catch (Exception e)
                {
                    System.out.println(e.getMessage() );
                }
                break;                          
            case 2 : 
                try
                {
                    System.out.println("Maximum Element : "+ bh.deleteMin()); 
                }
                catch (Exception e)
                {
                    System.out.println(e.getMessage() );
                }                 
                break;                                
            case 3 : 
                System.out.println("Full status = "+ bh.isFull());
                break;                                   
            case 4 : 
                System.out.println("Empty status = "+ bh.isEmpty());
                break; 
            case 5 : 
                bh.makeEmpty();
                System.out.println("Heap Cleared\n");
                break;
            case 6:
                bh.printHeap();
                break;         
            default : 
                System.out.println("Wrong Entry \n ");
                break;   
            }
            bh.printHeap();  
 
            System.out.println("\nDo you want to continue (Type y or n) \n");
            ch = scan.next().charAt(0);                        
        } while (ch == 'Y'|| ch == 'y');  
    }
}