package cs3500.pa03.view;


import java.io.IOException;
import java.util.Objects;

/**
 * class to hold the display for view
 */
public class Display {

  /**
   * field to hold appendable
   */
  private final Appendable output;

  /**
   * constuctor to make a view
   *
   * @param output where rto add to
   */
  public Display(Appendable output) {
    this.output = Objects.requireNonNull(output);
  }

  /**
   * displays welcome
   *
   * @throws IOException error
   */
  public void displayWelcome() throws IOException {
    output.append("Hello! Welcome to the OOD BattleSalvo Game! \n"
        + "Please enter a valid height and width below:\n");
  }

  /**
   * displays invalid dimension message
   *
   * @throws IOException error
   */
  public void displayInvalidDim() throws IOException {
    output.append("Uh Oh! You've entered invalid dimensions. Please remember that the height "
        + "and width\n" + "of the game must be in the range (6, 15), inclusive. Try again!\n");
  }

  /**
   * displays the fleet selection message
   *
   * @param num max fleet
   * @throws IOException error
   */
  public void displayFleetSelection(int num) throws IOException {
    output.append("Please enter your fleet in the order "
        + "[Carrier, Battleship, Destroyer, Submarine].\n"
        + "Remember, your fleet must include at least 1 ship of each "
        + "type and may not exceed size " + num + " in total.\n");
  }

  /**
   * displays invalid fleet chosen message
   *
   * @throws IOException error
   */
  public void displayInvalidFleetSize() throws IOException {
    output.append("Uh Oh! You've entered invalid fleet sizes.\n");
  }

  /**
   * displays a board
   *
   * @param board board to show
   * @throws IOException error
   */
  public void displayBoard(char[][] board) throws IOException {
    for (char[] row : board) {
      for (char element : row) {
        output.append(element + " ");
      }
      output.append("\n");
    }
  }

  /**
   * displays opponenet board
   *
   * @param board board to show
   * @throws IOException error
   */
  public void displayOpponentBoard(char[][] board) throws IOException {
    output.append("Your Opponents Board: \n");
    displayBoard(board);
  }

  /**
   * shoes the users board
   *
   * @param board board to display
   * @throws IOException error
   */
  public void displayUserBoard(char[][] board) throws IOException {
    output.append("Your Board: \n");
    displayBoard(board);
  }

  /**
   * displays ask for shots message
   *
   * @param num number of shots to ask for
   * @throws IOException error
   */
  public void askForShots(int num) throws IOException {
    output.append("Please Enter " + num + " Shots:\n");
  }

  /**
   * displays end message
   *
   * @param result how game ended
   * @throws IOException error
   */
  public void displayEnd(int result) throws IOException {
    if (result == 0) {
      output.append("The game ended with a Draw");
    } else if (result > 0) {
      output.append("You won this game!");
    } else if (result < 0) {
      output.append("You lost this game :(");
    }
  }
}
