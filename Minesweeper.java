package packages.Mineswepper;

import packages.Basicprograms.Pair;

import java.util.LinkedList;
import java.util.Queue;

public class Minesweeper {
        private final char[][] mineSweeperBoard ={
                {'M', '1', '0', '0', '1', 'M', '2', 'M', '1', '0', '0', '1'},
                {'2', '3', '1', '1', '2', '2', 'M', '2', '1', '1', 'M', '1'},
                {'M', '2', 'M', '1', '0', '0', '2', '1', 'M', '2', '2', '0'},
                {'1', '3', '2', '2', '1', '0', '1', '3', '2', '1', '1', '0'},
                {'0', '1', 'M', '2', 'M', '1', '0', '1', 'M', '2', '2', '1'},
                {'M', '1', '2', '3', '2', 'M', '1', '2', '2', '3', 'M', '2'},
                {'1', '2', 'M', '1', '1', '1', '2', 'M', '3', '1', '2', 'M'},
                {'M', '1', '2', '2', 'M', '2', 'M', '1', '1', '1', '1', '1'},
                {'1', '2', 'M', '3', '3', 'M', '2', '2', 'M', '2', '2', '0'},
                {'0', '1', '2', 'M', '1', '2', 'M', '1', '2', 'M', '1', '0'},
                {'1', '2', 'M', '1', '0', '1', 'M', '1', '1', '1', '2', 'M'},
                {'M', '2', '1', '1', '0', '1', '2', 'M', '3', '2', '1', '1'}
       };
        private int boardlength;
        private int bombcount;
        public Minesweeper()
        {
            boardlength= mineSweeperBoard.length;
            bombcount=17;
        }
        public int getBombCount()
        {
            return bombcount;
        }
        public int getBoardLength()
        {
            return boardlength;
        }
        public boolean validateMove(int row,int column,char[][] playerBoard)
       {
           if(mineSweeperBoard[row][column]=='M')
           {
               gameOverBoard(playerBoard);
               return false;
           }
           else if(mineSweeperBoard[row][column]!='M'&& mineSweeperBoard[row][column]!='0')
           {
               playerBoard[row][column]= mineSweeperBoard[row][column];
               return true;
           }
           else
           {
               int[][] visitedMatrix=new int[mineSweeperBoard.length][mineSweeperBoard[0].length];
               playerBoard[row][column]=' ';

               revealWhiteSpaces(row,column,playerBoard, mineSweeperBoard,visitedMatrix);
               return true;
           }

       }
       public boolean checkStatusOfGame(char[][] playerBoard)
       {
          for (int row=0;row<playerBoard.length;row++)
          {
              for (int column=0;column<playerBoard.length;column++)
              {
                  if(playerBoard[row][column]=='F')
                  {
                      if(mineSweeperBoard[row][column]!='M')
                      {
                          return false;
                      }
                  }
              }
          }
          this.displayBoard();
          return true;
       }
       public void displayBoard()
       {
           for(int row = 0; row< mineSweeperBoard.length; row++)
           {
               for (int column = 0; column< mineSweeperBoard[0].length; column++)
               {
                   System.out.print(mineSweeperBoard[row][column]+" ");
               }
               System.out.println();

           }
           System.out.println("You won the game");
       }
       public void revealWhiteSpaces(int row, int column, char[][] playerBoard, char[][] mineSweeperBoard, int[][] visitedMatrix)
       {
           visitedMatrix[row][column]=1;
           Queue<Pair<Integer,Integer>> reveal=new LinkedList<>();
           reveal.add(new Pair<>(row,column));
           while(!reveal.isEmpty())
           {
               int rowToReveal=reveal.peek().getKey();
               int columnToReveal=reveal.peek().getValue();
               reveal.remove();
               for(int i=-1;i<=1;i++)
               {
                   for(int j=-1;j<=1;j++)
                   {

                       int checkRowToReveal=rowToReveal+i;
                       int checkColumnToReveal=columnToReveal+j;
                       if(checkColumnToReveal>=0&&checkColumnToReveal<playerBoard[0].length&&checkRowToReveal>=0&&checkRowToReveal<playerBoard.length&&visitedMatrix[checkRowToReveal][checkColumnToReveal]==0&&mineSweeperBoard[checkRowToReveal][checkColumnToReveal]=='0')
                       {
                           reveal.add(new Pair<>(checkRowToReveal,checkColumnToReveal));
                           playerBoard[checkRowToReveal][checkColumnToReveal]=' ';
                           visitedMatrix[checkRowToReveal][checkColumnToReveal]=1;
                       } else if (checkColumnToReveal>=0&&checkColumnToReveal<playerBoard[0].length&&checkRowToReveal>=0&&checkRowToReveal<playerBoard.length&&mineSweeperBoard[checkRowToReveal][checkColumnToReveal]!='0'&&mineSweeperBoard[checkRowToReveal][checkColumnToReveal]!='M') {
                           playerBoard[checkRowToReveal][checkColumnToReveal]=mineSweeperBoard[checkRowToReveal][checkColumnToReveal];
                       }
                   }
               }
           }
       }
       public void gameOverBoard(char[][] playerBoard)
       {
           for (int row = 0; row < playerBoard.length; row++) {
               for (int column = 0; column < playerBoard[0].length; column++) {
                   if(mineSweeperBoard[row][column]=='M')
                   {
                       System.out.print("M");
                   }
                   if(playerBoard[row][column]!='F')
                   {
                       System.out.print(playerBoard[row][column]+" ");
                   }

               }
               System.out.println();
           }
       }
}
