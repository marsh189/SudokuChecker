

//checks the columns of the given grid to see if they are correct
public class CheckColumns implements Runnable
{
	private int[][] arrayToCheck = new int[9][9]; //the whole grid being checked
	private int[] columnArray = new int[9]; //column currently being checked
	int[] numCounter = new int[9]; //counts the amount of each number there are in each column
	int[] errors = new int[9]; //will have amount of errors in each column

	int[][] numbersMissing = new int[9][9]; //stores what number is missing in the column

	public CheckColumns(int[][] data)
	{
		this.arrayToCheck = data;
	}

	public void run()
	{
		int numErrors = 0;

		for (int x = 0; x < 9; x++)
		{
			for (int i = 0; i < 9; i++) 
			{
				numCounter[i] = 0;	//set all counters to 0
			}

			for (int y = 0; y < 9; y++) 
			{
				columnArray[y] = arrayToCheck[y][x];

				switch(columnArray[y]) //finds the amount of each number there are in the Column
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
						System.out.println("column " + (x+1) + " is not complete.");
						break;
				}
			}

			for (int j = 0; j <9; j++)
			{
				if(numCounter[j] > 1)
				{
					errors[x] += 1;
					numErrors++;
				}
				else if(numCounter[j] == 0)
				{
					numbersMissing[x][j] = 1;
				}
			}
			
		}
		
	}

	public int[] GetColumnErrors()
	{
		return errors;
	}
	public int[][] MissingColumnNumbers()
	{
		return numbersMissing;
	}
}