import java.util.*;
import static java.lang.System.exit;
class queue{ 
private class node{
    int data;
    node next;
}
node front;
node rear;
queue(){
    this.front=null;
    this.rear=null;
}
public void enqueue(int x){
    node newnode=new node();
    newnode.data=x;
    newnode.next=null;
    if(front==null&&rear==null){
        front=rear=newnode;
    }
    else{
        rear.next=newnode;
        rear=newnode;
    }
}
 public void dequeue(){
     node temp;
     temp=front;
     if(front==null&&rear==null){
         System.out.println("underflow");
     }
     else{
         System.out.println("the popped out element is::");
         System.out.println(front.data);
         front=front.next;
     }
 }
 public void display(){
     node temp;
     if(front==null&&rear==null){
         System.out.println("no data to display");
     }
     else{
         temp=front;
         while(temp!=null){
             System.out.println(temp.data);
             temp=temp.next;
         }
     }
}
 public void peek(){
     if(front==null&&rear==null){
         System.out.println("No data to display");
     }
     else{
         System.out.println(front.data);
     }
 }
}
public class impl_que {
    public static void main(String args[]){
        queue obj=new queue();
        Scanner sc=new Scanner(System.in);
        char ch;
        do{
        int c;
        System.out.println("Choose queue operation 1.enqueue 2.dequeuep 3.display 4.peek 5.exit");
        c=sc.nextInt();
        switch(c){
            case 1:
            System.out.println("Insert the element");
            int i=sc.nextInt();
            obj.enqueue(i);
            break;
            case 2:
            obj.dequeue();
            break;
            case 3:
            System.out.println("----Displaying elements in stack----");
            obj.display();
            break;
            case 4:
            System.out.println("the peek element is");
            obj.peek();
            break;
            case 5:
            exit(1);
            break;
            }
        System.out.print("Do you want to continue (Type y or n)::   ");
            ch = sc.next().charAt(0);                        
        } while (ch == 'Y'|| ch == 'y');  
    }
}