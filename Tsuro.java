import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
  
/** 
 * a class that represents the game board and runs the game
 * @author TungHo Lin
 */ 
public class Tsuro extends JFrame implements ActionListener {
  /** the number of players in the game */
  private int numplayers;
  
  /** the game board */
  private JPanel board;
  
  /** the number of row in the game board */
  private int row;
  
  /** the number of column in the game board */
  private int column;
  
  /** the number of hands of each player */
  private int handsize;
  
  /** the array of TsuroButtons on the game board */
  private TsuroButton[][] buttons;
  
  /** player 1 */
  private Players player1;
  
  /** player 2*/
  private Players player2;
  
  /** the number that stores the number of moves made by all players */
  private int actioncount;
  
  /** stores the button clicked each time */
  private TsuroButton clickedbtn;
  
  /** stores the previous button clicked by player 1 */
  private TsuroButton player1lastbtn;
  
  /** stores the previous button clicked by player 2*/
  private TsuroButton player2lastbtn;
  
  /** stores the endpoint where player 1's stone will stop */
  private int stonestop1;
  
  /** stores the endpoint where player 2's stone will stops */
  private int stonestop2;
  
  /** determines whether player1 has lost */
  private boolean p1lost;
  
  /** determines whether player2 has lost */
  private boolean p2lost;
  
  /**
   * the constructor that creates the game boards, the players and the player boards
   */
  public Tsuro () {
    super();
    this.numplayers = 2;
    this.handsize = 3;
    this.row = 6;
    this.column = 6;
    this.actioncount = 0;
    this.p1lost = false;
    this.p2lost = false;
    try {
      UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
    }
    catch (Exception e) {
    }
    this.board = new JPanel(new GridLayout(row, column, 0, 0));
    Container c = this.getContentPane();
    this.buttons = new TsuroButton[row][column];
    for (int i = 0; i < buttons.length; i++) {
      for (int j = 0; j < buttons[i].length; j++) {
        buttons[i][j] = new TsuroButton();
        buttons[i][j].addActionListener(this);
        board.add(buttons[i][j]);
      }
    }
    c.add(this.board, "Center");
    this.setSize(100 * row, 100 * column);
    this.setVisible(true);
    this.setLocation(574, 73);     //a good position for the board
    this.setTitle("Tsuro");
    this.player1 = new Players("Player 1");
    player1.setLocation(this.getX() - 500, this.getY());
    for (int i = 0; i < player1.playerbuttons.length; i++)
        player1.playerbuttons[i].addStone(Color.BLUE, 6);
    this.player2 = new Players("Player 2");
    player2.setLocation(this.getX() - 500, this.getY() + 200);
    for (int i = 0; i < player2.playerbuttons.length; i++) {
        player2.playerbuttons[i].addStone(Color.GREEN, 2);
    }
  }
      
  /** 
   * a constructor that will create a game board with desired size, players and player boards
   * @param row the number of rows in the game board
   * @param column the number of columns in the game board
   */ 
  public Tsuro (int row, int column) {
    super();
    this.row = row;
    this.row = row;
    if (row <= 0)
      throw new IllegalArgumentException ("row cannot be 0 or negative!!!");
    this.column = column;
    if (column <= 0)
      throw new IllegalArgumentException ("column cannot be 0 or negative!!!");
    this.numplayers = 2;
    this.handsize = 3;
    this.actioncount = 0;
    this.p1lost = false;
    this.p2lost = false;
    try {
      UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
    }
    catch (Exception e) {
    }
    this.board = new JPanel(new GridLayout(row, column, 0, 0));
    Container c = this.getContentPane();
    this.buttons = new TsuroButton[row][column];
    for (int i = 0; i < buttons.length; i++) {
      for (int j = 0; j < buttons[i].length; j++) {
        buttons[i][j] = new TsuroButton();
        buttons[i][j].addActionListener(this);
        board.add(buttons[i][j]);
      }
    }
    c.add(this.board, "Center");
    this.setSize(120 * column, 120 * row);
    this.setVisible(true);
    this.setLocation(574, 73);
    this.setTitle("Tsuro");
    this.player1 = new Players("Player 1");
    player1.setLocation(this.getX() - 500, this.getY());     //500 to the left of the game board
    for (int i = 0; i < player1.playerbuttons.length; i++)
      player1.playerbuttons[i].addStone(Color.BLUE, 6);
    this.player2 = new Players("Player 2");
    player2.setLocation(this.getX() - 500, this.getY() + 200);  //200 to the bottom of player1 board
    for (int i = 0; i < player2.playerbuttons.length; i++)
      player2.playerbuttons[i].addStone(Color.GREEN, 2);
  }
  
  /** 
   * a constructor that will create a game board with desired size, players and desired handsizes in the player boards
   * @param row the number of rows in the game board
   * @param column the number of columns in the game board
   * @param handsize the number of hands in each player's player boards
   */ 
  public Tsuro (int row, int column, int handsize) {
    super();
    this.row = row;
    if (row <= 0)
      throw new IllegalArgumentException ("row cannot be 0 or negative!!!");
    this.column = column;
    if (column <= 0)
      throw new IllegalArgumentException ("column cannot be 0 or negative!!!");
    this.handsize = handsize;
    if (handsize <= 0)
      throw new IllegalArgumentException( "handsize cannot be 0 or negative!!!");
    this.numplayers = 2;
    this.actioncount = 0;
    this.p1lost = false;
    this.p2lost = false;
    try {
      UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
    }
    catch (Exception e) {
    }
    this.board = new JPanel(new GridLayout(row, column, 0, 0));
    Container c = this.getContentPane();
    this.buttons = new TsuroButton[row][column];
    for (int i = 0; i < buttons.length; i++) {
      for (int j = 0; j < buttons[i].length; j++) {
        buttons[i][j] = new TsuroButton();
        buttons[i][j].addActionListener(this);
        board.add(buttons[i][j]);
      }
    }
    c.add(this.board, "Center");
    this.setSize(120 * column, 120 * row);
    this.setVisible(true);
    this.setLocation(574, 73);
    this.setTitle("Tsuro");
    this.player1 = new Players("Player 1");
    player1.setLocation(this.getX() - 500, this.getY());
    for (int i = 0; i < player1.playerbuttons.length; i++)
      player1.playerbuttons[i].addStone(Color.BLUE, 6);
    this.player2 = new Players("Player 2");
    player2.setLocation(this.getX() - 500, this.getY() + 200);
    for (int i = 0; i < player2.playerbuttons.length; i++)
      player2.playerbuttons[i].addStone(Color.GREEN, 2);
  }
  
  /**
   * the main method that will initialize everything
   * @param args[] an array of String that provides information about the desired game board
   */
  public static void main(String args[]) {
    if (args.length == 0)     //if there is no input
      new Tsuro();
    else if (args.length == 2)
      try
    {
      new Tsuro(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
    }
    catch (NumberFormatException exception)     //catch an exception where player inputs any types other than int
    {
      throw new NumberFormatException("Invalid input! Please enter integers only!");
    }
    else if (args.length == 3)
      try
    {
      new Tsuro(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]));
    }
    catch (NumberFormatException exception)
    {
      throw new NumberFormatException("Invalid input! Please enter integers only!");
    }
    else
      System.out.println("Invalid input! Please input: (nothing) / (row) (column) / (row) (column) (handsize)");   
  }
  
  /** 
   * a method to get the x coordinate of a TsuroButton on the game board
   * @param b1 a TsuroButton input 
   * @return the x coordinate of the input button
   */ 
  public int getX(TsuroButton b1) {
    int x = 0;
    for (int i = 0; i < buttons.length; i++) {     //find the input button in the 2D array of buttons
      for (int j = 0; j < buttons[i].length; j++) {
        if (buttons[i][j] == b1)      
          x = j;
      }
    }
    return x;
  }
  
  /**
   * a method to get the y coordinate of a TsuroButton on the game board
   * @param b1 a TsuroButton input 
   * @return the y coordinate of the input button
   */ 
  public int getY(TsuroButton b1) {
    int y = 0;
    for (int i = 0; i < buttons.length; i++) {
      for (int j = 0; j < buttons[i].length; j++) {
        if (buttons[i][j] == b1)
          y = i;
      }
    }
    return y;
  }
  
  /** 
   * a method to check if it is Player1's turn
   * @return true if it is player 1 's turn
   */ 
  public boolean isPlayer1Turn() {
    if (this.actioncount % 2 == 0)      //if actioncount is 0 or even, it is player1's turn
      return true;
    else
      return false;
  }
  
  /** 
   * a method to check if it is player 2's turn
   * @return true if it is player 2 's turn
   */ 
  public boolean isPlayer2Turn() {
    if (this.actioncount % 2 != 0)     //if actioncount is odd, it is player2's turn    
      return true;
    else
      return false;
  }
  
  /** a method to check if the first input button is adjacent to the second input button
    * @param b1 input of a TsuroButton
    * @param b2 input of another TsuroButton
    * @return true if the first input button is adjacent to the second input button
    */ 
  public boolean isAdjacent(TsuroButton b1, TsuroButton b2) {
    if (isPlayer1Turn() == true) {
      if (b1 == b2)     //if the first button is the second button, they are not adjacent
        return false;
      else if (stonestop1 == 0 || stonestop1 == 1) {     //if player1 stone is at the top of the button
        if (getX(b1) == getX(b2) && getY(b1) == getY(b2) - 1)     //and if the second button is on top of the first button
          return true;
        else
          return false;
      }
      else if (stonestop1 == 2 || stonestop1 == 3) {     //if player1 stone is on the right
        if (getX(b1) == getX(b2) + 1 && getY(b1) == getY(b2))     //and if the second button is on the right of the first button
          return true;
        else
          return false;
      }
      else if (stonestop1 == 4 || stonestop1 == 5) {
        if (getX(b1) == getX(b2) && getY(b1) == getY(b2) + 1)
          return true;
        else
          return false;
      }
      else if (stonestop1 == 6 || stonestop1 == 7) {
        if (getX(b1) == getX(b2) - 1 && getY(b1) == getY(b2))
          return true;
        else
          return false;
      }
      else
        return false;
    }
    else if (isPlayer2Turn() == true) {
      if (b1 == b2)
        return false;
      else if (stonestop2 == 0 || stonestop2 == 1) {
        if (getX(b1) == getX(b2) && getY(b1) == getY(b2) - 1)
          return true;
        else
          return false;
      }
      else if (stonestop2 == 2 || stonestop2 == 3) {
        if (getX(b1) == getX(b2) + 1 && getY(b1) == getY(b2))
          return true;
        else
          return false;
      }
      else if (stonestop2 == 4 || stonestop2 == 5) {
        if (getX(b1) == getX(b2) && getY(b1) == getY(b2) + 1)
          return true;
        else
          return false;
      }
      else if (stonestop2 == 6 || stonestop2 == 7) {
        if (getX(b1) == getX(b2) - 1 && getY(b1) == getY(b2))
          return true;
        else
          return false;
      }
      else
        return false;
    }
    else
      return false;
  }
  
  /** 
   * a method to get the button adjacent to the side where player 1's stone is 
   * @param b1 the input TsuroButton
   * @return the button adjacent to the side where player 1's stone is
   */ 
  public TsuroButton getP1Adjacent(TsuroButton b1) {
    if (stonestop1 == 0 || stonestop1 == 1) {     //if player1 stone is at the top
      if (getY(b1) != 0)     //and if the input button is not at the top edge of the game board
        return buttons[getY(b1) - 1][getX(b1)];
      else 
        return null;
    }
    else if (stonestop1 == 2 || stonestop1 == 3) {     //if player1 stone is on the right
      if (getX(b1) != column - 1)     //and if the input button is not at the right edge of the game board
        return buttons[getY(b1)][getX(b1) + 1];
      else
        return null;
    }
    else if (stonestop1 == 4 || stonestop1 == 5) {
      if (getY(b1) != row - 1)
        return buttons[getY(b1) + 1][getX(b1)];
      else 
        return null;
    }
    else {
      if (getX(b1) != 0)
        return buttons[getY(b1)][getX(b1) - 1];
      else
        return null;
    }
  }
  
  /** 
   * a method to get the button adjacent to the side where player 2's stone is 
   * @param b1 the input TsuroButton
   * @return the button adjacent to the side where player 2's stone is
   */ 
  public TsuroButton getP2Adjacent(TsuroButton b1) {
    if (stonestop2 == 0 || stonestop2 == 1) {
      if (getY(b1) != 0)
        return buttons[getY(b1) - 1][getX(b1)];
      else
        return null;
    }
    else if (stonestop2 == 2 || stonestop2 == 3) {
      if (getX(b1) != column - 1)
        return buttons[getY(b1)][getX(b1) + 1];
      else
        return null;
    }
    else if (stonestop2 == 4 || stonestop2 == 5) {
      if (getY(b1) != row - 1)
        return buttons[getY(b1) + 1][getX(b1)];
      else
        return null;
    }
    else {
      if (getX(b1) != 0)
        return buttons[getY(b1)][getX(b1) - 1];
      else
        return null;
    }
  }
  
  /**
   * a method to get the endpoint of the input after rotating 90 degrees clockwise
   * @param p1 the original endpoint
   * @return the new rotated endpoint
   */ 
   public static int getNextEndPoint(int p1) {
    if (p1 == 0)     //if the line starts at 0 originally, after turning 90CW, the line will start at 2
      return 2;
    else if (p1 == 1)
      return 3;
    else if (p1 == 2)
      return 5;
    else if (p1 == 3)
      return 4;
    else if (p1 == 4)
      return 6;
    else if (p1 == 5)
      return 7;
    else if (p1 == 6)
      return 1;
    else
      return 0;
  }
   
   /** 
    * a method to rotate a TsuroButton 90 degrees clockwise
    * @return a rotated TsuroButton
    */ 
   public static TsuroButton rotate90(TsuroButton b1) {
    int[] oldvalues = b1.getConnections();
    int[] newvalues = new int[8];     //create a new array to store the new rotated endpoints
    for (int i = 0; i < oldvalues.length; i++) {
      newvalues[Tsuro.getNextEndPoint(i)] = Tsuro.getNextEndPoint(oldvalues[i]);     //store the old connecting points of each line into the new array
    }
    b1.setConnections(newvalues);     //reset the button's connections to the new values
    return b1;
  }
   
  /**
   * a method to get the stopping endpoint of the stone on the newly placed tile
   * @param i1 the starting endpoint
   * @return the stopping endpoint
   */ 
   public static int getStoneStop(TsuroButton b1, int i1) {
     int[] path = b1.getConnections();     //find out where will the stone go with the provided starting endpoint
     return path[i1];
   }
   
   /**
    * a method to get the connecting endpoint of the adjacent tile
    * @param p1 the original endpoint
    * @return the new endpoint on the adjacent tile
    */ 
   public static int getConnectEndpt(int p1) {
     if (p1 == 0)     //find out the matching endpoint if there is another tile next to the current tile that contains the stone
       return 4;
     else if (p1 == 1)
       return 5;
     else if (p1 == 2)
       return 6;
     else if (p1 == 3)
       return 7;
     else if (p1 == 4)
       return 0;
     else if (p1 == 5)
       return 1;
     else if (p1 == 6)
       return 2;
     else
       return 3;
   }
   
   /**
    * a method to remove all the stones from a tile
    * @param b1 the input tile
    */ 
   public static void removeAllStone(TsuroButton b1) {
     for (int i = 0; i < 8; i++)
       b1.removeStone(i);
   }

  /** 
   * a method to check if the button the player clicked is a correct move
   * @return true if it is a correct move
   */ 
  public boolean isCorrectMove() {
    if (isPlayer1Turn() == true) {
      if (player1.anyHighlighted() == true) {      //if there is a highlighted button in the player board
          if (this.actioncount < 2) {     //if it is the first move of player 1
            if (getX(clickedbtn) == 0)     //player 1 starts at the right edge of the game board
              return true;
            else 
              return false;
          }
          else {
            if (clickedbtn.getConnections() == null) {     //if the clicked button has nothing on it
              if (isAdjacent(clickedbtn, player1lastbtn) == true)     //and if the clicked button is adjacent to the button player1's stone is at 
                return true;
              else
                return false;
            }
            else
              return false;
          }
      }
      else
        return false;
    }
    else if (isPlayer2Turn() == true) {
       if (player2.anyHighlighted() == true) {
          if (this.actioncount < 2) {
            if (getX(clickedbtn) == column - 1)
              return true;
            else
              return false;
          }
          else {
            if (clickedbtn.getConnections() == null) {
              if (isAdjacent(clickedbtn, player2lastbtn) == true)
                return true;
              else
                return false;
            }
            else
              return false;
          }
        }
        else
          return false;
      }
    else
      return false;
  }
  
  /** a method to check if player 1 has lost the game
    * @return true if player 1 has lost the game
    */ 
  public boolean p1GameOver() {
    if (stonestop1 == 0 || stonestop1 == 1) {     //if player1's stone is at the top
      if (getY(player1lastbtn) == 0)     //and if the current button with player1's stone is at the top edge of the game board
        return true;
      else 
        return false;
    }
    else if (stonestop1 == 2 || stonestop1 == 3) {
      if (getX(player1lastbtn) == column - 1)
        return true;
      else
        return false;
    }
    else if (stonestop1 == 4 || stonestop1 == 5) {
      if (getY(player1lastbtn) == row - 1)
        return true;
      else
        return false;
    }
    else if (stonestop1 == 6 || stonestop1 == 7) {
      if (getX(player1lastbtn) == 0)
        return true;
      else
        return false;
    }
    else if (isAdjacent(player1lastbtn, player2lastbtn) == true)
      return true;
    
    else
      return false;
  }
    
  /** 
   * a method to check if player2 has lost
   * @return true if player2 has lost
   */ 
  public boolean p2GameOver() {
    if (stonestop2 == 0 || stonestop2 == 1) {
      if (getY(player2lastbtn) == 0)
        return true;
      else 
        return false;
    }
    else if (stonestop2 == 2 || stonestop2 == 3) {
      if (getX(player2lastbtn) == column - 1)
        return true;
      else
        return false;
    }
    else if (stonestop2 == 4 || stonestop2 == 5) {
      if (getY(player2lastbtn) == row - 1)
        return true;
      else
        return false;
    }
    else if (stonestop2 == 6 || stonestop2 == 7) {
      if (getX(player2lastbtn) == 0)
        return true;
      else
        return false;
    }
    else
      return false;
  }
  
  /** 
   * a method to detect the click of a mouse on the game board and perform actions accordingly
   * @param e the detected mouse click on the game board
   */ 
  public void actionPerformed(ActionEvent e) {
    this.clickedbtn = (TsuroButton)e.getSource();
    boolean p1moved = false;
    boolean p2moved = false;
    if (isPlayer1Turn() == true) {
      if (p1lost == false && p2lost == false) {     //if none of the players has lost
        if (isCorrectMove() == true) {
          if (this.actioncount < 2) {     //the first move of player 1
            clickedbtn.setConnections(player1.getHighlighted());
            clickedbtn.addStone(Color.BLUE, getStoneStop(clickedbtn, 6));
            this.stonestop1 = getStoneStop(clickedbtn, 6);
            this.player1lastbtn = clickedbtn;
            ++actioncount;
          }
          else {     //the later moves of player 1
            clickedbtn.setConnections(player1.getHighlighted());     //set the empty button on the board to be the highlighted button from the player board
            player1lastbtn.removeStone(stonestop1);     //remove stone from the last position
            clickedbtn.addStone(Color.BLUE, getStoneStop(clickedbtn, Tsuro.getConnectEndpt(stonestop1)));     //add stone to the new endpoint
            stonestop1 = getStoneStop(clickedbtn, Tsuro.getConnectEndpt(stonestop1));
            player1lastbtn = clickedbtn;
            if (isAdjacent(player2lastbtn, player1lastbtn) == true) {     //if the two current buttons are adjacent to each other
              if (Tsuro.getConnectEndpt(stonestop1) != stonestop2) {     //and if the two stones do not collide
                while (getP1Adjacent(player1lastbtn) != null && getP1Adjacent(player1lastbtn).getConnections() != null) {  
                  //player1 stone will travel until its adjacent button is empty or it has reached the end of the game board
                  TsuroButton nextbtn = getP1Adjacent(player1lastbtn);
                  player1lastbtn.removeStone(stonestop1);
                  nextbtn.addStone(Color.BLUE, getStoneStop(nextbtn, Tsuro.getConnectEndpt(stonestop1)));
                  stonestop1 = getStoneStop(nextbtn, Tsuro.getConnectEndpt(stonestop1));
                  player1lastbtn = nextbtn;
                }
                while (getP2Adjacent(player2lastbtn) != null && getP2Adjacent(player2lastbtn).getConnections() != null) {
                  //player2 stone will do the same as player1 stone
                  TsuroButton nextbtn = getP2Adjacent(player2lastbtn);
                  player2lastbtn.removeStone(stonestop2);
                  nextbtn.addStone(Color.GREEN, getStoneStop(nextbtn, Tsuro.getConnectEndpt(stonestop2)));
                  stonestop2 = getStoneStop(nextbtn, Tsuro.getConnectEndpt(stonestop2));
                  player2lastbtn = nextbtn;
                  p2moved = true;
                }
                ++actioncount;
              }
              else {     //if the two stones collide with each other
                p1lost = true;
                p2lost = true;
              }
            }
            else {     //if the two buttons are not adjacent to each other
              while (getP1Adjacent(player1lastbtn) != null && getP1Adjacent(player1lastbtn).getConnections() != null) {
                  TsuroButton nextbtn = getP1Adjacent(player1lastbtn);
                  player1lastbtn.removeStone(stonestop1);
                  nextbtn.addStone(Color.BLUE, getStoneStop(nextbtn, Tsuro.getConnectEndpt(stonestop1)));
                  stonestop1 = getStoneStop(nextbtn, Tsuro.getConnectEndpt(stonestop1));
                  player1lastbtn = nextbtn;
                }
                while (getP2Adjacent(player2lastbtn) != null && getP2Adjacent(player2lastbtn).getConnections() != null) {
                  TsuroButton nextbtn = getP2Adjacent(player2lastbtn);
                  player2lastbtn.removeStone(stonestop2);
                  nextbtn.addStone(Color.GREEN, getStoneStop(nextbtn, Tsuro.getConnectEndpt(stonestop2)));
                  stonestop2 = getStoneStop(nextbtn, Tsuro.getConnectEndpt(stonestop2));
                  player2lastbtn = nextbtn;
                  p2moved = true;
                }
                ++actioncount;
                if (p2GameOver() == true)
                  p2lost = true;
            }
          }
          if (p1GameOver() == true)   //check if player1 has lost
            p1lost = true;
          for (int i = 0; i < player1.playerbuttons.length; i++) {     //replace the used button on player 1 board
            if (player1.isHighlighted(player1.playerbuttons[i]) == true) {
              player1.playerbuttons[i].setConnections(TsuroButton.makeRandomConnectArray());
              player1.playerbuttons[i].setBackground(Color.WHITE);
            }
          }
          for (int i = 0; i < player1.playerbuttons.length; i++) {     //remove previous stones and add new ones to the new starting endpoint
            Tsuro.removeAllStone(player1.playerbuttons[i]);
            player1.playerbuttons[i].addStone(Color.BLUE, Tsuro.getConnectEndpt(stonestop1));
          }
          if (p2moved == true) {     //if player1's move causes player2's stone to move, then the stones on player 2 board have to be adjusted as well
            for (int i = 0; i < player2.playerbuttons.length; i++) {
              if (player2.isHighlighted(player2.playerbuttons[i]) == true) {
                player2.playerbuttons[i].setConnections(TsuroButton.makeRandomConnectArray());
                player2.playerbuttons[i].setBackground(Color.WHITE);
              }
            }
            for (int i = 0; i < player2.playerbuttons.length; i++) {
              Tsuro.removeAllStone(player2.playerbuttons[i]);
              player2.playerbuttons[i].addStone(Color.GREEN, Tsuro.getConnectEndpt(stonestop2));
            }
          }
        }
      }
      else if (p1lost == false && p2lost == true) {     //if player 1 has lost
        player1.c.removeAll();
        JTextArea output1 = new JTextArea(10, 10);
        player1.c.add(output1, "Center");
        output1.append("Congratulations! You WON!");
        player2.c.removeAll();
        JTextArea output2 = new JTextArea(10, 10);
        player2.c.add(output2, "Center");
        output2.append("Sorry! You LOST!!");
        ++actioncount;
      }
      else if (p1lost == true && p2lost == false) {     //if player 2 has lost
        player1.c.removeAll();
        JTextArea output1 = new JTextArea(10, 10);
        player1.c.add(output1, "Center");
        output1.append("Sorry! You LOST!!");
        player2.c.removeAll();
        JTextArea output2 = new JTextArea(10, 10);
        player2.c.add(output2, "Center");
        output2.append("Congratulations! You WON!");
        ++actioncount;
      }
      else if (p1lost == true && p2lost == true) {     //if they both lost
        player1.c.removeAll();
        JTextArea output1 = new JTextArea(10, 10);
        player1.c.add(output1, "Center");
        output1.append("Sorry! You LOST!!");
        player2.c.removeAll();
        JTextArea output2 = new JTextArea(10, 10);
        player2.c.add(output2, "Center");
        output2.append("Sorry! You LOST!!");
        ++actioncount;
      }
    }
      else if (isPlayer2Turn() == true) {      //everything that happens to player 1 happens to player 2
        if (p2lost == false && p1lost == false) {
          if (isCorrectMove() == true) {
            if (this.actioncount < 2) {
              clickedbtn.setConnections(player2.getHighlighted());
              clickedbtn.addStone(Color.GREEN, getStoneStop(clickedbtn, 2));
              this.stonestop2 = getStoneStop(clickedbtn, 2);
              this.player2lastbtn = clickedbtn;
              ++actioncount;
            }
            else {
              clickedbtn.setConnections(player2.getHighlighted());
              player2lastbtn.removeStone(stonestop2);
              clickedbtn.addStone(Color.GREEN, getStoneStop(clickedbtn, Tsuro.getConnectEndpt(stonestop2)));
              stonestop2 = getStoneStop(clickedbtn, Tsuro.getConnectEndpt(stonestop2));
              player2lastbtn = clickedbtn;  
              if (isAdjacent(player1lastbtn, player2lastbtn) == true) {
                if (Tsuro.getConnectEndpt(stonestop2) != stonestop1) {
                  while (getP1Adjacent(player1lastbtn) != null && getP1Adjacent(player1lastbtn).getConnections() != null) {
                    TsuroButton nextbtn = getP1Adjacent(player1lastbtn);
                    player1lastbtn.removeStone(stonestop1);
                    nextbtn.addStone(Color.BLUE, getStoneStop(nextbtn, Tsuro.getConnectEndpt(stonestop1)));
                    stonestop1 = getStoneStop(nextbtn, Tsuro.getConnectEndpt(stonestop1));
                    player1lastbtn = nextbtn;
                    p1moved = true;
                  }
                  while (getP2Adjacent(player2lastbtn) != null && getP2Adjacent(player2lastbtn).getConnections() != null) {
                    TsuroButton nextbtn = getP2Adjacent(player2lastbtn);
                    player2lastbtn.removeStone(stonestop2);
                    nextbtn.addStone(Color.GREEN, getStoneStop(nextbtn, Tsuro.getConnectEndpt(stonestop2)));
                    stonestop2 = getStoneStop(nextbtn, Tsuro.getConnectEndpt(stonestop2));
                    player2lastbtn = nextbtn;
                  }
                  ++actioncount;
                }
                else {
                  p1lost = true;
                  p2lost = true;
                }
              }
              else {
                while (getP1Adjacent(player1lastbtn) != null && getP1Adjacent(player1lastbtn).getConnections() != null) {
                    TsuroButton nextbtn = getP1Adjacent(player1lastbtn);
                    player1lastbtn.removeStone(stonestop1);
                    nextbtn.addStone(Color.BLUE, getStoneStop(nextbtn, Tsuro.getConnectEndpt(stonestop1)));
                    stonestop1 = getStoneStop(nextbtn, Tsuro.getConnectEndpt(stonestop1));
                    player1lastbtn = nextbtn;
                    p1moved = true;
                  }
                  while (getP2Adjacent(player2lastbtn) != null && getP2Adjacent(player2lastbtn).getConnections() != null) {
                    TsuroButton nextbtn = getP2Adjacent(player2lastbtn);
                    player2lastbtn.removeStone(stonestop2);
                    nextbtn.addStone(Color.GREEN, getStoneStop(nextbtn, Tsuro.getConnectEndpt(stonestop2)));
                    stonestop2 = getStoneStop(nextbtn, Tsuro.getConnectEndpt(stonestop2));
                    player2lastbtn = nextbtn;
                  }
                  ++actioncount;
                  if (p1GameOver() == true)
                    p1lost = true;
              }
            }
            if (p2GameOver() == true)
              p2lost = true;
            for (int i = 0; i < player2.playerbuttons.length; i++) {
              if (player2.isHighlighted(player2.playerbuttons[i]) == true) {
                player2.playerbuttons[i].setConnections(TsuroButton.makeRandomConnectArray());
                player2.playerbuttons[i].setBackground(Color.WHITE);
              }
            }
            for (int i = 0; i < player2.playerbuttons.length; i++) {
              Tsuro.removeAllStone(player2.playerbuttons[i]);
              player2.playerbuttons[i].addStone(Color.GREEN, Tsuro.getConnectEndpt(stonestop2));
            }
            if (p1moved == true) {
              for (int i = 0; i < player1.playerbuttons.length; i++) {
                if (player1.isHighlighted(player1.playerbuttons[i]) == true) {
                  player1.playerbuttons[i].setConnections(TsuroButton.makeRandomConnectArray());
                  player1.playerbuttons[i].setBackground(Color.WHITE);
                }
              }
              for (int i = 0; i < player1.playerbuttons.length; i++) {
                Tsuro.removeAllStone(player1.playerbuttons[i]);
                player1.playerbuttons[i].addStone(Color.BLUE, Tsuro.getConnectEndpt(stonestop1));
              }
            }
          }
        }
        else if (p2lost == false && p1lost == true) {
          player2.c.removeAll();
          JTextArea output2 = new JTextArea(10, 10);
          player2.c.add(output2, "Center");
          output2.append("Congratulations! You WON!");
          player1.c.removeAll();
          JTextArea output1 = new JTextArea(10, 10);
          player1.c.add(output1, "Center");
          output1.append("Sorry! you LOST!!");
          ++actioncount;
        }
        else if (p2lost == true && p1lost == false) {
          player2.c.removeAll();
          JTextArea output2 = new JTextArea(10, 10);
          player2.c.add(output2, "Center");
          output2.append("Sorry! You LOST!!");
          player1.c.removeAll();
          JTextArea output1 = new JTextArea(10, 10);
          player1.c.add(output1, "Center");
          output1.append("Congratulations! You WON!");
          ++actioncount;
        }
        else if (p2lost == true && p1lost == true) {
          player2.c.removeAll();
          JTextArea output2 = new JTextArea(10, 10);
          player2.c.add(output2, "Center");
          output2.append("Sorry! You LOST!!");
          player1.c.removeAll();
          JTextArea output1 = new JTextArea(10, 10);
          player1.c.add(output1, "Center");
          output1.append("Sorry! You LOST!!");
          ++actioncount;
        }
      }
  }
  
  /** a class that represents the player boards
    * @author TungHo Lin
    */ 
  public class Players extends JFrame implements ActionListener {
    
    /** the panel of the player board */
    private JPanel playerpanel;
    
    /** the hands' buttons on the player board */
    private TsuroButton[] playerbuttons;
    
    /** the title of each player board window */
    private String title;
    
    /** the container of each player board */
    private Container c;
    
    /**
     * the constructor that creates the player boards with the desired title
     * @param title the title of the player boards
     */ 
    public Players (String title) {
      super();
      try {
        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
      }
      catch (Exception e) {
      }
      this.playerpanel = new JPanel(new GridLayout(1, handsize));
      this.playerbuttons = new TsuroButton[handsize];
      this.title = title;
      this.c = this.getContentPane();
      for (int i = 0; i < playerbuttons.length; i++) {
        playerbuttons[i] = new TsuroButton();
        playerbuttons[i].addActionListener(this);
        playerbuttons[i].setConnections(TsuroButton.makeRandomConnectArray());
        playerpanel.add(playerbuttons[i]);
      }
      c.add(playerpanel, "Center");
      this.setSize(120 * handsize, 135);
      this.setTitle(title);
      this.setVisible(true);
    }
    
    /**
     * a method to detect the click of a mouse on the player board and perform actions accordingly
     * @param e the detected mouse click on the player board
     */ 
    public void actionPerformed(ActionEvent e) {
      TsuroButton b = (TsuroButton)e.getSource();
      if (this.title == "Player 1") {     //if the button clicked is from player 1 board
        for (int i = 0; i < playerbuttons.length; i++) {
          if (isPlayer1Turn() == true) {
            if (playerbuttons[i] == b) {     //find the button in player 1 board
              if (isHighlighted(playerbuttons[i]) == false)     //and if the button is not highlighted, a click will highlight it in yellow
                playerbuttons[i].setBackground(Color.YELLOW);
              else
                playerbuttons[i] = Tsuro.rotate90(playerbuttons[i]);    //and if the button is highlighted, a click will turn it 90 CW
            }
            else 
              playerbuttons[i].setBackground(Color.WHITE);    //other unrelated buttons will have white background(unhighlighted)
          }
        }
      }
      else if (this.title == "Player 2") {
        for (int i = 0; i < playerbuttons.length; i++) {
          if (isPlayer2Turn() == true) {
            if (playerbuttons[i] == b) {
              if (isHighlighted(playerbuttons[i]) == false) 
                playerbuttons[i].setBackground(Color.YELLOW);
              else
                playerbuttons[i] = Tsuro.rotate90(playerbuttons[i]);
            }
            else 
              playerbuttons[i].setBackground(Color.WHITE);
          }
        }
      }
    }
    
    /** 
     * a method to check if another hand button is highlighted
     * @param b1 an input TsuroButton 
     * @return true if the input TsuroButton is highlighted
     */ 
    public boolean isHighlighted(TsuroButton b1) {
      if (b1.getBackground() == Color.YELLOW)     //check to see if the input Button is highlighted
        return true;
      else
        return false;
    }
    
    /**
     * a method to check if there is any highlighted hand button in the player board
     * @return true if there is a highlighted button in the player board
     */
    public boolean anyHighlighted() {
      boolean any = false;
      for (int i = 0; i < playerbuttons.length; i++) {     //check to see if any of the player buttons are highlighted
        if (isHighlighted(playerbuttons[i]) == true)
          any = true;
      }
      return any;
    }
    
    /** 
     * a method to get the highlighted button's connection paths in the form of an array
     * @return an array that represents the highlighted button's connection paths
     */ 
    public int[] getHighlighted() {
      for (int i = 0; i < playerbuttons.length; i++) {     //get the highlighted button's connections
        if (isHighlighted(playerbuttons[i]) == true)
          return playerbuttons[i].getConnections();
      }
      return null;
    }
  }
}