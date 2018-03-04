

//checks the given grid's rows to see if they are correct
public class CheckRows implements Runnable
{
	private int[][] arrayToCheck = new int[9][9]; //the whole grid being checked
	private int[] rowArray = new int[9]; //row currently being checked
	int[] numCounter = new int[9]; //counts the amount of each number there are in each row
	int[] errors = new int[9]; //will have amount of errors in each row

	int[][] numbersMissing = new int[9][9]; //stores what number is missing in the row

	public CheckRows(int[][] data)
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
				rowArray[y] = arrayToCheck[x][y];

				switch(rowArray[y])
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
						System.out.println("Row " + (x+1) + " is not complete.");
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
				else
				{
					errors[j] = 0;
				}
			}
			
		}
		
	}

	public int[] GetRowErrors()
	{
		return errors;
	}
	public int[][] MissingRowNumbers()
	{
		return numbersMissing;
	}
}