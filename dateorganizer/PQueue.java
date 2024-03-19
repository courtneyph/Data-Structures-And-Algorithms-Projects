package dateorganizer;

import java.util.*;

/**
 * This class describes a priority min-queue that uses an array-list-based min binary heap 
 * that implements the PQueueAPI interface. The array holds objects that implement the 
 * parameterized Comparable interface.
 * @author Duncan, Courtney Pham
 * @param <E> the priority queue element type. 
 * @since 9-25-23
 *  */
public class PQueue<E extends Comparable<E>> implements PQueueAPI<E>
{    
   /**
    * A complete tree stored in an array list representing the 
    * binary heap
    */
   private ArrayList<E> tree;
   /**
    * A comparator lambda function that compares two elements of this
    * heap when rebuilding it; cmp.compare(x,y) gives 1. negative when x less than y
    * 2. positive when x greater than y 3. 0 when x equal y
    */   
   private Comparator<? super E> cmp;
   
   /**
    * Constructs an empty PQueue using the compareTo method of its data type as the 
	* comparator
    */
   public PQueue()
   {
      tree = new ArrayList<E>();
      cmp = (obj1, obj2) -> obj1.compareTo(obj2);
      //for (int m=0;m<tree.size();m++) {System.out.println(tree.poll());
   }
   
   /**
    * A parameterized constructor that uses an externally defined comparator    
    * @param fn - a trichotomous integer value comparator function   
    */
   public PQueue(Comparator<? super E> fn)
   {
       tree = new ArrayList<>();
       cmp = fn;
   }

   public boolean isEmpty()
   {
      return tree.isEmpty();
   }

   public void insert(E obj)
   {
    tree.add(obj);
    int index = tree.size() - 1;
    int parIndex = (index - 1) / 2;
    while (index > 0 && cmp.compare(tree.get(index), tree.get(parIndex)) > 0) {
        swap(index, parIndex);
        index = parIndex;
        parIndex = (index - 1) / 2;
    }
   }

   public E remove() throws PQueueException
   {
      int size = size() - 1;
      if (size < 0) {
        throw new PQueueException("Priority queue is empty.");
        }
      swap(0, size);
      E removed = tree.remove(size);
      rebuild(0);
      return removed;
   }
 
   public E peek() throws PQueueException
   {
      if (isEmpty()) {
        throw new PQueueException("Priority queue is empty.");
      }
      return tree.get(0);
   }

   public int size()
   {
      return (tree.size());
   }
   
   /**
    * Swaps a parent and child elements of this heap at the specified indices
    * @param place an index of the child element on this heap
    * @param parent an index of the parent element on this heap
    */
   private void swap(int place, int parent)
   {
      Collections.swap(tree,place,parent);
   }

   /**
    * Rebuilds the heap to ensure that the heap property of the tree is preserved.
    * @param root the root index of the subtree to be rebuilt
    */
   private void rebuild(int root)
   {
    int lChild = 2 * root + 1;
    int rChild = 2 * root + 2;
    int largest = root;
   
    if (lChild < size() && 
            cmp.compare(tree.get(lChild), tree.get(largest)) > 0) {
        largest = lChild;
    }
    if (rChild < size() && 
            cmp.compare(tree.get(rChild), tree.get(largest)) > 0) {
        largest = rChild;
    }
    if (largest != root) {
        swap(root, largest);
        rebuild(largest);
    }
   }
}
