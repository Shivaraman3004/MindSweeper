package packages.Mineswepper;


import java.util.Scanner;

public class Player {
    char[][] playerBoard;
    Minesweeper game;
    int flagCount;
    Player()
    {
        game=new Minesweeper();
        flagCount =17;
        playerBoard =new char[12][12];
        for(int row = 0; row< playerBoard.length; row++)
        {
            for (int column = 0; column< playerBoard[0].length; column++)
            {
                playerBoard[row][column]='-';
            }
        }
    }
    public void displayBoard()
    {
        for (int row = 0; row < playerBoard.length; row++) {
            for (int column = 0; column < playerBoard[0].length; column++) {
                System.out.print(playerBoard[row][column]+" ");
            }
            System.out.println();
        }
        System.out.println("Flag Count:"+ flagCount);
    }
    public void start(Scanner scan)
    {
        boolean isRunning=true;
        while (isRunning)
        {
            this.displayBoard();
            System.out.println("Enter 1 to FlagOperations");
            System.out.println("Enter 2 to Discover places");
            System.out.println("Enter 3 to Exit");
            System.out.println("Enter your choice:");
            int userChoice=scan.nextInt();
            if(userChoice==1)
            {
                isRunning= flagOperations(scan);

            }

            else if(userChoice==2)
            {
                isRunning= discoverPlaces(scan);
            }
            else if(userChoice==3)
            {
                System.out.println("Thank you");
                isRunning=false;
            }
            else
            {
                System.out.println("Invalid Move");
            }
        }
    }
    public boolean flagOperations(Scanner scan)
    {
        System.out.println("Enter row:");
        int row=scan.nextInt();
        System.out.println("Enter column:");
        int column=scan.nextInt();
        if(row>= playerBoard.length||column>= playerBoard.length||row<0||column<0)
        {
            System.out.println("Invalid Move");
        }
        else if(playerBoard[row][column]=='-'&& flagCount >0)
        {
            playerBoard[row][column]='F';
            flagCount--;
            if(flagCount ==0)
            {
                return !game.checkStatusOfGame(playerBoard);
            }
        } else if (playerBoard[row][column] == 'F') {
            playerBoard[row][column]='-';
            flagCount++;
        } else if (flagCount <= 0) {
            System.out.println("No more flag to place.");
        } else {
            System.out.println("We cannot place flag here.");
        }
        return true;
    }

    public boolean discoverPlaces(Scanner scan)
    {
        System.out.println("Enter row:");
        int row=scan.nextInt();
        System.out.println("Enter column:");
        int column=scan.nextInt();
        if(row>= playerBoard.length||column>= playerBoard.length||row<=-1||column<=-1)
        {
            System.out.println("Invalid Move");
        }
        else if (playerBoard[row][column]=='F')
        {
            System.out.println("Sorry we cannot find any place.Flag is there");

        } else if (game.validate(row,column, playerBoard))
        {
            System.out.println("working");
        }
        else {
            System.out.println("Game over");
            return false;
        }
        return true;
    }
}
