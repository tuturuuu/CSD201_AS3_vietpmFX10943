import java.util.ArrayList;
import java.util.Scanner;

public class MyBSTree {
    public Node root;
    public MyBSTree() {}

    /**
     * Kiem tra xem cay co rong khong
     * @return boolean
     */
    public boolean isEmpty(){
        return root==null;
    }

    /**
     * Choice 1
     * Them 1 node vao cay
     */
    public void insert(Person p){
        if(isEmpty()) {
            root = new Node(p);
        }
        else{
            root.insert(p);
        }
    }

    /**
     * Choice 2
     * Duyet cay theo thu tu inOrder
     */
    public void printInOrder(ArrayList<Person> arrayList,Node current){
        //Khi node !=null
        if(current!=null){
            //Duyet het tat ca cac node o ben trai
            printInOrder(arrayList,current.left);
            //Neu node chua bi xoa -> them node vao arrayList
            if(!current.deleted) arrayList.add(current.info);
            //Duyet het tat ca cac node o ben phai
            printInOrder(arrayList,current.right);
        }
    }

    /**
     * Choice 3
     * Duyet node the chieu rong
     */
    public void printBST(){
        MyQueue q = new MyQueue();
        Node current = root;
        //Bat dau tu node root
        while(true){
            //Neu node != deleted -> in node
            if(!current.deleted) System.out.println(current.info);
            //Them node trai va phai cua node tren vao q
            if(current.left!=null) q.enqueue(current.left);
            if(current.right!=null) q.enqueue(current.right);
            //Khi q rong -> dung vong lap
            if(q.isEmpty()) break;
            //Dat lai current = node dau cua q
            current = (Node) q.dequeue();
        }
    }

    /**
     * Choice 4
     * Duyet node the thu tu postOrder
     */
    public void printPostOrder(Node current){
        if(current!=null){
            //Duyet tat ca cac node trai va phai cua root
            printPostOrder(current.left);
            printPostOrder(current.right);
            //Neu node chua bi xoa -> in node
            if(!current.deleted) System.out.println(current.info);
        }
    }

    /**
     * Choice 5
     * Duyet Node the thu tu preOrder
     */
    public void printPreOrder(Node current){
        if(current!=null){
            //In node cha truoc
            if(!current.deleted) System.out.println(current.info);
            //Duyet qua node trai va phai cua node cha
            printPreOrder(current.left);
            printPreOrder(current.right);
        }
    }

    /**
     * Choice 6
     * Su dung queue de tim kiem vi tri cua 1 node giua vao id
     * @return Node
     */
    public Node search(String id){
        MyQueue q = new MyQueue();
        Node current = root;
        while(current!=null){
            //Neu current > id -> dua left node cua current vao queue
            if(current.left!=null&&current.equals(id)) {
                //Neu current == id -> return current
                if(current.info.ID.compareTo(id)==0) {
                    if(current.deleted) {
                        break;
                    }
                    return current;
                }
                q.enqueue(current.left);
            }
            //Neu current < id -> dua right node cua current vao queue
            if(current.right!=null) q.enqueue(current.right);
            //Neu current == id -> return current
                if(current.info.ID.compareTo(id)==0) {
                    if(current.deleted) {
                        break;
                    }
                    return current;
                }
                //Neu q rong -> break
            if(q.isEmpty()) break;
            current = (Node) q.dequeue();
        }
        return null;
    }

    /**
     * Choice 7
     * Thay doi gia tri bool cua node -> deleted
     */
    public void delete(){
        //Nhap input tu nguoi dung
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the id you want to delete");
        String id = sc.next();
        //Tim node theo id
        Node temp = search(id);
        if(temp==null) {
            System.out.println("Id doesn't exist");
            return;
        }
        //Doi gia tri delete cua node thanh false
        temp.deleted = true;
    }

    /**
     * Choice 8
     * Su dung de quy de lay phan tu o giua first va last
     * Insert phan tu vao 1 cay moi sao cho cay moi balance
     * Thay root cu = root cua balanced tree
     * @param list ArrayList cua 1 mang cac node cua cay duoc sap xep tu be - > lon
     * @return node root cua cay moi
     */
    private Node balance(ArrayList<Person> list, int first, int last){
        //Lay phan tu o giua last, first
        if(first>last) return null;
        int mid = (last+first)/2;
        Node node = new Node(list.get(mid));
        //Su dung de quy de them nhung node phu hop vao phia ben trai cua cay
        node.left = balance(list,first,mid-1);
        //Su dung de quy de them nhung node phu hop vao phia ben phai cua cay
        node.right = balance(list,mid+1,last);
        return node;
    }

    /**
     * Choice 8
     * Tao mang arrayList -> luu cac node theo thu tu be-> lon vao mang
     * Goi ham balance -> thay vao root moi
     * @return root cua cay moi
     */
    public Node balance(){
        ArrayList<Person> list = new ArrayList<>();
        printInOrder(list,root);
        return balance(list,0,list.size()-1);
    }





}

