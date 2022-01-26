import java.io.IOException;
import java.util.Scanner;

public class Main {
    /**
     * Ham hien thi menu cua chuong trinh va goi den class MyPerson theo input
     */
    public static void showMenu() throws IOException {
        int choice=1;
        MyPerson p = new MyPerson();
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose one of this options:");
        System.out.println("Person Tree:");
        System.out.println("1. Insert a new Person.");
        System.out.println("2. Inorder traversal");
        System.out.println("3. Breadth-First Traversal");
        System.out.println("4. Postorder Traversal");
        System.out.println("5. Preorder Traversal");
        System.out.println("6. Search by Person ID");
        System.out.println("7. Delete by Person ID");
        System.out.println("8. Balancing Binary Search Tree ");
        System.out.println("9. DFS_Graph");
        System.out.println("10. BFS_Graph");
        System.out.println("11. Dijkstra");
        System.out.println("Exit:");
        System.out.println("0. Exit");
        //Vong lap se thoat ra khi input = 0 -> Thoat khoi chuong trinh
        while(choice!=0){
            System.out.println("Choice: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1 -> p.insert();
                case 2 -> p.printInOrder();
                case 3 -> p.printBST();
                case 4 -> p.printPostOrder();
                case 5 -> p.printPreOrder();
                case 6 -> p.search();
                case 7 -> p.delete();
                case 8 -> p.balance();
                case 9 -> p.dfs();
                case 10 ->p.bfs();
                case 11 -> p.dijkstra();
                case 0 -> System.out.println("Exit");
                default -> System.out.println("Make sure that it's an available option");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Main.showMenu();

    }
}
