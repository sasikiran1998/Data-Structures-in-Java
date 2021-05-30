import java.util.Scanner;
class node{
    int data;
    node parent;
    node left;
    node right;
    public node(int data){
        this.data=data;
        this.parent=null;
        this.left=null;
        this.right=null;
        }
}
public class splaytree{
    public node root;
    public splaytree(){root=null;}
    //function for 6 types of splay rotation
   public void splay(node n){
        while(n.parent!=null){
            if(n.parent.parent==null){
                if(n==n.parent.left){
                    rotateright(n.parent);
                    System.out.println("Performing zig rot");
                }
                else{
                    rotateleft(n.parent);
                    System.out.println("Performing zag rot");
                }
            }
            else if(n==n.parent.left && n.parent==n.parent.parent.left){
                rotateright(n.parent.parent);
                rotateright(n.parent);
                System.out.println("Performing zig zig rot");
            }
            else if(n==n.parent.right && n.parent==n.parent.parent.right){
                rotateleft(n.parent.parent);
                rotateleft(n.parent);
                System.out.println("Performing zag zag rot");
            }
            else if(n==n.parent.right && n.parent==n.parent.parent.left){
                rotateleft(n.parent);
                rotateright(n.parent);
                System.out.println("Performing zig zag rot");
            }
            else {
                rotateright(n.parent);
                rotateleft(n.parent);
                System.out.println("Performing zag zig rot");
            }
        }
    }

    //function for left rotation
   public void rotateleft(node x){
        node y=x.right;
        x.right=y.left;
        if(y.left!=null){
            y.left.parent=x;
        }
        y.parent=x.parent;
        if(x.parent==null){
            this.root=y;
        }
        else if(x==x.parent.left){
            x.parent.left=y;
        }
        else{
            x.parent.right=y;
        }
        y.left=x;
        x.parent=y;
    }
    //function for right rotation
   public void rotateright(node x){
        node y=x.left;
        x.left=y.right;
        if(y.right!=null){
            y.right.parent=x;
        }
        y.parent=x.parent;
        if(x.parent==null){
            this.root=y;
        }
        else if(x==x.parent.right){
            x.parent.right=y;
        }
        else{
            x.parent.left=y;
        }
        y.right=x;
        x.parent=y;
    }
    //inserting the element
  public  void insert(int elt){
        node nod=new node(elt);
        node y=null;
        node x=this.root;
        while(x!=null){
            y=x;
            if(nod.data<x.data){
                x=x.left;
            }
            else{
                x=x.right;
            }
        }
        nod.parent=y;
        if(y==null){
            root=nod;
        }
        else if(nod.data<y.data){
            y.left=nod;
        }
        else{
            y.right=nod;
        }
        splay(nod);
    }
    //searching the element
  public  node search(node nod,int elt){
        if(nod==null || elt==nod.data){
            return nod;
        }
        if(elt<nod.data){
            return search(nod.left,elt);
        }
        else{
            return search(nod.right,elt);
        }
    }
    //deleting the element
   public void delete(node nod,int elt){
        node x=null;
        node y=null;
        node z=null;
        while(nod!=null){
            if(nod.data==elt){
                x=nod;
            }
            if(nod.data<=elt){
                nod=nod.right;
            }
            else{
                nod=nod.left;
            }
        }
        if(x==null){
            System.out.println(" Element is not in the tree");
            return;
        }
        splay(x);
        if(x.right!=null){
            y=x.right;
            y.parent=null;
        }
        else{
            y=null;
        }
        z=x;
        z.right=null;
        x=null;
        if(z.left!=null){
            z.left.parent=null;
        }
        root=join(z.left,y);
        z=null;
    }
    //joining the tree
   public node join(node z,node y){
        if(z==null){
            return y;
        }
        if(y==null){
            return z;
        }
        node x=maximum(z);
        splay(x);
        x.right=y;
        y.parent=x;
        return x;
    }
    //maximum element in the tree
    public node maximum(node nod){
        while(nod.right!=null){
            nod=nod.right;
        }
        return nod;
    }
    //printing the tree structure
   public void print(node cptr, String indent, boolean last){
        if(cptr!=null){
            System.out.print(indent);
            if(last){
                System.out.print("R----");
                indent +="     ";
            }
            else{
                System.out.print("L----");
                indent +="|    ";
            }
            System.out.println(cptr.data);
            print(cptr.left,indent,false);
            print(cptr.right,indent,true);
        }
    }
    public node searchtree(int k){
        node x=search(root,k);
        if(x!=null){
            splay(x);
        }
        return x;
    }
    public void printing(){
        print(this.root,"",true);
    }
    public void deleting(int data){
        delete(this.root,data);
        System.out.println("The Tree after deletion :: \n\n");
        printing();
    }
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        splaytree st=new splaytree();
        System.out.println("SPLAY Tree");          
        char ch;
        do    
        {
            System.out.println("\nSPLAY Tree Operations\n");
            System.out.println("1. INSERT ");
            System.out.println("2. SEARCH");
            System.out.println("3. DELETE");
            System.out.println("4. DISPLAY TREE");
            System.out.println("\nEnter your choice");
 
            int choice = sc.nextInt();            
            switch (choice)
            {
                case 1 : 
                System.out.println("Enter integer element to insert");
                st.insert( sc.nextInt() );                     
                break; 
                case 2 : 
                System.out.println("Enter integer element to search");
                st.searchtree( sc.nextInt() );                     
                break;
                case 3 : 
                System.out.println("Enter integer element to delete");
                st.deleting( sc.nextInt() );                     
                break;
                case 4 : 
                System.out.println("\n\nTREE\n\n");
                st.printing();                    
                break; 
                default : 
                System.out.println("Wrong Entry \n ");
                break;          
          }
           System.out.println("\nDo you want to continue (Type y or n) \n");
            ch = sc.next().charAt(0);                        
        } while (ch == 'Y'|| ch == 'y');    
        
    }
}

