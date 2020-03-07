import java.util.*;
import javax.swing.*;

public class Main {

static JLabel mapLabel;

public static void main(String[] args) {    
    JFrame frame = new JFrame("MyGame");
    frame.setSize(1000,1000);
    mapLabel = new JLabel("hhhhhh");
    frame.getContentPane().add(mapLabel); // Adds Button to content pane of frame
    frame.setVisible(true);

    Scanner playerInput = new Scanner( System.in );
    Board board = makeBoard();
    initBoard(board);

    drawBoard(board);
    
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
    int px = board.getPx();
    int py = board.getPy();
    gameBoard[px][py] = 1;
}

private static void drawBoard(Board board){
    int height = board.getHeight();
    int width = board.getWidth();
    int[][] gameBoard = board.getBoard();

    StringBuffer sb = new StringBuffer();
    sb.append("<html><p>");
    for(int i = 0; i < height; i++){
        for (int j = 0; j < width; j++){
            sb.append( gameBoard[i][j] );
            sb.append(" ");
        }
        sb.append("<br />");
    }
    sb.append("</p></html>");

    mapLabel.setText(sb.toString());
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
      break;
      case 'a':
      case 'A':
      gameBoard[px][py] = 0;
      gameBoard[px][--py] = 1;
      break;
      case 's':
      case 'S':
      gameBoard[px][py] = 0;
      gameBoard[++px][py] = 1;
      break;
      case 'd':
      case 'D':
      gameBoard[px][py] = 0;
      gameBoard[px][++py] = 1;
      break;
    }
    drawBoard(board);
  }
}

}



