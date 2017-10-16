public class MyHashTable {

    List[] hashList = null;
    int capSize = 100;

    public MyHashTable() {
        hashList = new List[100];
    }

    public MyHashTable(int n) {
        capSize = n;
        hashList = new List[capSize];
    }

    public void insert(String k, double v) {
        int hash = hashCode(k);
        if(hashList[hash] == null) {
            hashList[hash] = new List();
        }
        hashList[hash].insert(v, k);
        resize();
    }

    public void remove(String k) {
        int hash = hashCode(k);
        hashList[hash].remove(k);

    }

    public double getValue(String k) {
        int hash = hashCode(k);
        if(hashList[hash] == null){
            return 0.0;
        }
        return hashList[hash].getItem(k);
    }

    public boolean contains(String k) {
        int hash = hashCode(k);
        if(hashList[hash] == null){
            return false;
        } else{
            return hashList[hash].exist(k);
        }
    }

    public int size() {
        int count = 0;
        for(int i = 0; i != hashList.length; i++){
            if(hashList[i] != null){
                count += hashList[i].size();
            }
        }
        return count;
    }

    public int capacity() {
        return capSize;
    }

    public void printTable() {
        for(int i = 0; i< hashList.length; i++){
            System.out.print("Index " + i + ": ");
            if(hashList[i] == null || hashList[i].head == null){
                System.out.println("null");
            } else{
                System.out.println(hashList[i].str());
            }
        }
    }


    private int hashCode(String key) {
        int hash = 0;
        for(int i = 0; i < key.length(); i++){
            char a = key.charAt(i);
            hash += (int)a;
        }
        return hash%capSize;
    }

    private void resize() {
        if(capacity() == size()){
            String[] key = new String[capSize];
            double[] vul = new double[capSize];
            int j = 0;
            for(int i = 0; i < capSize; i++){
                if(hashList[i] != null ||hashList[i].head == null){
                    key[j] = hashList[i].head.key;
                    vul[j] = hashList[i].head.num;
                    j++;
                    node temp = hashList[i].head;
                    while(temp.next != null){
                        temp = temp.next;
                        key[j] = temp.key;
                        vul[j] = temp.num;
                        j++;
                    }
                }
            }
        }
    }


    class List {
        node head = null;
        int num = 0;

        public List() {
        }

        public void insert(double v, String k) {
            if (head == null) {
                head = new node(v, k);
            } else if(head.key.equals(k)){
                head.num = v;
            } else {
                node temp = head;
                while (temp.next != null) {
                    temp = temp.next;
                    if(temp.key.equals(k)){
                        temp.num = v;
                        return;
                    }
                }
                temp.next = new node(v,k);
            }
            num ++;
        }

        public double getItem(String key) {
            node temp = head;
            if(temp.key.equals(key)){
                return temp.num;
            } else {
                while(!temp.next.key.equals(key)){
                    temp= temp.next;
                    if(temp.next == null){
                        return 0.0;
                    }
                }
                return temp.next.num;
            }
        }

        public boolean exist(String key){
            node temp = head;
            if(head == null){
                return false;
            } else if(temp.key.equals(key)){
                return true;
            } else {
                while (!temp.next.key.equals(key)) {
                    temp = temp.next;
                    if (temp.next == null) {
                        return false;
                    }
                }
                return true;
            }
        }

        public void remove(String key){
            if(head.next == null){
                head = null;
            }else if(head.key.equals(key)){
                head = head.next;
            } else {
                node temp = head;
                while(!temp.next.key.equals(key)){
                    temp = temp.next;
                }
                temp.next = temp.next.next;
            }
        }

        public int size(){
            return num;
        }

        public String str(){
            String text = head.num+"  -> ";
            node temp = head;
            while(head.next!= null){
                head = head.next;
                text += temp.num;
                if(head.next != null){
                    text += " -> ";
                }
            }
            return text;
        }

    }

    class node {
        double num;
        String key;
        node next = null;

        public node(double v,String k) {
            num = v;
            key = k;
        }
    }
}