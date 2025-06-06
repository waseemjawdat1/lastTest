package application;

import java.util.ArrayList;

public class HashTable<T extends Comparable<T>> {
    private AVLTree<T>[] table;
    private int size;
    private int capacity;

    public HashTable() {
        allocate(11);
    }

    public HashTable(int size) {
        allocate(size);
    }
    
    private void allocate(int size) {
        this.capacity = nextPrime(size);
        this.size = 0;
        table = new AVLTree[capacity];
        for (int i = 0; i < capacity; i++) {
            table[i] = new AVLTree<>();
        }
    }
    public int getPreviousNonEmptyIndex(int currentIndex) throws Exception {
        if (size == 0)
            throw new Exception("Hash table is empty");

        int index = currentIndex;
        do {
            index = (index - 1 + table.length) % table.length;
            if (table[index].height() > 0) {
                return index;
            }
        } while (index != currentIndex);

        throw new Exception("All Trees empty");
    }
    public int getNextNonEmptyIndex(int currentIndex) throws Exception {
        if (size == 0)
            throw new Exception("Hash table is empty");

        int index = currentIndex;
        do {
            index = (index + 1) % table.length;
            if (table[index].height() > 0) {
                return index;
            }
        } while (index != currentIndex);

        throw new Exception("All Trees empty");
    }


    private int hash(T t) {
        return Math.abs(t.hashCode()) % capacity;
    }

    public void insert(T t) {
        int index = hash(t);
        if (table[index].search(t) == null) {
            table[index].insert(t);
            size++;
            if (avg() > 3)
                rehash();
        }
        else {
        	System.out.println(((Movie)t).getTitle());
        }
    }

    public void delete(T item) {
        int index = hash(item);
        if (table[index].search(item) != null) {
            table[index].delete(item);
            size--;
        }
    }
    
    public int getSz() {
    	int sz= 0;
    	for (int i  = 0 ;i < table.length; i++) {
    		sz+= table[i].toList().size();
    	}
    	return sz;
    }

    public boolean search(T t) {
        int index = hash(t);
        return table[index].search(t) != null;
    }

    public T get(T t) {
        int index = hash(t);
        return table[index].search(t);
    }

    private double avg() {
        int totalHeight = 0;
        int totalTrees = 0;
        for (int i =0  ;i< table.length; i++) {
        	if (table[i].height() != 0) {
        		totalTrees++;
        		totalHeight += table[i].height();
        	}
        }
       if (totalTrees == 0) return 0;
        return (double) totalHeight / totalTrees;
    }

    private void rehash() {
        System.out.println("Rehashing...");
        int oldCapacity = capacity;
        capacity = nextPrime(capacity * 2);
        AVLTree<T>[] oldTable = table;

        table = new AVLTree[capacity];
        for (int i = 0; i < capacity; i++) {
            table[i] = new AVLTree<>();
        }
        size = 0;

        for (int i = 0; i < oldCapacity; i++) {
            ArrayList<T> list = oldTable[i].toList();
            for (T item : list) {
                int index = Math.abs(item.hashCode()) % capacity;
                if (table[index].search(item) == null) {
                    table[index].insert(item);
                    size++;  
                }
            }
        }
    }

    public void deallocate() {
        for (int i = 0; i < capacity; i++) {
            table[i] = null;
        }
        table = null;
        size = 0;
        capacity = 0;
    }

    private int nextPrime(int n) {
        while (!isPrime(n)) n++;
        return n;
    }

    private boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
    public AVLTree<T> getAVL (int index){
    	return table[index];
    }
    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return capacity;
    }
    
}
