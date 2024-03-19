package dendrologist;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * A testbed for an augmented implementation of an AVL tree
 * @author William Duncan, Courtney Pham
 * @see AVLTreeAPI, AVLTree
 * <pre>
 * Date: 10-18-2023
 * Course: csc 3102 
 * Programming Project # 2
 * Instructor: Dr. Duncan 
 * </pre>
 */
public class Dendrologist
{
    public static void main(String[] args) throws FileNotFoundException, AVLTreeException 
    {
            System.out.println("Received " + args.length + " arguments:");
    
            for (String arg : args) {
                System.out.println(arg);
            }
    
            if (args.length != 2) {
                System.out.println("Usage: Dendrologist <order-code> <command-file>");
                System.exit(1);
            }
        String usage = "Dendrologist <order-code> <command-file>\n";
        usage += "  <order-code>:\n";
        usage += "  0 ordered by increasing string length, primary key, and reverse lexicographical order, secondary key\n";
        usage += "  -1 for reverse lexicographical order\n";
        usage += "  1 for lexicographical order\n";
        usage += "  -2 ordered by decreasing string length\n";
        usage += "  2 ordered by increasing string length\n";
        usage += "  -3 ordered by decreasing string length, primary key, and reverse lexicographical order, secondary key\n";
        usage += "  3 ordered by increasing string length, primary key, and lexicographical order, secondary key\n";      
        if (args.length != 2)
        {
            System.out.println(usage);
            throw new IllegalArgumentException("There should be 2 command line arguments.");
        }
       Scanner in = new Scanner(new FileReader(args[1]));
       String orderCode = args[0];
       Comparator cmp = null;
  
       switch (orderCode) {
        case "0": cmp = new Comparator<String>() {
            @Override
            public int compare(String oc1, String oc2) {
                if(oc1.length() > oc2.length()) {
                    return 1;
                }
                if(oc1.length() < oc2.length()) {
                    return -1;
                }
                else {
                    return -1*oc1.compareTo(oc2);
                }
            }
       };
       break;
       case "-1": cmp = new Comparator<String>() {
        @Override
        public int compare(String oc1, String oc2) {
            return -1*oc1.compareTo(oc2);
        }
       };
       break;
       case "1": cmp = new Comparator<String>() {
        @Override
        public int compare(String oc1, String oc2) {
            return oc1.compareTo(oc2);
       }
    };
    break;
       case "-2": cmp = new Comparator<String>() {
        @Override
        public int compare(String oc1, String oc2) {
            if(oc1.length() > oc2.length()) {
                return 1;
            }
            if(oc1.length() < oc2.length()) {
                return -1;
            }
            else {
                return 0;
            }
        }
    };  
    break;
       case "2": cmp = new Comparator<String>() {
        @Override
         public int compare(String oc1, String oc2) {
            if(oc1.length() > oc2.length()) {
                return -1;
            }
            if(oc1.length() < oc2.length()) {
                return 1;
            }
            else {
                return 0;
            }
        }
    };  
    break;
       case "-3": cmp = new Comparator<String>() {
        @Override
         public int compare(String oc1, String oc2) {
            if(oc1.length() > oc2.length()) {
                return -1;
            }
            if(oc1.length() < oc2.length()) {
                return 1;
            }
            else {
                return -1*oc1.compareTo(oc2);
            }
        }
    };  
       break;
       case "3": cmp = new Comparator<String>() {
        @Override
         public int compare(String oc1, String oc2) {
            if(oc1.length() > oc2.length()) {
                return 1;
            }
            if(oc1.length() < oc2.length()) {
                return -1;
            }
            else {
                return oc1.compareTo(oc2);
            }
        }
    }; 	
    break;     

}
    AVLTree tree = new AVLTree(cmp);
    String cmd = "";
       while(in.hasNextLine()) {
        cmd = in.next();
        switch(cmd) {
            case "insert": {
            	String n = in.next();
                System.out.println("\nInserted: " + n);
                tree.insert(n);
        }
        break;
            case "delete": {
            	String n = in.next();
            	System.out.println("\nDeleted: " + n);
            	tree.remove(n);
             
        }
        break;
            case "traverse": {
                System.out.printf("\npre-Order Traversal:\n");
                tree.preorderTraverse(x -> {System.out.println(x); return null;});
                System.out.printf("\nIn-Order Traverse:\n");
                tree.traverse(x -> {System.out.println(x); return null;});
                System.out.printf("\npost-Order Traverse:\n");
                tree.postorderTraverse(x -> {System.out.println(x); return null;});
            }
        break;
            case "props": {
                System.out.printf("Properties:\n");
                int size = tree.size();
                int height = tree.height();
                int diameter = tree.diameter();
                boolean fib = tree.isFibonacci();
                boolean comp = tree.isComplete();
                System.out.printf("size = %d, height = %d, diameter = %d\nfibonacci? = %s, complete? = %s\n", size, height, diameter, fib, comp);
            }
         break;
            case "gen": {
            	String n = in.next();
            	if(tree.inTree(n) == false) {
            		System.out.printf("Geneology:%s UNDEFINED \n", n);
            		continue;
            	}
                System.out.printf("\nGeneology: %s \n", n);
                	ArrayList<String> children = new ArrayList<>();
                	children = tree.getChildren(n);
                	String  leftChild = children.get(0);
                	String rightChild = children.get(1);
                	if(leftChild == null) {leftChild = "NONE";}
                	if(rightChild == null) {rightChild = "NONE";}
                    System.out.printf("Parent = %s, left-child = %s, right-child = %s\nancestors = %d, descendants = %d\n", 
                    		tree.getParent(n), leftChild, rightChild, tree.ancestors(n), tree.descendants(n));
                
            }
            break;
            default:
            	throw new IllegalArgumentException("File Parsing Error");
        }
        in.nextLine();
       }
       in.close();
       
}
}