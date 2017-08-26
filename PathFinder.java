/*
THIS CODE WAS MY OWN WORK , IT WAS WRITTEN WITHOUT CONSULTING
CODE WRITTEN BY OTHER STUDENTS . -Ashwin Shukla
*/
/**
 * Starter code for the Maze path finder problem.
 */

import java.io.*;
import java.util.Scanner;
import java.util.ArrayDeque;
import java.util.Arrays;

/*
 * Recursive class to represent a position in a path
 */
class Position{
	public int i;     //row
	public int j;     //column
	public char val;  //1, 0, or 'X'
	
	// reference to the previous position (parent) that leads to this position on a path
	Position parent;
	
	Position(int x, int y, char v){
		i=x; j = y; val=v;
	}
	
	Position(int x, int y, char v, Position p){
		i=x; j = y; val=v;
		parent=p;
	}
	
}


public class PathFinder {   
	
	public static void main(String[] args) throws IOException {
		if(args.length<1){
			System.err.println("***Usage: java PathFinder maze_file");
			System.exit(-1);
		}
		
		char [][] maze;
		maze = readMaze(args[0]);
		printMaze(maze);
		Position [] path = stackSearch(maze);
		System.out.println("stackSearch Solution:");
		printPath(path);
		printMaze(maze);
		
		char [][] maze2 = readMaze(args[0]);
		path = queueSearch(maze2);
		System.out.println("queueSearch Solution:");
		printPath(path);
		printMaze(maze2);
	}
	
	
	public static Position [] stackSearch(char [] [] maze){ 
		//Pathfinding algorithm using stack
		//Adds positions to Stack until path is found
		//Valid path is added to position array
		//returns path[] containing valid path
				
        ArrayDeque <Position> positions = new ArrayDeque< >();	//stack containing valid positions	
		int mazeRowSize = maze.length;		//row size of maze
		int mazeColSize = maze[0].length;   //col size of maze 							  
		Position entrancePosition = new Position(0,0,'X',null);  //constructing entrance position
		positions.push(entrancePosition);  //entrance position added to the stack
		
						
		
		char [][] mazeCopy = new char[maze.length][maze.length];  //Maze is copied into mazeCopy [][] to allow for marking of visited positions
		for(int i = 0; i < maze.length; i++){
			for(int j = 0; j < maze.length; j++){
                    char ch = maze[i][j];
                     mazeCopy[i][j]= ch;
            }
		} 
   
		while (positions.size() != 0) { 
		                                   //while stack is not empty    	
        	Position currentPos=positions.pop();  //current Pos is the currentPosition, which is the popped position
        	currentPos.val= 'X';  // the popped position is marked with X on the copied maze, to shows that it is visited
        	mazeCopy[currentPos.i][currentPos.j]= 'X';		

		    if(currentPos.i == maze.length-1 && currentPos.j == maze[0].length-1) { //if = exit position n-1 n-1

		        Position temp=currentPos;	//temp position to access linked list without deleting it	        	   		        	          		
		        int listSize=0; //size of linked list of positions

        		while(temp != null) {  //used to calculate size of linked list
        			listSize += 1;
        			temp = temp.parent;        				        				
        		}
        			
        			
        		int z=0; //path [] counter
        		Position[] path=new Position[listSize]; //initialzing path[] containing positions that make up the solution

        		while(currentPos != null) { //positions in LL added to path[]
        				
        			maze[currentPos.i][currentPos.j]='X';  //mark the positions of the final answer with X
        			path[z]=currentPos;
       				currentPos=currentPos.parent;        				
        			z++;

	        	}
        			
	        	return path;  //path [] returned

			}else{  //if not exit position
                                                        //currentRowPosition and currentColPosition variables get these positions and store them for later calculations 
		  	    int currentRowPosition= currentPos.i; //calculations for up, down, left and right positions from current positions.
		  	    int currentColPosition = currentPos.j;				 				  						    					  												  	   					    					  				    			  	    	    int newLeft=currentRowPosition-1;
			  	int newRight=currentRowPosition+1;
			  	int newDown=currentColPosition+1;
			  	int newUp=currentColPosition-1;			  	    	    
 			  	boolean leftOk=false;	//checks if the Position is valid and not previously pushed to the position stack		  	    	   
			  	boolean rightOk=false;			  	    
			  	boolean downOk=false;			  	    	  
			  	boolean upOk=false;			  	    	  
			  	    	   			  	  
			  	if(newLeft>=0 && newLeft<mazeRowSize) {		//is position left of current position within bounds, a new postion and '0' i.e. not a wall?	  	    		
			  	   if(mazeCopy[newLeft][currentColPosition]=='0') {  //if yes, it can be pushed to the positions stack
			  	      leftOk=true;
			  	    	    	  
			  	    }
			  	    	 
			  	}
			  	    	 			  	  	 			  	    	    			  	    			  	    	    			  	    	 
			  	if(leftOk==true) {  //if the position can be pushed to the stack, create a new position object with the co-ordinates
			  		//push it and mark it as X i.e visited in the copy of the maze
			  	   Position leftPosition= new Position(newLeft, currentColPosition, 'X', currentPos );				  	    	   
			  	   positions.push(leftPosition);
			  	}
			  	    	  
	  	    	   
			  	if(newRight>=0 && newRight<mazeRowSize) { // same logic, but for position right of current position			  	   
			  	    if(mazeCopy[newRight][currentColPosition] == '0') {
			  	    	rightOk=true;				  	    	    			  	    	    	 	  	    	    	 
			  	    }			  	    											  	    	   			  	    	  			  	    	 
			   }     
			  	  
			  	    
	  	    	if(rightOk==true) {
	  	    	    Position rightPosition= new Position(newRight, currentColPosition, 'X', currentPos );				  	    	    
	  	    	    positions.push(rightPosition);	  	    	  	  	    	    	
	  	    	}
	  	    	    

	  	    	    
	  	   		if(newDown>=0 && newDown<mazeColSize) {  // same logic, but for position below current position	
	  		 		if(mazeCopy[currentRowPosition][newDown] =='0') {
	  	    	    	downOk=true;			  	    	  			  	    	  
	  	    	    } 	    	   
	  	    	  	
	  	   		}
	  	    	   
	  	  			  	   
	  	    	if(downOk==true) {
	  	    	    Position downPosition= new Position(currentRowPosition, newDown, 'X', currentPos );		  	    	   
	  	    	    positions.push(downPosition);
	  	    	   				  	    	  
	  	    	}
	  	    	    
			  	if(newUp>=0 && newUp<mazeColSize) {	  // same logic, but for position above current position

			 		if(mazeCopy[currentRowPosition][newUp]=='0') {
	  	    	    	upOk=true;
	  	    	    	
	  	    	    }			  	    	  
			  	}  	
			  
				if(upOk==true) {
	  	    	    Position upPosition= new Position(currentRowPosition, newUp, 'X', currentPos );				  	    	    	
	  	    	    positions.push(upPosition);			  	    	   
	  	    	    	
	  	    	}
			  	    	    			  	    	    			  	    	   			  	    	    			  	    	 			  	    	  			  	   		  	    	     				  		  
		    }
								  	
	    }
											
				
		return null;

	}
	
	
	public static Position [] queueSearch(char [] [] maze){  
        
       //This method uses the exact same logic as the stack method but uses the .add and .remove methods,
	   //to add elements to the back of the queue and remove elements from the front of the queue.
		//All pop methods have been replaced with remove and all push methods have been replaced with add.

				
	 	ArrayDeque<Position> positions = new ArrayDeque<>();		
		int mazeRowSize=maze.length;		
		int mazeColSize=maze[0].length;							  
		Position entrancePosition = new Position(0,0,'X',null);
		positions.add(entrancePosition);
													
		char [][] mazeCopy = new char[maze.length][maze.length];
		for(int i = 0; i < maze.length; i++) {
			for(int j = 0; j < maze.length;  j++) {
	            char ch = maze[i][j];
	            mazeCopy[i][j]= ch;
	        }
		} 

	   
	    while(positions.size() != 0) {	        	
	        Position currentPos = positions.remove();
	        currentPos.val= 'X';
	        mazeCopy[currentPos.i][currentPos.j]= 'X';
				    
			if(currentPos.i == maze.length-1 && currentPos.j == maze[0].length-1) {
		      	Position temp=currentPos;			        	   
   	  			int listSize=0;

        		while(temp !=null) {
        			listSize+=1;
        			temp=temp.parent;        				        				
        		}
        			      			
        		int z=0;
        		Position[] path = new Position[listSize];
        		while(currentPos != null) { 	        				
        			maze[currentPos.i][currentPos.j]='X';
        			path[z]=currentPos;
       				currentPos=currentPos.parent;
        			z++;
        	    }
        			
        		return path;
		
			}else{ 

		  		int currentRowPosition= currentPos.i; 
		  		int currentColPosition = currentPos.j;
				 
			    int newLeft=currentRowPosition-1;
			  	int newRight=currentRowPosition+1;
			  	int newDown=currentColPosition+1;
			  	int newUp=currentColPosition-1;
			  	    				  	    	    
			  	boolean leftOk=false;			  	    	   
			  	boolean rightOk=false;			  	    
			  	boolean downOk=false;			  	    	  
			  	boolean upOk=false;			  	    	  
			  	    	   			  	  
			  	if(newLeft>=0 && newLeft < mazeRowSize) {			  	    		
			  	   	if(mazeCopy[newLeft][currentColPosition]=='0') {
			  	    	leftOk=true;
			  	    	    
			  	   	}
			  	    	 
			  	}
			  	    	 
	    			  	    	 
			  	if(leftOk==true) {
			  	    Position leftPosition= new Position(newLeft, currentColPosition, 'X', currentPos );	
  	    	  	    positions.add(leftPosition);
			  	    	   
			  	}
			  	    	  
			  	    	
			  	if(newRight>=0 && newRight<mazeRowSize) {				  	    
			  	    if(mazeCopy[newRight][currentColPosition] == '0') {
			  	    	rightOk=true;	
			  	    	    				  	    	    	 	  	    	    	 
			  	    }
			  	}     
			  	  
   				if(rightOk==true) {
			  	    Position rightPosition= new Position(newRight, currentColPosition, 'X', currentPos );					  	   
			  	   	positions.add(rightPosition);
			  	    	  				  	    	    	
			  	}
			  	    	    
			  	    	    

			  	if(newDown>=0 && newDown<mazeColSize) { 					  		 	
			  	    if(mazeCopy[currentRowPosition][newDown] =='0') {
			  	    	downOk=true;				  	    	   				  	    	  
			  	   	} 	    	   
			  	    	  	 
			  	}
			  	    	   
			  	  			  	   
				if(downOk==true) {
			  	  	Position downPosition= new Position(currentRowPosition, newDown, 'X', currentPos );					  	    	    	
			  	    positions.add(downPosition);				  	    	  	
			  	}
			  	    	    
	   			if(newUp>=0 && newUp<mazeColSize) {	
	   			 	
			  	    if(mazeCopy[currentRowPosition][newUp]=='0') {
			  	    	upOk=true;
			  	    	   
			  	  	}
			  	    	
	   			}  	
	   			  
	   			
	   			  
			  	if(upOk==true) {
			  	   	Position upPosition= new Position(currentRowPosition, newUp, 'X', currentPos );	
			  	   	positions.add(upPosition);				  	    	 				  	    	    	
			  	}

			}
									  	
		}
									    

		return null;
	}
	
	
	
	
	public static void printPath(Position [] path){
		

		String answer= "Path:("; //Initalizes string that represents all of the elements in the path[] (the answer)

		for(int k = path.length-1; k >= 0; k --) {   //accessing each element in the array	
			                                        	
			String rowResult= "[" + path[k].i	+ "]"; //getting row value
			String colResult= "[" +path[k].j + "], ";   //getting col value
			
			answer += rowResult + colResult;	//adding row and col to values to the string representation					
		}
		
	    answer+= ")";		
		System.out.println(answer);
			
	}		
       
	
	
	/**
	 * Reads maze file in format:
	 * N  -- size of maze
	 * 0 1 0 1 0 1 -- space-separated 
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	public static char [][] readMaze(String filename) throws IOException{
		char [][] maze;
		Scanner scanner;
		try{
			scanner = new Scanner(new FileInputStream(filename));
		}
		catch(IOException ex){
			System.err.println("*** Invalid filename: " + filename);
			return null;
		}
		
		int N = scanner.nextInt();
		scanner.nextLine();
		maze = new char[N][N];
		int i=0;
		while(i < N && scanner.hasNext()){
			String line =  scanner.nextLine();
			String [] tokens = line.split("\\s+");
			int j = 0;
			for (; j< tokens.length; j++){
				maze[i][j] = tokens[j].charAt(0);
			}
			if(j!=N){
				System.err.println("*** Invalid line: " + i + " has wrong # columns: " + j);
				return null;
			}
			i++;
		}
		if(i!=N){
			System.err.println("*** Invalid file: has wrong number of rows: " + i);
			return null;
		}
		return maze;
	}
	
	public static void printMaze(char[][] maze){
		
		if(maze==null || maze[0] == null){
			System.err.println("*** Invalid maze array");
			return;
		}
		
		for(int i=0; i< maze.length; i++){
			for(int j = 0; j< maze[0].length; j++){
				System.out.print(maze[i][j] + " ");	
			}
			System.out.println();
		}
		
		System.out.println();
	}

}
