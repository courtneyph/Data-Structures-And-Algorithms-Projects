package dendrologist;

import java.util.function.Function;
import java.util.ArrayList;

/**
 * Reports an exception in an AVL Tree
 * @author Duncan
 * <pre>
 * Date: 99-99-9999
 * Course: csc 3102
 * Programming Project # 2
 * Instructor: Dr. Duncan
 * Note: DO NOT MODIFIED THIS FILE
 * </pre>
 */
class AVLTreeException extends Exception 
{

    /**
     * Creates a new instance of <code>AVLTreeException</code> without detail
     * message.
     */
    public AVLTreeException() { }

    /**
     * Constructs an instance of <code>AVLTreeException</code> with the
     * specified detail message.
     * @param msg the detail message.
     */
    public AVLTreeException(String msg) 
    {
        super(msg);
    }
}

/**
 * Describes operations on an AVLTree
 * @param <E> the data type
 * @author William Duncan
 * @see AVLTreeException
 * <pre>
 * Date: 99-99-9999
 * CSC 3102 Programming Project # 2
 * Instructor: Dr. Duncan 
 *
 * DO NOT REMOVE THIS NOTICE (GNU GPL V2):
 * Contact Information: duncanw@lsu.edu
 * Copyright (c) 2022 William E. Duncan
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>
 * </pre>
 */
public interface AVLTreeAPI<E extends Comparable<E>>
{
   /**
    * Determines whether the tree is empty.
    * @return true if the tree is empty;  otherwise, false
   */
   boolean isEmpty();

   /**
    * Inserts an item into the tree.
    * @param obj the value to be inserted.
    */
   void insert(E obj);

   /**
    * Determine whether an item is in the tree.
    * @param item item with a specified search key.
    * @return true on success; false on failure.
    */
   boolean inTree(E item);

   /**
    * Delete an item from the tree.
    * @param item item with a specified search key.
   */
   void remove(E item);

   /**
    * returns the item with the given search key.
    * @param key the key of the item to be retrieved
    * @return the item with the specified key
    * @throws AVLTreeException when no such element exists 
    */
   E retrieve(E key) throws AVLTreeException;
   
   /**
    * This function traverses the tree in in-order
    * and calls the function apply, once for each node.
    * @param apply the function to apply to the data in each node
    */
   void traverse(Function apply);
   
   /**
    * Returns the number of items stored in the tree.
    * @return the size of the tree.
    */
   int size();
   /*===> Begin: Signatures of AUGMENTED public methods <===*/  
   /**
    * This function traverses the tree in in preorder
    * and calls the function apply, once for each node.
    * @param apply the function to apply to the data in each node
    */
   void preorderTraverse(Function apply);

   /**
    * This function traverses the tree in in postorder
    * and calls the function apply, once for each node.
    * @param apply the function to apply to the data in each node
    */
   void postorderTraverse(Function apply);

   /**
    * Determines the entries in the left and right child nodes of the 
    * node containing the specify entry
    * @param entry an entry in this tree
    * @return an array list of size 2 such that [0] contains the entry in the left child,
    * [1] contains the entry in the right child, or null for either entry
    * if they do not exist.
    * @throw AVLTreeException when the specified entry is not in this tree
    */
   public ArrayList<E> getChildren(E entry) throws AVLTreeException;    
   
   /**
    * Determines the entry in parent node of the node containing
    * the specify entry
    * @param entry an entry in this tree
    * @return the entry in the parent node
    * @throw AVLTreeException when the specified entry is not in this tree
    */
   public E getParent(E entry) throws AVLTreeException;    
   
   /**
    * Counts the number of ancestor nodes for the node containing the
    * specified entry
    * @param entry an entry in this tree
    * @return the number of ancestors for the specified entry
    * @throw AVLTreeException if this entry is not in this tree
    */
   public int ancestors(E entry) throws AVLTreeException;
   
   /**
    * Counts the number of descendant nodes for the node containing the
    * specified entry
    * @param entry an entry in this tree
    * @return the number of descendants for the specified entry
    * @throw AVLTreeException if this entry is not in this tree
	* <pre>
	* This method obtains a reference to the node containing the entry and 
	* passes that reference to the countDesc method to recursively
	* count the descendants of the node containing the enry.
	* </pre	
    */
   public int descendants(E entry) throws AVLTreeException;
   
   /**
    * Gives the height of this tree.
    * @return the height of this tree
    */   
   public int height();   

   /**
    * Gives the diameter of this tree.
    * @return the diameter of this tree
    */   
   public int diameter();   


   /**
    * Determines whether or not this tree is a Fibonacci tree
    * @return true if this tree is a Fibonacci tree; otherwise, false 
    */
   public boolean isFibonacci();
   
   /**
    * Determines whether this tree is complete
    * @return true if this tree is complete; otherwise, false
    */
   public boolean isComplete();
   
   /*===> End: Signatures of AUGMENTED public methods <===*/  
}