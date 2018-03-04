

//Splits grid into 3x3 squares to check if each has one of each number
public class CheckSquare implements Runnable
{

	private int[][] arrayToCheck = new int[3][3];
	private int[] numCounter = new int[9]; 
	private int startRow;
	private int startColumn;
	private int endRow;
	private int endColumn;
	private int squareNumber;
	int numErrors;

	public CheckSquare(int[][] data, int startX, int startY, int endX, int endY, int num)
	{
		arrayToCheck = data;
		startRow = startX;
		startColumn = startY;
		endRow = endX;
		endColumn = endY;
		squareNumber = num;
	}

	public void run()
	{
		numErrors = 0;
		int y = startColumn;
		int x = startRow;
		int index = 0;
		int[] currLine = new int [9];

		while(y <= endColumn)
		{
			while(x <= endRow)
			{
				currLine[index] = arrayToCheck[x][y];
				x++;
				index++;
			}

			x = startRow;
			y++;
		}

		for (int i = 0; i < 9; i++) 
		{
			switch(currLine[i])
			{
				case 1:
					numCounter[0] += 1;
					break;
				case 2:
					numCounter[1] += 1;
					break;
				case 3:
					numCounter[2] += 1;
					break;
				case 4:
					numCounter[3] += 1;
					break;
				case 5:
					numCounter[4] += 1;
					break;
				case 6:
					numCounter[5] += 1;
					break;
				case 7:
					numCounter[6] += 1;
					break;
				case 8:
					numCounter[7] += 1;
					break;
				case 9:
					numCounter[8] += 1;
					break;
				default:
					System.out.println("Square " + squareNumber + " is not complete.");
					break;
			}
		}

		for (int j = 0; j <9; j++)
		{
			if(numCounter[j] > 1)
			{
				numErrors++;
			}
		}
	}

	public int GetErrorsInSquare()
	{
		return numErrors;
	}
}
