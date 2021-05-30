import java.util.Scanner;
class node{
    int data;
    node parent;
    node left;
    node right;
    int color;
}
public class deleteredblack{
    private node root;
    private node tnull;
    public deleteredblack(){
        tnull=new node();
        tnull.color=0;
        tnull.left=null;
        tnull.right=null;
        root=tnull;
    }
    public void rotateleft(node x){
        node y=x.right;
        x.right=y.left;
        if(y.left!=tnull){
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
    public void rotateright(node x){
        node y=x.left;
        x.left=y.right;
        if(y.right!=tnull){
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
    public void fixing(node k){
        node u;
        while(k.parent.color==1){
            if(k.parent==k.parent.parent.right){
                u=k.parent.parent.left;
                if(u.color==1){
                    System.out.println("--RECOLOURING--");
                    u.color=0;
                    k.parent.color=0;
                    k.parent.parent.color=1;
                    k=k.parent.parent;
                }
                else{
                    if(k==k.parent.left){
                        k=k.parent;
                        System.out.println("--ROTATE RIGHT--");
                        rotateright(k);
                    }
                    System.out.println("--RECOLOURING--");
                    k.parent.color=0;
                    k.parent.parent.color=1;
                    System.out.println("--ROTATE LEFT--");
                    rotateleft(k.parent.parent);
                }
            }
            else{
                u=k.parent.parent.right;
                if(u.color==1){
                    System.out.println("--RECOLOURING--");
                    u.color=0;
                    k.parent.color=0;
                    k.parent.parent.color=1;
                    k=k.parent.parent;
                }
                else{
                    if(k==k.parent.right){
                        k=k.parent;
                        System.out.println("--ROTATE LEFT--");
                        rotateleft(k);
                    }
                    System.out.println("--RECOLOURING--");
                    k.parent.color=0;
                    k.parent.parent.color=1;
                    System.out.println("--ROTATE RIGHT--");
                    rotateright(k.parent.parent);
                }
            }
            if(k==root){break;}
        }
        root.color=0;
    }
     
    public void insert(int key){
        node n=new node();
        n.parent=null;
        n.data=key;
        n.left=tnull;
        n.right=tnull;
        n.color=1;
        node y=null;
        node x=this.root;
        while(x!=tnull){
            y=x;
            if(n.data<x.data){x=x.left;}
            else{x=x.right;}
        }
        n.parent=y;
        if(y==null){root=n;}
        else if(n.data<y.data){y.left=n;}
        else{y.right=n;}
        if(n.parent==null){
            n.color=0;
            return;
        }
        if(n.parent.parent==null){
            return;
        }
        fixing(n);
    }
    public void printing(node root,String indent,boolean last){
        if(root!=tnull){
            System.out.print(indent);
            if(last){
                System.out.print("R----");
                indent+="     ";
            }
            else{
                System.out.print("L----");
                indent+="|    ";
            }
            String scolor=root.color == 1 ? "RED" : "BLACK";
            System.out.println(root.data + "(" + scolor + ")");
            printing(root.left,indent,false);
            printing(root.right,indent,true);
        }
    }
    public void treeprint(){
        printing(this.root,"",true);
    }
    public void fixingdelete(node x){
        node s;
        while(x!=root && x.color==0){
            if(x==x.parent.left){
                s=x.parent.right;
                if(s.color==1){
                    s.color=0;
                    x.parent.color=1;
                    rotateleft(x.parent);
                    s=x.parent.right;
                }
                if(s.left.color==0 && s.right.color==0){
                    s.color=1;
                    x=x.parent;
                }
                else{
                    if(s.right.color==0){
                        s.left.color=0;
                        s.color=1;
                        rotateright(s);
                        s=x.parent.right;
                    }
                    s.color=x.parent.color;
                    x.parent.color=0;
                    s.right.color=0;
                    rotateleft(x.parent);
                    x=root;
                }
            }else{
                s=x.parent.left;
                if(s.color==1){
                    s.color=0;
                    x.parent.color=1;
                    rotateright(x.parent);
                    s=x.parent.left;
                }
                if(s.right.color==0 && s.right.color==0){
                    s.color=1;
                    x=x.parent;
                }else{
                    if(s.left.color==0){
                        s.right.color=0;
                        s.color=1;
                        rotateleft(s);
                        s=x.parent.left;
                    }
                    s.color=x.parent.color;
                    x.parent.color=0;
                    s.left.color=0;
                    rotateright(x.parent);
                    x=root;
                }
            }
        }
        x.color=0;
    }
    public void recolor(node u, node v){
        if(u.parent==null){
            root=v;
        }
        else if(u==u.parent.left){
            u.parent.left=v;
        }else{
            u.parent.right=v;
        }
        v.parent=u.parent;
    }
    public node getroot(){
        return this.root;
    }
    
    public void deleting(node n, int key){
        node z=tnull;
        node x,y;
        while(n!=tnull){
            if(n.data==key){
                z=n;
            }
            if(n.data<=key){
                n=n.right;
            }else{
                n=n.left;
            }
        }
        if(z==tnull){
            System.out.println("Element not found");
            return;
        }
        y=z;
        int ycolour=y.color;
        if(z.left==tnull){
            x=z.right;
            recolor(z,z.right);
        }else if(z.right==tnull){
            x=z.left;
            recolor(z,z.left);
        }else{
            y=minimum(z.right);
            ycolour=y.color;
            x=y.right;
            if(y.parent==z){x.parent=y;}
            else{
                recolor(y,y.right);
                y.right=z.right;
                y.right.parent=y;
            }
            recolor(z,y);
            y.left=z.left;
            y.left.parent=y;
            y.color=z.color;
        }
        if(ycolour==0){
          fixingdelete(x);
        }
    }
    public node minimum(node n) {
		while (n.left != tnull) {
			n = n.left;
		}
		return n;
	}
    public void delete(int data){
        deleting(this.root,data);
    }
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        deleteredblack rb=new deleteredblack();
        System.out.println("----RED BLACK TREE----");
        char ch;
        do{
           System.out.println("1. INSERT");
           System.out.println("2. DELETE");
           System.out.println("3. DISPLAY TREE");
           System.out.println("\nEnter your choice");
           int choice = sc.nextInt();            
            switch (choice) {
                case 1:
                System.out.println("Enter integer element to insert");
                rb.insert(sc.nextInt());
                break;
                case 2:
                System.out.println("Enter integer element to delete");
                rb.delete(sc.nextInt());
                System.out.println("\nTree after deletion :: \n");
                rb.treeprint();
                break;
                case 3:
                System.out.println("\n\nTREE\n\n");
                rb.treeprint();
                break;
                default:
                System.out.println("Wrong entry");
                break;
            }
            System.out.println("Do You Want To Continue (Type y or n) \n");
            ch=sc.next().charAt(0);
        }while(ch=='Y'||ch=='y');
    }

    
}