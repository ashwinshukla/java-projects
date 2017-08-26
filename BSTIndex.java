/*THIS  CODE  WAS MY OWN WORK ,
 * IT WAS  WRITTEN  WITHOUT  CONSULTING CODE
 *   WRITTEN  BY OTHER  STUDENTS. -Ashwin Shukla*/





public class BSTIndex {

    public class Node {  //defining node class. Each node contains a key, movie data and left and right child
        public String key;
        public MovieInfo data;
        public Node left;
        public Node right;

        public Node() {   //constructor, initalizing node
            this.key = key;
            this.data = data;
            left = null;
            right = null;
        }

    }

    Node root = new Node();  //creating root node, present as a part of the BSTindex class

    public BSTIndex() { //constructor, root set to null
        this.root = null;
    }


    public MovieInfo findExact(String key) {  //returns the Movie info that matches the exact search string.

        key = key.toLowerCase();  //change to key to lowercase to make comparison case-insensitive.
        Node currentNode = new Node();  //create pointer and set it = to the root
        currentNode = root; 

        while (true) {
            
            String checkString = currentNode.key;  //extract the current node's key
            checkString=checkString.toLowerCase(); //make it lowercase for comparison
                                        
            if(checkString.equalsIgnoreCase(key)){   //if current node's key equal to input key, regardless of case, return current node's data
                return currentNode.data;
            }

            if (checkString.compareTo(key) < 0) {  //condition to go left (current node's key< user input key)
                if (currentNode.left != null) {  //if left child isn't null, go left
                    currentNode = currentNode.left;

                } else {

                    return null;
                }

            } else if (currentNode.right != null) { //condition to go right
                currentNode = currentNode.right;  //if right child isn't null, go right


            } else {
                return null;

            }


        }

    }


    public MovieInfo findPrefix(String prefix) {

        prefix = prefix.toLowerCase();  //set prefix to lower case for case insensitive comparison
        prefix = prefix.replace("*", ""); //replace ** with nothing (delete stars)
        Node currentNode = new Node();  //pointer node, set = to root
        currentNode = root;

        while (true) {

            String checkString=currentNode.key;  //get the current node's key
            checkString=checkString.toLowerCase();  //make it lowercase

            if(checkString.length()>=prefix.length()) {  //if the current node's key is greater or = to the size of the prefix
                checkString=checkString.substring(0,prefix.length());  //cut of the extra parts of the current node's key with substring for comparison with prefix.
            }
            
            if(checkString.equalsIgnoreCase(prefix)){  //check if equal regardless of case, return data of current node if equal                
                return currentNode.data;
            }

            if (checkString.compareTo(prefix) < 0) {  //same as exact search, follow conditions to go left if lesser than input, if left child not null, go left
                if (currentNode.left != null) {
                    currentNode = currentNode.left;

                } else {
                    return null;
                }

            } else if (currentNode.right != null) {  //same except to go right (when current node's key> input)
                currentNode = currentNode.right;


            } else {
                return null;
            }


        }




    }


    public void insert(MovieInfo data) {  //Method to construct the BST. doesn't return anything

       //Inserter is the node used to insert the data in the BST.
        Node inserter = new Node();   //creating the node that will be inserted in the next appropriate null position. 
        inserter.key = data.shortName;  //extracting key from movie data element passed to method.
        inserter.data = data;   //adding data
        inserter.left = null;  //left and right children are null 
        inserter.right = null;



        if (root == null) {  //add inserter node (node representation of Movie data passed into the method) into the root if null
            root = inserter;

        } else { //if root not null
            Node currentNode = new Node(); //create a pointer node
            currentNode = root;  //set it = to the root
            while (true) { 

                if(currentNode.key.compareTo(inserter.key)==0){  //if there is a duplicate key, insert the current data into node position that has duplicate
                    currentNode=inserter;
                    break;  //stop the loop
                }

                if (currentNode.key.compareTo(inserter.key) < 0) {  // if pointer's key < key you are trying to insert, enter the conditional that lets you go left in BST
                    if (currentNode.left != null) {  //if the left child node of the pointer is not null, go left
                        currentNode = currentNode.left;

                    } else {
                        currentNode.left = inserter;  //if it is null, add the inserter node, data is now added to BST

                        break;  //stop the loop
                    }

                } else if (currentNode.right != null) {  //if pointer's key > key you are trying to insert, go to right conditional
                    currentNode = currentNode.right;  //same as for left, just go right instead.


                } else { //if it is null, add the inserter node.
                    currentNode.right = inserter;

                    break;  //stop the loop
                }


            }


        }


    }



}


















