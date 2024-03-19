package dateorganizer;

/**
 * Specifies operations on a priority queue
 * @author Duncan, Courtney Pham <br>
 * <pre>
 * File: PQueue.java<br>
 * Course: csc 3102
 * Project: # 1
 * Instructor: Dr. Duncan
 * </pre>
 */


/**
 * This class reports PQueue exceptions.
 */
class PQueueException extends Exception 
{
    /**
     * Creates a new instance of <code>PQueueException</code> without detail
     * message.
     */
    public PQueueException() { }

    /**
     * Constructs an instance of <code>PQueueException</code> with the specified
     * detail message.
     * @param msg the detail message.
     */
    public PQueueException(String msg) 
    {
        super(msg);
    }
}

/** 
 * Describes the basic operations of a priority queue
 * @param <E>
 */
public interface PQueueAPI<E extends Comparable<E>>
{
   /**
    * Determines whether the priority queue is empty.
    * @return this method returns true if the priority is empty;
    * otherwise, it returns false if the priority queue contains at least one item.
    */
   boolean isEmpty();

   /**
    * Inserts an item into the priority queue.
    * @param item the value to be inserted.
    */
   void insert(E item);

   /**
    * An exception is generated if this method is invoked
    * by an empty priority. The maximum/minimum value is removed
    * from the priority queue if the priority queue is not empty and its effective
    * size is reduced by 1.
    * @return the value with the highest priority.
     * @throws PQueueException when the priority queue is empty
    */
   E remove() throws PQueueException;

   /**
    * An exception is generated if this method is invoked
    * by an empty priority queue
    * @return the value with the highest priority
     * @throws PQueueException when the priority queue is empty
    */
   E peek() throws PQueueException;


   /**
    * Gives the size of this priority queue
    * @return the size of the priority queue; the effective size of the
    * priority queue.
    */
   int size();
}
