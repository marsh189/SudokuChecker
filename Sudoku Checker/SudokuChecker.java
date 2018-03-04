import java.io.*;
import java.util.*;

public class SudokuChecker
{
	
	public static void main(String[] args)
	{
		System.out.println();
		Scanner input = new Scanner(System.in);
		System.out.print("Enter the File Name to check: ");
		String in = input.next();

		System.out.println();
		ReadFile reader = new ReadFile();
		int[][] grid = reader.ReadInFile(in);

		//making threads for rows of columns
		CheckRows rowCheck = new CheckRows(grid);
		CheckColumns colCheck = new CheckColumns(grid);

		CheckSquare[] squareChecker = new CheckSquare[9];

		//making threads for shquares
		squareChecker[0] = new CheckSquare(grid, 0,0,2,2,1);
		squareChecker[1] = new CheckSquare(grid, 0,3,2,5,2);
		squareChecker[2] = new CheckSquare(grid, 0,6,2,8,3);
		squareChecker[3] = new CheckSquare(grid, 3,0,5,2,4);
		squareChecker[4] = new CheckSquare(grid, 3,3,5,5,5);
		squareChecker[5] = new CheckSquare(grid, 3,6,5,8,6);
		squareChecker[6] = new CheckSquare(grid, 6,0,8,2,7);
		squareChecker[7] = new CheckSquare(grid, 6,3,8,5,8);
		squareChecker[8] = new CheckSquare(grid, 6,6,8,8,9);

		//start Threads
		Thread rowThread = new Thread(rowCheck);
		Thread colThread = new Thread(colCheck);
		rowThread.start();
		colThread.start();

		Thread[] squareThreads = new Thread[9];
		for (int i = 0; i < 9; i++) 
		{
			squareThreads[i] =  new Thread(squareChecker[i]);
			squareThreads[i].start();	
		}

		try
		{
			rowThread.join();
			colThread.join();
			for (int j = 0; j < 9; j++) 
			{
				squareThreads[j].join();	
			}

		}
		catch(Exception e)
		{
			System.out.println("Thread joining Error");
		}


		int[] rowErrors = rowCheck.GetRowErrors();
		int[] colErrors = colCheck.GetColumnErrors();

		int[] squareErrorCount = new int[9];

		int[][] rowMissing = rowCheck.MissingRowNumbers();
		int[][] columnMissing = colCheck.MissingColumnNumbers();
		int rS = 0,cS = 0;
		int rE = 0, cE = 9;
		int count = 0;
		int x,y,z = 0;
		boolean foundError = false;

		for (int a = 0; a < 9; a++) 
		{
			squareErrorCount[a] = squareChecker[a].GetErrorsInSquare();
			if(squareErrorCount[a] > 0) //check if the square has an error
			{
				switch(a) //set coordinates of square to find error
				{
					case 0:
						rS = 0;
						rE = 3;
						cS = 0;
						cE = 3;
						break;
					case 1:
						rS = 0;
						rE = 3;
						cS = 3;
						cE = 6;
						break;
					case 2:
						rS = 0;
						rE = 3;
						cS = 6;
						cE = 9;
						break;
					case 3:
						rS = 3;
						rE = 6;
						cS = 0;
						cE = 3;
						break;
					case 4:
						rS = 3;
						rE = 6;
						cS = 3;
						cE = 6;
						break;
					case 5:
						rS = 3;
						rE = 6;
						cS = 6;
						cE = 9;
						break;
					case 6:
						rS = 6;
						rE = 9;
						cS = 0;
						cE = 3;
						break;
					case 7:
						rS = 6;
						rE = 9;
						cS = 3;
						cE = 6;
						break;
					case 8:
						rS = 6;
						rE = 9;
						cS = 6;
						cE = 9;
						break;
					default:
						break;
				}

				//loop through the square
				for(x = rS; x < rE; x++)
				{
					for(y = cS; y < cE; y++)
					{
						//loop through numbers 1-9 to check if they are missing from square
						for(z = 0; z < 9; z++)
						{
							//checks if row and column are missing the same number
							if(rowMissing[x][z] == 1 && columnMissing[y][z] == 1)
							{
								System.out.println("Coordinate (" + (x+1) + "," + (y+1) + ") should be " + (z+1));
								foundError = true;
							}
						}
					}
				}

				//rows and columns are not missing the same number, but still can have error
				if(!foundError)
				{
					for(x = rS; x < rE; x++)
					{
						for(z = 0; z < 9; z++)
						{
							if(rowMissing[x][z] == 1)
							{
								System.out.println("Row " + (x + 1) + " is missing a " + (z+1));
							}
						}
					}

					for(y = cS; y < cE; y++)
					{
						for(z = 0; z < 9; z++)
						{
							if(columnMissing[y][z] == 1)
							{
								System.out.println("Column " + (y + 1) + " is missing a " + (z+1));
							}
						}
					}
				}
			}
			else //no errors found in square, but there still can be errors
			{
				count++;
			}
		}

		if(count == 9) //check rows and columns individually for errors
		{
			for(x = 0; x < 9; x++)
			{
				for(z = 0; z < 9; z++)
				{
					if(rowMissing[x][z] == 1)
					{
						System.out.println("Row " + (x + 1) + " is missing a " + (z+1));
						foundError = true;
					}
				}
			}

			for(y = 0; y < 9; y++)
			{
				for(z = 0; z < 9; z++)
				{
					if(columnMissing[y][z] == 1)
					{
						System.out.println("Column " + (y + 1) + " is missing a " + (z+1));
						foundError = true;
					}
				}
			}

			if(!foundError)
			{
				System.out.println("There were no errors found. This is a Valid Sudoku Solution!");
			}
		}
	}
}