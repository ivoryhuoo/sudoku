/**
 * This class is used to represent a sudoku in which the digits along one or both of the main diagonals must be unique (no repeats) to be considered a valid solution
 * @author ivoryhuo
 *
 */
public class UniqueDiagonalSudoku extends Sudoku {

	/**
	 * Constructor that calls the Sudoku constructor 
	 * @param numbers
	 */
	public UniqueDiagonalSudoku (int[][] numbers) {
		super(numbers); //call the parent constructor 
	}
	
	/**
	 * Method that overrides the isValidSolution() method in the Sudoku class
	 */
	@Override
    public boolean isValidSolution() {
        
		//Call the parent class' isValidSolution() to check standard rules 
		if (!super.isValidSolution()) {
	        return false; //if the sudoku isn't even considered a valid sudoku on it's own, there's no way it can be considered a unique diagonal sudoku --> eliminate entirely 
	    }

	    int size = getSize(); //Get the size of the sudoku grid 
	    boolean diagonal1Valid = true; //track main diagonal 1 (top left to bottom right)
	    boolean diagonal2Valid = true; //track main diagonal 2 (top right to bottom left)

	    //Initialize arrays to track number occurrences in each diagonal
	    boolean[] diagonal1Digits = new boolean[size]; //separate array for diagonal 1
	    boolean[] diagonal2Digits = new boolean[size]; //separate array for diagonal 2

	    //Iterate through the diagonal 
	    for (int i = 0; i < size; i++) {
	        int digit1 = getDigitAt(i, i); //get digit from diagonal 1 --> start location is 0,0 index (top left corner)
	        int digit2 = getDigitAt(i, size - 1 - i); //get digit from diagonal 2 --> start location is 0, size-1 index (top right corner)

	        //Check if the digits are within the valid range (1 to size)
	        if (digit1 < 1 || digit1 > size || digit2 < 1 || digit2 > size) {
	            return false; //if they don't belong in the valid, range, they are not part of the unique diagonal solution 
	        }

	        //Check if digits are unique in each diagonal
	        if (diagonal1Digits[digit1 - 1]) {
	            diagonal1Valid = false; //mark main diagonal 1 as invalid if there is a duplicate 
	        } else {
	            diagonal1Digits[digit1 - 1] = true; //if not, the diagonal is valid 
	        }
	        if (diagonal2Digits[digit2 - 1]) { 
	            diagonal2Valid = false; //mark main diagonal 2 invalid if a duplicate is found 
	        } else {
	            diagonal2Digits[digit2 - 1] = true; //if not, the diagonal is valid 
	        }
	    }

	    //FOR A UNIQUE DIAGONAL TO BE CONSIDERED A VALID SOLUTION, IT REQUIRES AT LEAST ONE OF ITS MAIN DIAGONALS TO CONTAIN ALL UNIQUE DIGITS FROM 1 TO N
	    //THE OTHER DIAGONAL DOES NOT HAVE TO BE UNIQUE (KEYWORD AT LEAST ONE OF THE MAIN DIAGONALS)
	    return diagonal1Valid || diagonal2Valid;
    
    }

	
}
