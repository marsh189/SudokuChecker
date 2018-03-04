import java.io.*;
import java.util.*;

public class ReadFile
{
	public int[] setData(String line)
	{
		int[] numbers = new int[9];
		String[] lineArr = line.split(",");
		for(int i = 0; i < 9; i++)
		{
			numbers[i] = Integer.parseInt(lineArr[i]);
		}
		return numbers;
	}

	public int[][] ReadInFile(String fileName)
	{
		
		try 
		{
			int[][] grid = new int[9][9];
			Scanner sc = new Scanner(new File(fileName));
			int x = 0; //current row
			while(sc.hasNextLine())
			{
				int[] lineArr = setData(sc.nextLine());

				for (int y = 0; y < 9; y++) //loop through columns of row
				{
					grid[x][y] = lineArr[y];	
				}
				x++;
			}

			return grid;
		}
		catch(IOException ex) 
		{
			System.out.println("There has been an IO error.");
			return new int[9][9];
		}
	}
}