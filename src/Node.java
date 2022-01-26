public class Node {
    public Person info;
    public Node left;
    public Node right;
    public boolean deleted;

    public Node(Person info) {
        this.info = info;
        deleted = false;
    }

    /**
     * Cach so sanh 2 id
     * @return true -> this.id > id
     */
    public boolean equals(String id) {
        if(id.length() < this.info.ID.length()) return true;
        if(id.length() > this.info.ID.length()) return false;
        return this.info.ID.compareTo(id) > 0;
    }

    /**
     * Su dung de quy de them phan tu vao cay
     */
    public void insert(Person p){
        //Neu this.id < id -> newNode nam o ben trai cua cay
        if(this.equals(p.ID)){
            if(this.left!=null) this.left.insert(p);
            else this.left = new Node(p);
        }
        //Neu this.id > id -> newNode nam o ben phai cua cay
        if(!this.equals(p.ID)) {
            if(this.right!=null) this.right.insert(p);
            else this.right= new Node(p);
        }
    }
}
