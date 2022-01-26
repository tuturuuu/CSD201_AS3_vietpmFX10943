import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MyPerson {
    MyBSTree tree = new MyBSTree();
    Graph graph= new Graph();

    public MyPerson() throws IOException {
    }

    /**
     * Choice 1
     * Nhan input tu nguoi dung  -> Them 1 node moi trong cay
     */
    public void insert() {
        Scanner sc = new Scanner(System.in);
        String id;
        //Kiem tra xem id da co trong cay hay chua -> Neu khong -> nhap lai id
        while (true) {
            System.out.println("ID:");
            id = sc.next();
            if (tree.search(id) == null) break;
            else System.out.println("ID existed");
        }
        sc.nextLine();
        System.out.println("Name: ");
        String name = sc.nextLine();
        System.out.println("Birthplace: ");
        String birthPlace = sc.nextLine();
        System.out.println("Date of birth: ");
        String dob = sc.nextLine();
        //Them Node moi vao cay
        tree.insert(new Person(id, name, birthPlace, dob));
    }

    /**
     * Choice 2
     */
    public void printInOrder(){
        ArrayList<Person> p = new ArrayList<>();
        //Luu cac node theo thu tu inorder vao list
        tree.printInOrder(p,tree.root);
        //Hien thi list
        for (Person person : p) {
            System.out.println(person);
        }
    }

    /**
     * Choice 3
     * Hien thi mang theo BST
     */
    public void printBST(){
        tree.printBST();
    }

    /**
     * Choice 4
     * Duyet cay theo postOrder
     */
    public void printPostOrder(){tree.printPostOrder(tree.root);}

    /**
     * Choice 5
     * Duyet cay theo preOrder
     */
    public void printPreOrder(){tree.printPreOrder(tree.root);}

    /**
     * Choice 6
     * Tim 1 node trong cay
     */
    public void search(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the id you want to find: ");
        String id = sc.next();
        Node newNode = tree.search(id);
        //Neu newNode == null -> node khong co trong cay -> in thong bao ra man hinh
        if(newNode==null){
            System.out.println("Id doesn't exist");
            return;
        }
        System.out.println(newNode.info);
    }

    /**
     * Choice 7
     * Xoa 1 phan tu trong cay
     */
    public void delete(){
        tree.delete();
    }

    /**
     * Choice 8
     * Can bang cay
     */
    public void balance(){
        tree.root=tree.balance();
    }

    /**
     * Choice 9
     * Duyet graph theo phuong thuc DFS
     */
    public void dfs() {
        graph.setTrue();
        graph.dfs(0);}

    /**
     * Choice 10
     * Duyet graph theo phuong thuc BFS
     */
    public void bfs(){
        graph.setTrue();
        graph.bfs();
    }

    /**
     * Choice 11
     * Su dung phuong thuc dijsktra de tim duong di ngan nhat
     */
    public void dijkstra(){
        graph.setTrue();
        graph.dijkstra();
    }
}

