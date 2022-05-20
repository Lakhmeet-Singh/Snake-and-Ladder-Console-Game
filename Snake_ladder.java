import java.util.Random;
import java.util.Scanner;

public class Snake_ladder {

	public static void main(String[] args) 
	{
		System.out.println("Welcome to the Game");  		// prints the welcome message
		System.out.println(" ");
		System.out.println("How many players are playing? (Choose between 2 to 4)"); 
		// prints this statement and tells user to choose the number of players
		Scanner sc = new Scanner(System.in);
		int numplayer = sc.nextInt();						// user input number of players
		String[] name = {"Red", "Blue", "Green", "Yellow"}; //  name of the players in an array
		String[] player = new String[numplayer]; 		    //  players is an array which contains number of players
		int[] location = new int [numplayer];			    //  location is an array which contains number of players
		int[] winposition = new int[numplayer];				//   win position is an array which contains number of players
		boolean[] biscuit = new boolean [numplayer]; 		//   biscuit is an array which contains number of players
		boolean[] big_stick = new boolean [numplayer]; 		//   big stick is an array which contains number of players
		boolean[] Move = new boolean [numplayer]; 			//   move is an array which contains number of players
			for (int i = 0; i < numplayer; i++) 
			{
				player [i] = name[i]; 		// players are assigned with name 
				location [i] = 1;			// location is set to square 1
				biscuit [i] = false;		// biscuit is set to false at the start
				big_stick [i]= false;		// big stick is set to false at the start
				Move [i] = false;			// move is set to false at the start
				winposition[i] = 0;			// win position is set to 0
			}
		System.out.println("Game Started");	
		System.out.println(" ");
		int[][] snake = Snakes();			 		// snake is an array which calls the method Snakes
		int[][] ladder = Ladders();					// ladder is an array which calls the method Ladder
		int[] biscuitlocation = biscuit();			// biscuit location is an array which calls the method biscuit
		int[] bigsticklocation = big_stick(); 		// big stick is an array which calls the method big_stick
		boolean[] win = new boolean [numplayer];	// win is an array which contains number of players
		boolean dicesix = false; 					// dice six is set to false at the start
		int playernumber = numplayer; 				// player number is set to number of players
		int i = 0;
		int j = 0;
		do 
		{ 
			if(playernumber > 1) 
			{
				for (i = 0; i <= numplayer-1; i++)	// for loop to have all the players
				{
					if(!win[i]) // if win is false then player can play
					{
						System.out.println(player[i] + "  Press 'r' to roll dice"); 
						// prints this statement and tells user to press r to play
						Game(player, location, biscuit, big_stick, Move, snake, ladder, win, i, winposition, biscuitlocation, bigsticklocation, dicesix);
						// game method is called
						if (i == numplayer-1) 		// this statement is for players
						{
							i = -1;
						}
					}
				}
			}
			else 
			{
				for (j = 0; j <= numplayer-1; j++)
				{
					System.out.println(player[j] + " is " + winposition[j]);
				}
			}
			if (playernumber == numplayer) // when player number is equal to number of players 
			{
				System.out.println("------------------------------------");
				System.out.println("GAME ENDED!");
				System.out.println("------------------------------------");
				System.exit(0); // game ends 
				break;
			}
		}
		while (win[i]); // this continues in a loop until players reach 100th square	
	}
	
	public static void Game(String[] player,int[] location,boolean[] biscuit,boolean[] big_stick,boolean[] Move,int[][] snake, int [][] ladder, boolean[] win, int playerindex, int[] winposition, int[] biscuitlocation, int[] bigsticklocation, boolean dicesix) 
	{
		String roll = null; 				// roll is set to null
		int dice = 1;						// dice is set to 1
		int pos = 1;						// position is set to 1
		int[][] snakes = Snakes();			// snakes is a 2d array which contains Snakes method
		Scanner s = new Scanner(System.in);
	    roll = s.next();					// user input r to toll the dice
		dice = roll_Dice();					// dice is set to the method roll_Dice
			
			if (!Move[playerindex])  		// when move is false all the below code will work
			{
				int Location = Move(location[playerindex], dice);
				location[playerindex] = Location; 
				if(Location == 2) 			// if location is equal to 2 then the code below will work
				{
					System.out.println("Lets go" + " " + player[playerindex]);
					System.out.println(player[playerindex] + " " + "rolled" + " " + dice + " " + "and moved to square" + " " + location[playerindex] );
					System.out.println("------------------------------------");
					Move[playerindex] = true;
				}
				else
				{
					System.out.println(player[playerindex] + " " + "rolled" + " " + dice);
					System.out.println("Try again! (You need 1 or 6 to move)");
					System.out.println("------------------------------------");
				}
			}
			else
			{
				int Location = location[playerindex] + dice;
				if(Location == 100) 		// if location is equal to 100 then the code below will work and player wins
				{
					location[playerindex] += dice;
					System.out.println(" ");
					System.out.println(player[playerindex] + " " + "rolled" + " " + dice + " " + "and moved to square" + " " + location[playerindex]);
					System.out.println("------------------------------------");
					System.out.println(win(player, location, playerindex));
					System.out.println("------------------------------------");
					System.out.println();
					System.out.println(" ");
					win[playerindex] = true;
					winposition[playerindex] = pos;
					pos++;
				}
				else if(Location > 100) 	// if location is bigger then 100 then the code below will work
				{
					System.out.println(player[playerindex] + " " + "rolled" + " " + dice);
					System.out.println(player[playerindex] + " need " + (100-location[playerindex]) + " to win " );
					System.out.println("------------------------------------");
				}
				else 
				{
					System.out.println(player[playerindex] + " " + "rolled" + " " + dice + " " + "and moved from square" + " " + location[playerindex] + " to sqaure " + Location);
					System.out.println("------------------------------------");
					location[playerindex] += dice;
					for (int i = 0; i < 2; i++)
					{
						if (biscuitlocation[i] == location[playerindex]) 	// if location equal to biscuit then the code below will work
						{
							System.out.println(" ");
							System.out.println("------------------------------------");
							System.out.println(player[playerindex] + " Get Biscuit and feed the next snake you met");
							System.out.println("------------------------------------");
							System.out.println(" ");
							biscuit[playerindex] = true;
						}
					}
					for (int i = 0; i < 2; i++)
					{
						if (bigsticklocation[i] == location[playerindex])	// if location equal to big stick then the code below will work
						{
							System.out.println(" ");
							System.out.println("------------------------------------");
							System.out.println(player[playerindex] + " Get Big Stick and scare the next snake you met");
							System.out.println("------------------------------------");
							System.out.println(" ");
							big_stick[playerindex] = true;
						}
					}
					for (int i = 0; i < snake.length; i++) 
					{
						for(int j = 0; j < snake[0].length; j++)
						{
						if (snake[0][j] == location[playerindex])	// if location equal to snake then the code below will work
						{
							if (biscuit[playerindex])	// if location of the player equal to biscuit then the code below will work
							{
								System.out.println(" ");
								System.out.println("------------------------------------");
								System.out.println(player[playerindex] + " gave the Snake a Biscuit and can stay on the current square");
								System.out.println("------------------------------------");
								System.out.println(" ");
								location[playerindex] = snake[0][j];
								biscuit[playerindex] = true;
							}
							else if (big_stick[playerindex]) // if location of the player equal to big stick then the code below will work
							{
								if(location[playerindex] > 90) 	// if location of the player is bigger then 90 then big stick won't work
								{
									System.out.println(" ");
									System.out.println(player[playerindex] + " you can't scare the Snake after the 90th square");
									System.out.println("------------------------------------");
									location[playerindex] = snake[1][j];
									System.out.println(player[playerindex] + " Swallowed by Snake and moved to " + location[playerindex]  );
									System.out.println("------------------------------------");
									System.out.println(" ");
								}
								else 	// if location of the player is less than 90 then the code below will work
								{
									System.out.println(" ");
									System.out.println("------------------------------------");
									System.out.println(player[playerindex] + " scare the snake. Snake now moved to " + (snake[0][j] + 10));
									System.out.println("------------------------------------");
									System.out.println(" ");
									snake[0][j] += 10;
									snake[1][j] += 10;
									big_stick[playerindex] = false;
								}
							}
							else		// when location of the player is on snakes head the code below will work
							{
								location[playerindex] = snake[1][j];
								System.out.println(" ");
								System.out.println("------------------------------------");
								System.out.println(player[playerindex] + " Swallowed by Snake and moved to " + location[playerindex]  );
								System.out.println("------------------------------------");
								System.out.println(" ");
							}
						}
					}
						}
				}
					for (int i = 0; i < ladder.length-1; i++) 
					{
						for(int j = 0; j < ladder[0].length; j++)
						{
						if (ladder[0][j] == location[playerindex])
						{
								location[playerindex] = ladder[1][j]; // when location of the player is at the bottom of the ladder the code below will work
								System.out.println(" ");
								System.out.println("------------------------------------");
								System.out.println(player[playerindex] + " Climbed up the Ladder and moved to " + location[playerindex]  );
								System.out.println("------------------------------------");
								System.out.println(" ");
						}
					}
				}
			}
			if (dice == 6)		// when player get 6 on dice they will get extra turn and code below will work
			{
				dicesix = true;
				System.out.println(player[playerindex] + " rolled " + dice + "! " + player[playerindex] + " have additional turn " );
				System.out.println("------------------------------------");
				Game(player, location, biscuit, big_stick, Move, snake, ladder, win, playerindex, winposition, biscuitlocation, bigsticklocation, dicesix);
			}
		}

	public static int[][] Snakes() 						// 2d array of Snakes
	{
		int[][] snake = 
			{
				{23,69,56,43,86,62,89,96,94,98},		// head of the snakes
				{5, 12,25,39,54,59,72,84,73,58},		// tail of the snakes
			};
		return snake;
	}
	
	public static int[][] Ladders() 					// 2d array of ladders
	{
		int[][] ladder = 
			{
				{3, 16, 8,37,33,50,64,80,77,89},		// bottom of the ladder
				{21,26,55,76,74,70,83,99,95,91},		// top of the ladder
			};
		return ladder;
	}
	
	public static int[] big_stick() 					// big_stick is in an array
	{
		int[] bigstick = {29,44};						// big stick values
		return bigstick;
	}
	
	public static int[] biscuit() 						// biscuit is in an array
	{
		int[] biscuit = {55,93};						// biscuit values
		return biscuit;
	}
	
	public static int roll_Dice()						// roll dice method
	{
		Random rand = new Random();						// random method imported
		int dice = rand.nextInt(6)+1;					// dice is set to random
		return dice;
	}
	
	public static int Move(int location, int dice) 		// move method
	{
		switch(dice) 
		{
			case 1: 									// if player get 1 on dice or
			case 6:										// if player get 6 on dice 
				location = 2;							// player will move to square 2
				break;
		}
		return location;
	}
	
	public static String win(String[] player, int[] location, int playerindex) 	// win method
	{
		if(location[playerindex] == 100); 						// if location of the player is 100 
		{
			return (player[playerindex] + " " + "Win"); 		// player win the this statement is printed
		}
	}
}