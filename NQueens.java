

/*
THIS CODE WAS MY OWN WORK , IT WAS WRITTEN WITHOUT CONSULTING
CODE WRITTEN BY OTHER STUDENTS . -Ashwin Shukla
*/

import java.util.Stack;
import java.math.*;   //for using absolute value in isAllowed method.

public class NQueens {
 
  
   
   	public static boolean isEmpty (Stack <Integer> s) { //checks if the stack is empty. returns  a boolean - true if empty (size 0) and false if not.

   		if(s.size() == 0){  //to check if stack is empty
   			return true;
   		}

 		return false;
 	}   	
	
  	
					
  	public static boolean isAllowed (Stack <Integer> s, int position, int n, int loopcounter ) { //checks if the current position is allowed. (No conflicts diagonally or vertically) 
    //returns boolean false if not allowed, true if allowed.

		int i = 0;  //counter variable intialized
       
       	if(loopcounter > 0){  //to prevent validating a position when a temporary Integer is pushed. See full explanation in solve method.
		
		
			while(i < s.size()){  //to check all values currently present in the stack.
					
			 	if(Math.abs(position-s.get(i)) == Math.abs(s.size()-i)) {  //checks diagonals, false is conflict (not Allowed). Absolute value is for case when col>row to prevent negative values
		    	 	return false;
		    	}
			 
			 	if(position==s.get(i)) {  // checks columns for conflict. rows do not need to be checked because there will only be one queen per row by default
					return false;
				}

				if(position>=n){  //if the current column position is beyond the nth position of the board, it is invalid.
				return false;

				}         			
			
				i++;

			}

    	}
      
    	return true;
  
	}



  	public static int solve(int n) { //core logic with backtracing implentation. Returns int that represents the number of solutions
	  	Stack <Integer> s = new Stack <Integer> (); //stack containing valid integer positions.
		int solutions = 0;  // number of solutions
		int position = 0; //the Queen's column position- for each row
		int loopcounter = 0; //counts the number of iterations of the inner while loop
		boolean incomplete = true; //used to create an infinite loop, to find all solutions. The loop is broken when the size of the stack is zero (no further solutions found)

	  	while(incomplete == true){ //outer infinite loop to find all solutions
		  	  	  
			if(loopcounter == 0){ //pushes a temporary value to the stack to prevent emptystack exception when the inner loop is called for the first time
		 		s.push(0);
		 	}
	 	   
			while(s.get(0)<n && isEmpty(s) == false) { //inner loop. terminates when the first queen on the board's position equals n,or the stack is empty. both represent no further soltions found.
			 

			  	if(loopcounter == 0 && isEmpty(s) == false){  //used to pop the temporary value on the first loop iteration.
			  		s.pop();	  		     
			  	}
		   
			  			 
				if(isAllowed (s, position, n, loopcounter) && position <n) { //checks to see if the current column position in a row is allowed. if it is, the position is pushed to stack. 
					s.push(position);                                         //otherwise, it is incremented by one to check for the next soltion.
					position = 0;                                             //the queen in the next row starts off at position zero.

				}else if(position <n){			   
			       position = position + 1;

				}	

		        
				if(position == n && isEmpty(s) == false) {	//backtracking portion of method. if a queen's column position in a row reaches value 'n', no further solutions are possible and the method	 	   
					position = s.pop() +1;                   //backtracks to the previous row's queen position +1.
				           
		        	if(position<n && isAllowed(s, position, n, loopcounter)) { //if the position after popping the previous queen is allowed, it is added to the stack           	
		              s.push(position);                                        //and the next queen starts at position zero.
		              position = 0;             

		          	}
		                        
				}
		        

				if(s.size() == 0) {   //if no solutions are found the inner loop breaks
					break;
				}


				if(s.size() == n){    //if the stack is size n, a solution has been found, the inner loop breaks so the solution can be printed.
				  	break;		  	
				}

				loopcounter = loopcounter +1; //counts the number of iterations of the loop. incremented by one here.
			  		  		  
			}  

			if(n == s.size()) {  //once the stack is size n, it is printed and the number of solutions is incremented by one.
				printSolution(s);   //the queen in the last row is popped, and the queen's position in the second to last row is incremented by one.
				solutions = solutions +1;
				position = s.pop() +1;
			}		 

		    if(s.size()==0){  //condition to break the outer while loop if no solutions are found after this entire process.
		    	incomplete=false;
		    }

		}
            
		return solutions;  //returns the number of solutions found (int).
        
	}


  
  //this method prints out a solution from the current stack
  //(you should not need to modify this method)
  private static void printSolution(Stack<Integer> s) {
    for (int i = 0; i < s.size(); i ++) {
      for (int j = 0; j < s.size(); j ++) {
        if (j == s.get(i))
          System.out.print("Q ");
        else
          System.out.print("* ");
      }//for
      System.out.println();
    }//for
    System.out.println();  
  }//printSolution()
  
  
 
  
  // ----- the main method -----
  // (you shouldn't need to change this method)
  public static void main(String[] args) {
  
  int n = 8;

  // pass in parameter n from command line
  if (args.length == 1) {
    n = Integer.parseInt(args[0].trim());
    if (n < 1) {
      System.out.println("Incorrect parameter");
      System.exit(-1);
    }//if   
  }//if
  
  int number = solve(n);
  System.out.println("There are " + number + " solutions to the " + n + "-queens problem.");
 }//main()
  
}








