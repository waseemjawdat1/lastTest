package application;

import java.util.ArrayList;

public class HashTableMovie {
    private AVLTree<Movie>[] table;
    private int size;
    private int capacity;

    public HashTableMovie() {
        allocate(11); 
    }

    public HashTableMovie(int size) {
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
    
    private int hash(Movie m) {
        return Math.abs(m.hashCode()) % capacity;
    }

    public void insert(Movie m) {
        int index = hash(m);
        if (table[index].search(m) == null) {
            table[index].insert(m);
            size++;
            if (avg() > 3)
                rehash();
        }
    }

    public void delete(Movie m) {
        int index = hash(m);
        if (table[index].search(m) != null) {
            table[index].delete(m);
            size--;
        }
    }

    public boolean search(Movie m) {
        int index = hash(m);
        return table[index].search(m) != null;
    }
    

    public void deleteByTitle(String title) {
        Movie temp = new Movie();
        temp.setTitle(title);
        delete(temp);
    }

    public boolean searchByTitle(String title) {
        Movie temp = new Movie();
        temp.setTitle(title);
        return search(temp);
    }

    private double avg() {
        int totalHeight = 0;
        for (AVLTree<Movie> avl : table) {
            totalHeight += avl.height();
        }
        return (double) totalHeight / capacity;
    }

    private void rehash() {
        int oldCapacity = capacity;
        capacity = nextPrime(capacity * 2);
        AVLTree<Movie>[] oldTable = table;

        table = new AVLTree[capacity];
        for (int i = 0; i < capacity; i++) {
            table[i] = new AVLTree<>();
        }

        size = 0;
        for (AVLTree<Movie> avl : oldTable) {
            ArrayList<Movie> list = avl.toList();
            for (Movie m : list) {
                insert(m);
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
    public Movie getByTitle(String title) {
        Movie temp = new Movie();
        temp.setTitle(title);
        int index = hash(temp);
        
        return table[index].search(temp); 
    }
    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return capacity;
    }
}
