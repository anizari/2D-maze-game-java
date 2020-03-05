import java.util.*;

public class Main {
public static void main(String[] args) {
    Scanner playerInput = new Scanner( System.in );
    Board board = makeBoard();
    initBoard(board);
    drawBoard(board);
    System.out.println();
    Move(playerInput, board);
}

private static Board makeBoard(){
    int height = 32;
    int width = 32;
    int[][] gameBoard = new int[height][width];
    int px = 0;
    int py = 0;
    Board data = new Board(height, width, gameBoard, px, py);
    return data;
}


//idea: initialize everything as 0s, and then add 1s for walls, 2 for enemies etc
private static void initBoard(Board board){
    int height = board.getHeight();
    int width = board.getWidth();
    int[][] gameBoard = board.getBoard();
    for (int x = 0; x < height; x++ ){
        for (int y = 0; y < width; y++ ){
          gameBoard[x][y] = 0;
        }
    }
}

private static void drawBoard(Board board){
    int height = board.getHeight();
    int width = board.getWidth();
    int[][] gameBoard = board.getBoard();
    int px = board.getPx();
    int py = board.getPy();
    gameBoard[px][py] = 1;
    for(int i = 0; i < height; i++){
        for (int j = 0; j < width; j++){
            System.out.print( gameBoard[i][j] );
        }
            System.out.println( "" );
    }
}


private static void Move(Scanner playerInput, Board board){
  char playerMove = '\0';
  boolean isTurnDone = false;
  int[][] gameBoard = board.getBoard();
  int px = board.getPx();
  int py = board.getPy();
  while(!isTurnDone){
    playerMove = playerInput.next().charAt(0);
    switch(playerMove){
      //Need to check if not out of bounds
      case 'w':
      case 'W':
      gameBoard[px][py] = 0;
      gameBoard[--px][py] = 1;
      updateBoard(board, playerMove);
      break;
      case 'a':
      case 'A':
      gameBoard[px][py] = 0;
      gameBoard[px][--py] = 1;
      updateBoard(board, playerMove);
      break;
      case 's':
      case 'S':
      gameBoard[px][py] = 0;
      gameBoard[++px][py] = 1;
      updateBoard(board, playerMove);
      break;
      case 'd':
      case 'D':
      gameBoard[px][py] = 0;
      gameBoard[px][++py] = 1;
      updateBoard(board, playerMove);
      break;
    }
  }
//  updateBoard(board, steppedTile);
}

//Updates the board after each player turn
private static void updateBoard( Board board, char playerMove ){
  int height = board.getHeight();
  int width = board.getWidth();
  int[][] gameBoard = board.getBoard();
    for ( int i = 0; i < height; i++ ){
        for ( int j = 0; j < width; j++ ){
          System.out.print(gameBoard[i][j]);
        }
            System.out.println( "" );
   }
}

/*
private static void print(int[][] grid){
  for(int i = 0; i < grid.length; i++){
    for(int j = 0; j < grid[0].length; j++){
      System.out.print(grid[i][j]);
    }
    System.out.println();
  }
}*/

}



