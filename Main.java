import java.util.Scanner;
import java.util.Stack;

public class Main {

	static int moveCount = 0;
	static Stack<Box>[] stackArray = new Stack[3];
	
	static void printRods(Stack<Box>[] stackArray, int noOfBoxes)
	{
		System.out.println();
		for(int i=noOfBoxes-1; i>=0 ; i--)
		{
			try
			{
				System.out.print(stackArray[0].get(i).boxID + "\t");
			}
			catch(Exception ArrayIndexOutOfBoundsException)
			{
				System.out.print(" " + "\t");
			}
			try
			{
				System.out.print(stackArray[1].get(i).boxID + "\t");
			}
			catch(Exception ArrayIndexOutOfBoundsException)
			{
				System.out.print(" " + "\t");
			}
			try
			{
				System.out.print(stackArray[2].get(i).boxID + "\t");
			}
			catch(Exception ArrayIndexOutOfBoundsException)
			{
				System.out.print(" " + "\t");
			}
			System.out.println();
		}
		System.out.println("-----------------------");
		System.out.println("Rod 1\tRod 2\tRod 3");
	}
	public static void main(String[] args) {
		
		Scanner read = new Scanner(System.in);
		System.out.println("Starting Game....");
		System.out.print("Enter no of Boxes: ");
		int noOfBoxes = read.nextInt();
		System.out.println("Setting up game....");
		gameSetupOnStart(noOfBoxes, stackArray);
		System.out.println("Setup Done.");
		printRods(stackArray, noOfBoxes);
		
		while(!checkGoalState(stackArray, noOfBoxes))
		{
			int pos1, pos2;
			System.out.print("\nEnter the initial and final Rod numbers to move the Box(separated by space) [Enter 0 0 to exit game] : " );
			pos1 = read.nextInt();
			pos2 = read.nextInt();
			if(pos1 == 0 )
			{
				System.out.println("Quitting.....");
				break;
			}
			moveBox(pos1, pos2, stackArray);
			printRods(stackArray, noOfBoxes);
			
		}
		read.close();
		System.out.println("No of moves taken : " + moveCount);
		System.out.println("Optimal solution is " + (int)(Math.pow(2, noOfBoxes) - 1) + " moves ");
	}
	static boolean checkGoalState(Stack<Box>[] stackArray, int noOfBoxes)
	{
		int k = 1;
		if(stackArray[2].size() == noOfBoxes)
		{
			
			for(int i=noOfBoxes-1; i>=0; i--)
			{
				if(stackArray[2].get(i).boxID != k)
				{
					
					return false;
				}
				k++;
			}
			
			return true;
		}
		else
		{
			return false;
		}
	}
		

	static void moveBox(int fromRodNumber, int toRodNumber, Stack<Box>[] stackArray)
	{
		if(fromRodNumber > 3 || toRodNumber > 3)
		{
			System.err.println("Invalid Rod numbers!");
			return ;
		}
		
		moveCount++;
		if(stackArray[fromRodNumber-1].empty())
		{
			System.err.println("fromRod is empty");
		}
		else
		{
			if(stackArray[toRodNumber-1].empty())
			{
				Box tempBox = stackArray[fromRodNumber-1].pop();
				stackArray[toRodNumber-1].add(tempBox);
			}
			else if(stackArray[toRodNumber-1].peek().boxID < stackArray[fromRodNumber-1].peek().boxID)
			{
				System.err.println("Invalid Move!\t(cannot place large box on small Box)");
			}
			else
			{
				Box tempBox = stackArray[fromRodNumber-1].pop();
				stackArray[toRodNumber-1].add(tempBox);
			}
		}
	}
	static void gameSetupOnStart(int noOfBoxes, Stack<Box>[] stackArray)
	{
		// Initializing Rods (Array) 
		for(int i=0; i<3; i++)
		{
			stackArray[i] = new Stack<Box>();
		}
		
		// creating and appending Boxes on Disk 1 on start of the game
		for(int i=noOfBoxes; i>0; i--)
		{
			stackArray[0].add(new Box(i));
			
		}
	}

}
