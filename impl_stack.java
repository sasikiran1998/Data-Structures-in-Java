import java.util.*;
import static java.lang.System.exit;
class stack{ 
private class node{
    int data;
    node link;
}
node top;
stack(){
    this.top=null;
}
   public void push(int x){
       node newnode=new node();
       newnode.data=x;
       newnode.link=top;
       top=newnode;
   }
  public void display(){
       if(top==null){
           System.out.println("Underflow");
       }
       else{
           node temp=top;
           while(temp!=null){
               System.out.println(temp.data);
               temp=temp.link;
           }
       }
   }
   public void pop(){
       if(top==null){
           System.out.println("underflow");
       }
       else{
           System.out.println("the popped out element is");
           System.out.println(top.data);
           top=top.link;
       }
   }
}
public class impl_stack {
    public static void main(String args[]){
        stack obj=new stack();
        Scanner sc=new Scanner(System.in);
        char ch;
        do{
        int c;
        System.out.println("Choose stack operation 1.push 2.pop 3.display 4.exit");
        c=sc.nextInt();
        switch(c){
            case 1:
            System.out.println("Insert the element");
            int i=sc.nextInt();
            obj.push(i);
            break;
            case 2:
            obj.pop();
            break;
            case 3:
            System.out.println("----Displaying elements in stack----");
            obj.display();
            break;
            case 4:
            exit(1);
            break;
            }
        System.out.print("Do you want to continue (Type y or n)::   ");
            ch = sc.next().charAt(0);                        
        } while (ch == 'Y'|| ch == 'y');  
    }
}
