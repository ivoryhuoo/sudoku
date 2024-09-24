/**
 * This class is used to represent a sudoku containing integers in a 2D array
 * @author ivoryhuo
 *
 */

public class Sudoku {

	//Provided private instance variables to implement
	/**
	 * Represents the size of the grid, assuming it will always be a square
	 */
	private int size;
	/**
	 * Represents the grid containing all the digits (a 2D array)
	 */
	private int[][]grid; 
	
	
	//Provided public methods to implement
	/**
	 * Constructor that initializes the instance variables size and grid given the parameter numbers
	 * @param numbers
	 */
	public Sudoku (int[][] numbers) {
		
		//size initializes by getting length of the numbers array parameters
		size = numbers.length;
		
		//assign to create shallow copy (2D)
		//same address
		grid = numbers;
		
		//THIS IS A DEEP COPY
		//create new 2D array with dimensions of size x size to store the Sudoku grid 
		//iterate through each row and column of the numbers array (create shallow copy) - 2D array 
		/*
		for (int i = 0; i < size; i++) { 
			for (int j = 0; j < size; j++) {
				//copy the values from the numbers array at i, j to the corresponding position in grid array 
				grid[i][j] = numbers[i][j];
			}
		}*/
		
	}
	
	/**
	 * Method that returns the size of the grid, assuming it is a square
	 * @return the size of the grid (assuming square)
	 */
	public int getSize() {
		return size; 
	}
	
	/**
	 * Method that returns the grid containing all the digits 
	 * @return the grid containing all the digits
	 */
	public int[][] getGrid() {
		return grid;
	}
	
	/**
	 * Method that returns the digit stored in the grid at the given row and col indices
	 * @param row
	 * @param col
	 * @return the digit stored in the grid at the given row and col indices
	 */
	public int getDigitAt (int row, int col) {
		
		//If either of the indices are out of range (i.e. less than 0 or larger than 01), return -1
		if (row < 0 || row >= size || col < 0 || col >= size ) {
			return -1; //Out of range
		}
		//If the indices are within range (not in the if statement, default to the following
		//Retuning digit stored in the grid at the given row and col 
		return grid[row][col];
		
	}
	
	/**
	 * Method that determines if the row at index row in the sudoku is valid 
	 * @param row
	 * @return true if it is valid or false otherwise 
	 */
	public boolean isValidRow (int row) {
		
		//First condition, if the row index is out of range, return false
		if (row < 0 || row >= size) { //
			return false;
		}
		
		//Create array to keep track of digits featured in the row
		boolean[] check = new boolean[size];
		
		//Iterate through each column in the row 
		for (int col = 0; col < size; col++) {
			//get the digit at the current row and column position
			int digit = grid[row][col];
			
			//check if the digit is out of the valid range (less than 1 or greater than size)
			if (digit < 1 || digit > size || check[digit - 1]) {
				return false; //if it is outside of the range we want, return false
			}
			
			//if the digit is not out of range, mark the digit as checked 
			check[digit - 1] = true;
			
		}
		
		//After iterating through each column in the row, check if any digits are missing
		for (int i = 0; i < size; i++) {
			if (!check[i]) { //if the number is NOT checked, it is missing digit, so row is invalid 
				return false; 
			}
		}
		
		//If none of the conditions are met, the row is valid 
		return true; 
	}
	
	/**
	 * Method that determines if the col at index col in the sudoku is valid
	 * @param col
	 * @return true if it is valid or false otherwise
	 */
	public boolean isValidCol (int col) {
		//First condition, if the column index is out of range, return false
		if (col < 0 || col >= size) { //
			return false;
		}
				
		//Create array to keep track of digits featured in the column
		boolean[] check = new boolean[size];
				
		//Iterate through each row in the column 
		for (int row = 0; row < size; row++) {
			//get the digit at the current row and column position
			int digit = grid[row][col];
					
			//check if the digit is out of the valid range (less than 1 or greater than size)
			if (digit < 1 || digit > size || check[digit - 1]) {
				return false; //if it is outside of the range we want, return false
			}
				
			//if the digit is not out of range, mark the digit as checked 
			check[digit - 1] = true;
					
		}
				
		//After iterating through each row in the column, check if any digits are missing
		for (int i = 0; i < size; i++) {
			if (!check[i]) { //if the number is NOT checked, it is missing digit, so column is invalid 
				return false; 
			}
		}
				
		//If none of the conditions are met, the column is valid 
		return true; 		
	}
	
	/**
	 * Method that determines if the 3x3 box whose top-left corner is at index row, col, in the sudoku is valid
	 * @param row
	 * @param col
	 * @return true if it is valid or false otherwise
	 */
	public boolean isValidBox (int row, int col) {
		//First condition, if the row or column index is out of range, return false
		if (row < 0 || row >= size || col < 0 || col >= size) { //
			return false; //box is invalid
		}
						
		//Create array to keep track of digits featured in the row and column
		boolean[] check = new boolean[size];
						
		//Iterate through the 3x3 box
		for (int i = row; i < row + 3; i++) {
	        for (int j = col; j < col + 3; j++) {
	        	//get the digit at the current position
	            int digit = grid[i][j];
	           
	            //check if the digit is out of the valid range (less than 1 or greater than 9) or if it has been seen before in the same box
	            if (digit < 1 || digit > 9 || check[digit - 1]) {
	                return false; // Invalid digit or duplicate digit, so the box is invalid.
	            }
	            check[digit - 1] = true; //mark digit as checked if true
	        }
	    }
						
		//After iterating through each row in the column, check if any digits are missing
		for (int i = 0; i < size; i++) {
	        if (!check[i]) {
	            return false; //if the number is NOT checked, it is missing digit, so box is invalid 
	        }
	    }

	    //If none of the conditions are met, the box is valid 
		return true; 	
	}
	
	/**
	 * Determine if entire sudoku is valid 
	 * @return true if sudoku is valid, false otherwise 
	 */
	public boolean isValidSolution() {
		//check through each row and column for its validity
		for (int i = 0; i < size; i++) {
	        if (!isValidRow(i) || !isValidCol(i)) { //use the already created methods that check
	            return false; //row or column is not valid
	        }
	    }

		//If the sudoku is size 9, also call idValidBox for all nine 3x3 boxes 
	    if (size == 9) {
	        //check the 3x3 boxes for size 9 Sudoku
	        for (int row = 0; row < size; row += 3) {
	            for (int col = 0; col < size; col += 3) {
	                if (!isValidBox(row, col)) {
	                    return false; //box is not valid if at least one condition is not met 
	                }
	            }
	        }
	    }

	    return true; //Sudoku is valid
	}
	
	/**
	 * Method that sees if the 'this' sudoku is identical to the other sudoku
	 * @param other
	 * @return true if they are identical, false otherwise 
	 */
	public boolean equals (Sudoku other) {
		//Size of each sudoku AND digits in grid of each must all be equal in the same position
		//check if other is null or the sizes are different 
		if (other == null || size != other.size) {
	        return false; //sizes are different, so they can't be identical
	    }

	    //Compare each digit in the grid to see if they are equal 
	    for (int i = 0; i < size; i++) { 
	        for (int j = 0; j < size; j++) {
	        	//compare digits at position (i,j) in the 'this' grid with the digit at the same position on 'other' grid
	            if (grid[i][j] != other.grid[i][j]) {
	                return false; //grids are not identical if they do not match
	            }
	        }
	    }


	    //If the size of each grid are equal AND the digits in the grid of each are all in the same position
	    return true; //Grids are identical
	}
	
	/**
	 * Method that builds and returns a string containing all the digits in the grid 
	 * @return string representation of the sudoku grid 
	 */
	public String toString() {
		//Return with a single space after each digit and a newline character (\n) at the end of each row (after the space that follows the final digit in the row)
		
		//initialize empty string to hold the sudoku grid
		String result = "";

	    for (int i = 0; i < size; i++) { //iterate through each row of the sudoku grid
	        for (int j = 0; j < size; j++) { //iterate through each column of the sudoku grid 
	            result += grid[i][j] + " "; //add the current digit from the grid to the string with an added space after each digit 

	            //add a newline character (\n) at the end of each row 
	            if (j == size - 1) { //if it is at the end of the row 
	                result += "\n"; //newline character
	            }
	        }
	    }

	    return result; //return the string representation of the sudoku grid
	}
}

