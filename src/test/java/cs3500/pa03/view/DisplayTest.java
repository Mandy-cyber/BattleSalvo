package cs3500.pa03.view;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DisplayTest {

  /**
   * field for appendable
   */
  private Appendable appendable;

  /**
   * field for questionview
   */
  private Display writer;

  /**
   * setup
   */
  @BeforeEach
  public void setUp() {
    this.appendable = new StringBuilder();
    this.writer = new Display(this.appendable);
  }

  /**
   * tests displayWelcome
   *
   * @throws IOException error
   */
  @Test
  public void testdisplayWelcome() throws IOException {
    // check empty StringBuilder
    assertEquals("", this.appendable.toString());

    // write to it
    this.writer.displayWelcome();

    // check only that value appears in the StringBuilder
    assertEquals("Hello! Welcome to the OOD BattleSalvo Game! \n"
            + "Please enter a valid height and width below:\n",
        this.appendable.toString());
  }

  /**
   * tests displayQuestionNumberMessage
   *
   * @throws IOException error
   */
  @Test
  public void testddisplayInvalidDim() throws IOException {
    // check empty StringBuilder
    assertEquals("", this.appendable.toString());

    // write to it
    this.writer.displayInvalidDim();

    // check only that value appears in the StringBuilder
    assertEquals("Uh Oh! You've entered invalid dimensions. Please remember that the height "
            + "and width\n" + "of the game must be in the range (6, 15), inclusive. Try again!\n",
        this.appendable.toString());
  }

  /**
   * tests displayQuestion
   *
   * @throws IOException error
   */
  @Test
  public void testdisplayFleetSelection() throws IOException {
    assertEquals("", this.appendable.toString());

    // write to it
    this.writer.displayFleetSelection(5);

    // check only that value appears in the StringBuilder
    assertEquals("Please enter your fleet in the order "
            + "[Carrier, Battleship, Destroyer, Submarine].\n"
            + "Remember, your fleet must include at least 1 ship of each "
            + "type and may not exceed size 5 in total.\n",
        this.appendable.toString());
  }

  /**
   * tests displayAnswer
   *
   * @throws IOException error
   */
  @Test
  public void testdisplayInvalidFleetSize() throws IOException {
    assertEquals("", this.appendable.toString());

    // write to it
    this.writer.displayInvalidFleetSize();

    // check only that value appears in the StringBuilder
    assertEquals("Uh Oh! You've entered invalid fleet sizes.\n",
        this.appendable.toString());
  }

  /**
   * tests displayOptions
   *
   * @throws IOException error
   */
  @Test
  public void testdisplayBoard() throws IOException {
    // check empty StringBuilder
    assertEquals("", this.appendable.toString());

    char[][] expectedBoard = {{'0', '0', '0', '0', '0', 'S', 'S', 'S', '0', '0'},
        {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
        {'S', '0', 'S', 'S', 'S', 'S', '0', '0', 'S', 'S'},
        {'S', '0', 'S', '0', '0', '0', '0', '0', 'S', 'S'},
        {'S', '0', 'S', '0', '0', '0', '0', '0', 'S', 'S'},
        {'S', '0', 'S', '0', '0', '0', '0', '0', 'S', 'S'},
        {'S', '0', 'S', '0', '0', '0', 'S', 'S', 'S', '0'},
        {'0', '0', 'S', '0', '0', '0', '0', '0', '0', '0'},
        {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
        {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0'}};

    // write to it
    this.writer.displayBoard(expectedBoard);

    // check only that value appears in the StringBuilder
    assertEquals(
        "0 0 0 0 0 S S S 0 0 \n"
            + "0 0 0 0 0 0 0 0 0 0 \n"
            + "S 0 S S S S 0 0 S S \n"
            + "S 0 S 0 0 0 0 0 S S \n"
            + "S 0 S 0 0 0 0 0 S S \n"
            + "S 0 S 0 0 0 0 0 S S \n" + "S 0 S 0 0 0 S S S 0 \n" + "0 0 S 0 0 0 0 0 0 0 \n"
            + "0 0 0 0 0 0 0 0 0 0 \n" + "0 0 0 0 0 0 0 0 0 0 \n",
        this.appendable.toString());
  }

  @Test
  public void testdisplayOpponentBoard() throws IOException {
    // check empty StringBuilder
    assertEquals("", this.appendable.toString());

    char[][] expectedBoard = {{'0', '0', '0', '0', '0', 'S', 'S', 'S', '0', '0'},
        {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
        {'S', '0', 'S', 'S', 'S', 'S', '0', '0', 'S', 'S'},
        {'S', '0', 'S', '0', '0', '0', '0', '0', 'S', 'S'},
        {'S', '0', 'S', '0', '0', '0', '0', '0', 'S', 'S'},
        {'S', '0', 'S', '0', '0', '0', '0', '0', 'S', 'S'},
        {'S', '0', 'S', '0', '0', '0', 'S', 'S', 'S', '0'},
        {'0', '0', 'S', '0', '0', '0', '0', '0', '0', '0'},
        {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
        {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0'}};

    // write to it
    this.writer.displayOpponentBoard(expectedBoard);

    // check only that value appears in the StringBuilder
    assertEquals(
        "Your Opponents Board: \n"
            + "0 0 0 0 0 S S S 0 0 \n" + "0 0 0 0 0 0 0 0 0 0 \n" + "S 0 S S S S 0 0 S S \n"
            + "S 0 S 0 0 0 0 0 S S \n" + "S 0 S 0 0 0 0 0 S S \n" + "S 0 S 0 0 0 0 0 S S \n"
            + "S 0 S 0 0 0 S S S 0 \n" + "0 0 S 0 0 0 0 0 0 0 \n" + "0 0 0 0 0 0 0 0 0 0 \n"
            + "0 0 0 0 0 0 0 0 0 0 \n",
        this.appendable.toString());
  }

  @Test
  public void testdisplayUserBoard() throws IOException {
    // check empty StringBuilder
    assertEquals("", this.appendable.toString());

    char[][] expectedBoard = {{'0', '0', '0', '0', '0', 'S', 'S', 'S', '0', '0'},
        {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
        {'S', '0', 'S', 'S', 'S', 'S', '0', '0', 'S', 'S'},
        {'S', '0', 'S', '0', '0', '0', '0', '0', 'S', 'S'},
        {'S', '0', 'S', '0', '0', '0', '0', '0', 'S', 'S'},
        {'S', '0', 'S', '0', '0', '0', '0', '0', 'S', 'S'},
        {'S', '0', 'S', '0', '0', '0', 'S', 'S', 'S', '0'},
        {'0', '0', 'S', '0', '0', '0', '0', '0', '0', '0'},
        {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
        {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0'}};

    // write to it
    this.writer.displayUserBoard(expectedBoard);

    // check only that value appears in the StringBuilder
    assertEquals(
        "Your Board: \n"
            + "0 0 0 0 0 S S S 0 0 \n" + "0 0 0 0 0 0 0 0 0 0 \n" + "S 0 S S S S 0 0 S S \n"
            + "S 0 S 0 0 0 0 0 S S \n" + "S 0 S 0 0 0 0 0 S S \n" + "S 0 S 0 0 0 0 0 S S \n"
            + "S 0 S 0 0 0 S S S 0 \n" + "0 0 S 0 0 0 0 0 0 0 \n" + "0 0 0 0 0 0 0 0 0 0 \n"
            + "0 0 0 0 0 0 0 0 0 0 \n",
        this.appendable.toString());
  }

  @Test
  public void testaskForShots() throws IOException {

    assertEquals("", this.appendable.toString());

    // write to it
    this.writer.askForShots(3);

    // check only that value appears in the StringBuilder
    assertEquals("Please Enter 3 Shots:\n",
        this.appendable.toString());

  }

  /**
   * tests displayEnd
   *
   * @throws IOException error
   */
  @Test
  public void testdisplayEndlose() throws IOException {
    // check empty StringBuilder
    assertEquals("", this.appendable.toString());

    // write to it
    this.writer.displayEnd(-2);

    // check only that value appears in the StringBuilder
    assertEquals("You lost this game :(",
        this.appendable.toString());
  }

  @Test
  public void testdisplayEndwin() throws IOException {
    // check empty StringBuilder
    assertEquals("", this.appendable.toString());

    // write to it
    this.writer.displayEnd(3);

    // check only that value appears in the StringBuilder
    assertEquals("You won this game!",
        this.appendable.toString());
  }

  @Test
  public void testdisplayEnddraw() throws IOException {
    // check empty StringBuilder
    assertEquals("", this.appendable.toString());

    // write to it
    this.writer.displayEnd(0);

    // check only that value appears in the StringBuilder
    assertEquals("The game ended with a Draw",
        this.appendable.toString());
  }
}

