import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.StringTokenizer;

public class Graph {
    static int INF = 9999;
    int[][] a;
    boolean[] selected ;
    int n;
    String s1, shortestPath, b;
    Graph() throws IOException {
        this.s1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        setWeights("src/Matran");
    }

    /**
     * Nhap du lieu tu file va luu vao mang a[][]
     */
    void setWeights(String filename) throws IOException, IOException {
        int i=0, j=0;
        String s = "";
        StringTokenizer t;
        RandomAccessFile f;
        f = new RandomAccessFile(filename, "r");
        //Doc 1 dong o trong file
        s = f.readLine();
        //Su dung chia nho string ra voi token = " "
        t = new StringTokenizer(s);
        int numberOfNode = t.countTokens();
        //Dem so khoang trang -> So node cua graph
        n = t.countTokens();
        int[][] b = new int[numberOfNode][numberOfNode];
        boolean[] tempCheck = new boolean[numberOfNode];
        while (true) {
            j=0;
            //Luu gia tri cua phan tu vao trong mang b[][]
            while(t.hasMoreTokens()){
                b[i][j] = Integer.parseInt(t.nextToken().trim());
                j++;
            }
            s = f.readLine();
            if(s==null) break;
            t = new StringTokenizer(s);
            i++;
        }
        //Khai bao cho mang a[][] va selected[]
        a=b;
        selected = tempCheck;
    }

    //Dat tat ca cac phan tu trong selected = true
    public void setTrue(){
        for(int i = 0;i<selected.length;i++){
            selected[i] = true;
        }
    }

    /**
     * Hien thi tat ca cac phan tu o trong mang
     */
    public void displayWeights() {
        System.out.println("The weighted matrix of the graph:");
        System.out.println("========================================");
        //Di qua tung phan tu cua mang -> Hien thi tung phan tu
        for(int i = 0;i < n;i++){
            for(int j =0;j < n;j++){
                if(a[i][j]==9999) System.out.print("INF ");
                else System.out.print(a[i][j]+" ");
            }
            System.out.println();
        }
    }

    /**
     * Choice 9
     *Duyet mang theo dfs
     */
    public void dfs(int k){
        //Di qua tung node cua mang A->G
        for(int j = 0 ; j < n;j++){
            //Kiem tra nhung diem co the di den
            if(a[k][j]!=0&&a[k][j]!=INF&&selected[j]) {
                //Hien thi diem co the di ra man hinh danh dau diem do la false
                System.out.println(s1.charAt(k)+"->"+s1.charAt(j));
                selected[j] = false;
                selected[k] = false;
                //Tim kiem nhung node co the di den tu diem do
                dfs(j);
            }
        }
    }

    /**
     * Choice 10
     * Su dung queue de duyet cac node the bfs
     */
    public void bfs(){
        int k=0;
        MyQueue q = new MyQueue();
        do {
            //Di qua tung node trong mang A->G
            for(int j = 0;j < n;j++) {
                if (a[k][j] != 0 && a[k][j] != INF && selected[j]) {
                    //Hien thi diem co the di ra man hinh danh dau diem do la false
                    System.out.println(s1.charAt(k) + "->" + s1.charAt(j));
                    selected[j] = false;
                    selected[k] = false;
                    //Cho nhung diem co the di den duoc vao queue
                    q.enqueue(j);
                }
            }
            //Thay k = phan tu dau cua queue -> tim kiem nhung diem co the di den tu k
            k = (int) q.dequeue();
        }while (!q.isEmpty());
    }

    /**
     * Tim phan tu nho nhat chua duoc chon trong dist[]
     */
    public int extractMin(int[] dist, boolean[] selected){
        int min = INF, min_index = -1;
        for(int i = 0 ; i < dist.length;i++){
            if(min>=dist[i]&&!selected[i]){
                min = dist[i];
                min_index = i;
            }
        }
        return min_index;
    }

    /**
     * Choice 11
     * Tim duong di ngan nhat tu A->E theo phuong phat dijkstra
     */
    public void dijkstra() {
        //Diem truoc diem do
        int[] path = new int[n];
        //Quang duong tu A-> diem do
        int[] dist = new int[n];
        shortestPath="";
        b="";
        //Khoi tao mang dist
        for(int i =0; i<n;i++){
            dist[i] = INF;
            selected[i] = false;
        }
        displayWeights();
        System.out.println("Dijkstra algorithm for shortest path from A to  E:");
        //Quang duong tu A->A=0
        dist[0]=0;
        for(int i =0; i < n-1;i++) {
            //Lay diem di tu A ngan nhat trong dist[]
            int u = extractMin(dist, selected);
            for (int v = 0; v < n; v++) {
                /*
                *Neu diem v khong nam trong phan da tim duoc
                *Diem v!=u ->a[u][v] !=0
                *Khoang cach tu a->u != INF
                *khoang cach A->v > khoang cach tu A->u+ u->v
                * ->Cap nhat khoang cach tu A->v = khoang cach tu A->u+ u->v
                */
                if (!selected[v] && a[u][v] != 0 && dist[u] != INF && dist[v] > dist[u] + a[u][v]) {
                    dist[v] = dist[u] + a[u][v];
                    path[v] = u;
                    if(v==4){
                        //Luu cac phan tu phai di qua de den E
                        shortestPath+=s1.charAt(u)+"->";
                    }
                }
            }
            System.out.print(i+":    ");
            //Hien thi mang dist
            dijkstraStep(dist,path, u);
            //Doi selected cua diem u = true
            selected[u] = true;
            //Khi di den diem E -> thoat khoi vong lap
            if(u==4) break;
        }
        //Hien thi path tu A-> E
        dijkstraPath(dist);
    }

    /**
     * In mang dist
     * Hien thi quan duong tu A->E
     */
    void dijkstraPath(int[] dist)
    {
        System.out.println(" The length of shortest path from A to  E is "+dist[4]);
        System.out.println("Path:");
        System.out.println("A->"+shortestPath+"E");
    }

    /**
     * In tung buoc cua phuong phat dijkstra
     */
    public void dijkstraStep(int[] dist, int[] path, int temp){
        //b = cac diem da di qua
        b+=s1.charAt(temp);
        System.out.print(b);
        //Di qua tung phan tu cua dist[] va hien thi theo yeu cau de bai
        for(int i =1; i<n;i++){
            if(!selected[i]) {
                if (dist[i] == 9999) System.out.print("(INF," + s1.charAt(path[i]) + ")");
                else System.out.print("(" + dist[i] + "," + s1.charAt(path[i]) + ")");
            }
        }
        System.out.println();
    }
}
