package cs3500.pa03.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa03.model.BoardSetup;
import cs3500.pa03.view.Display;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.junit.jupiter.api.Test;

/**
 * class to test gamecontroller
 */
class GameControllerTest {

  /**
   * test for run study session
   *
   * @throws IOException error
   */
  @Test
  public void testrunStudySession() throws IOException {

    InputStream in;

    in = new ByteArrayInputStream(
        ("0 3\n" + "6 6\n" + "5 1 1 3\n" + "1 1 1 1\n" + "1 0 2 0 4 0 0 1\n" + "1 1 2 1 3 1 0 2\n"
            + "4 1 4 2 1 3 2 3\n" + "1 2 2 2 0 3 0 4\n" + "1 4 2 4 2 5 5 5\n").getBytes());

    //Readable input = new ByteArrayInputStream(stringArray);
    Appendable output = new StringBuilder();

    Display display = new Display(output);

    BoardSetup bsu = new BoardSetup(4);
    BoardSetup bsc = new BoardSetup(5);

    GameController controller = new GameController(in, display, bsu, bsc);

    // check that the StringBuilder is empty to start
    assertEquals("", output.toString());

    // run the workflow
    controller.runBattleSalvo();

    // check that the StringBuilder collected the correct output
    assertEquals("Hello! Welcome to the OOD BattleSalvo Game! \n"
        + "Please enter a valid height and width below:\n"
        + "Uh Oh! You've entered invalid dimensions. Please remember that the height and width\n"
        + "of the game must be in the range (6, 15), inclusive. Try again!\n"
        + "Please enter your fleet in the order [Carrier, Battleship, Destroyer, Submarine].\n"
        + "Remember, your fleet must include at least 1 ship of each type and may not exceed "
        + "size 6 in total.\n"
        + "Uh Oh! You've entered invalid fleet sizes.\n" + "Your Opponents Board: \n"
        + "0 0 0 0 0 0 \n" + "0 0 0 0 0 0 \n" + "0 0 0 0 0 0 \n" + "0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n" + "0 0 0 0 0 0 \n" + "Your Board: \n" + "0 S 0 0 S S \n"
        + "S S 0 0 S S \n" + "S S 0 0 S S \n" + "S S 0 0 S S \n" + "0 S 0 0 S 0 \n"
        + "0 0 0 0 S 0 \n" + "Please Enter 4 Shots:\n" + "Your Opponents Board: \n"
        + "0 H H 0 H 0 \n" + "H 0 0 0 0 0 \n" + "0 0 0 0 0 0 \n" + "0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n" + "0 0 0 0 0 0 \n" + "Your Board: \n" + "M H M M S S \n"
        + "S S 0 0 S S \n" + "S S 0 0 S S \n" + "S S 0 0 S S \n" + "0 S 0 0 S 0 \n"
        + "0 0 0 0 S 0 \n" + "Please Enter 4 Shots:\n" + "Your Opponents Board: \n"
        + "0 H H 0 H 0 \n" + "H H H M 0 0 \n" + "H 0 0 0 0 0 \n" + "0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n" + "0 0 0 0 0 0 \n" + "Your Board: \n" + "M H M M H H \n"
        + "H H 0 0 S S \n" + "S S 0 0 S S \n" + "S S 0 0 S S \n" + "0 S 0 0 S 0 \n"
        + "0 0 0 0 S 0 \n" + "Please Enter 4 Shots:\n" + "Your Opponents Board: \n"
        + "0 H H 0 H 0 \n" + "H H H M H 0 \n" + "H 0 0 0 H 0 \n" + "0 H H 0 0 0 \n"
        + "0 0 0 0 0 0 \n" + "0 0 0 0 0 0 \n" + "Your Board: \n" + "M H M M H H \n"
        + "H H M M H H \n" + "S S 0 0 S S \n" + "S S 0 0 S S \n" + "0 S 0 0 S 0 \n"
        + "0 0 0 0 S 0 \n" + "Please Enter 4 Shots:\n" + "Your Opponents Board: \n"
        + "0 H H 0 H 0 \n" + "H H H M H 0 \n" + "H H H 0 H 0 \n" + "H H H 0 0 0 \n"
        + "H 0 0 0 0 0 \n" + "0 0 0 0 0 0 \n" + "Your Board: \n" + "M H M M H H \n"
        + "H H M M H H \n" + "H H M 0 S S \n" + "S S 0 0 S S \n" + "0 S 0 0 S 0 \n"
        + "0 0 0 0 S 0 \n" + "Please Enter 4 Shots:\n" + "Your Opponents Board: \n"
        + "0 H H 0 H 0 \n" + "H H H M H 0 \n" + "H H H 0 H 0 \n" + "H H H 0 0 0 \n"
        + "H H H 0 0 0 \n" + "0 0 H 0 0 M \n" + "Your Board: \n" + "M H M M H H \n"
        + "H H M M H H \n" + "H H M M H S \n" + "S S 0 0 S S \n" + "0 S 0 0 S 0 \n"
        + "0 0 0 0 S 0 \n" + "You won this game!", output.toString());
  }

  /**
   * tests runstudysession
   *
   * @throws IOException error
   */
  @Test
  public void testrunStudySession2() throws IOException {

    InputStream in;

    in = new ByteArrayInputStream(
        ("20 18\n" + "6 6\n" + "0 1 1 1\n" + "1 1 1 1\n" + "1 0 2 0 10 0 0 1\n"
            + "1 0 2 0 4 0 0 1\n" + "1 1 2 1 3 1 0 2\n"
            + "4 1 4 2 1 3 2 3\n" + "1 2 2 2 0 3 0 4\n" + "1 4 2 4 2 5 5 5\n").getBytes());

    //Readable input = new ByteArrayInputStream(stringArray);
    Appendable output = new StringBuilder();

    Display display = new Display(output);

    BoardSetup bsu = new BoardSetup(4);
    BoardSetup bsc = new BoardSetup(5);

    GameController controller = new GameController(in, display, bsu, bsc);

    // check that the StringBuilder is empty to start
    assertEquals("", output.toString());

    // run the workflow
    controller.runBattleSalvo();

    // check that the StringBuilder collected the correct output
    assertEquals("Hello! Welcome to the OOD BattleSalvo Game! \n"
        + "Please enter a valid height and width below:\n"
        + "Uh Oh! You've entered invalid dimensions. Please remember that the height and width\n"
        + "of the game must be in the range (6, 15), inclusive. Try again!\n"
        + "Please enter your fleet in the order [Carrier, Battleship, Destroyer, Submarine].\n"
        + "Remember, your fleet must include at least 1 ship of each type "
        + "and may not exceed size 6 in total.\n" + "Uh Oh! You've entered invalid fleet sizes.\n"
        + "Your Opponents Board: \n"
        + "0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n" + "0 0 0 0 0 0 \n" + "0 0 0 0 0 0 \n" + "0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n" + "Your Board: \n" + "0 S 0 0 S S \n" + "S S 0 0 S S \n"
        + "S S 0 0 S S \n" + "S S 0 0 S S \n" + "0 S 0 0 S 0 \n" + "0 0 0 0 S 0 \n"
        + "Please Enter 4 Shots:\n" + "Please Enter 4 Shots:\n" + "Your Opponents Board: \n"
        + "0 H H 0 H 0 \n" + "H 0 0 0 0 0 \n" + "0 0 0 0 0 0 \n" + "0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n" + "0 0 0 0 0 0 \n" + "Your Board: \n" + "M H M M S S \n"
        + "S S 0 0 S S \n" + "S S 0 0 S S \n" + "S S 0 0 S S \n" + "0 S 0 0 S 0 \n"
        + "0 0 0 0 S 0 \n" + "Please Enter 4 Shots:\n" + "Your Opponents Board: \n"
        + "0 H H 0 H 0 \n" + "H H H M 0 0 \n" + "H 0 0 0 0 0 \n" + "0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n" + "0 0 0 0 0 0 \n" + "Your Board: \n" + "M H M M H H \n"
        + "H H 0 0 S S \n" + "S S 0 0 S S \n" + "S S 0 0 S S \n" + "0 S 0 0 S 0 \n"
        + "0 0 0 0 S 0 \n" + "Please Enter 4 Shots:\n" + "Your Opponents Board: \n"
        + "0 H H 0 H 0 \n" + "H H H M H 0 \n" + "H 0 0 0 H 0 \n" + "0 H H 0 0 0 \n"
        + "0 0 0 0 0 0 \n" + "0 0 0 0 0 0 \n" + "Your Board: \n" + "M H M M H H \n"
        + "H H M M H H \n" + "S S 0 0 S S \n" + "S S 0 0 S S \n" + "0 S 0 0 S 0 \n"
        + "0 0 0 0 S 0 \n" + "Please Enter 4 Shots:\n" + "Your Opponents Board: \n"
        + "0 H H 0 H 0 \n" + "H H H M H 0 \n" + "H H H 0 H 0 \n" + "H H H 0 0 0 \n"
        + "H 0 0 0 0 0 \n" + "0 0 0 0 0 0 \n" + "Your Board: \n" + "M H M M H H \n"
        + "H H M M H H \n" + "H H M 0 S S \n" + "S S 0 0 S S \n" + "0 S 0 0 S 0 \n"
        + "0 0 0 0 S 0 \n" + "Please Enter 4 Shots:\n" + "Your Opponents Board: \n"
        + "0 H H 0 H 0 \n" + "H H H M H 0 \n" + "H H H 0 H 0 \n" + "H H H 0 0 0 \n"
        + "H H H 0 0 0 \n" + "0 0 H 0 0 M \n" + "Your Board: \n" + "M H M M H H \n"
        + "H H M M H H \n" + "H H M M H S \n" + "S S 0 0 S S \n" + "0 S 0 0 S 0 \n"
        + "0 0 0 0 S 0 \n" + "You won this game!", output.toString());
  }

  /**
   * tests run study session
   *
   * @throws IOException error
   */
  @Test
  public void testrunStudySession3() throws IOException {

    InputStream in;

    in = new ByteArrayInputStream(
        ("10 18\n" + "6 6\n" + "1 0 1 1\n" + "1 1 1 1\n" + "1 0 2 0 4 0 0 1\n" + "1 1 2 1 3 1 0 2\n"
            + "4 1 4 2 1 3 2 3\n" + "1 2 2 2 0 3 0 4\n" + "1 4 2 4 2 5 5 5\n").getBytes());

    //Readable input = new ByteArrayInputStream(stringArray);
    Appendable output = new StringBuilder();

    Display display = new Display(output);

    BoardSetup bsu = new BoardSetup(4);
    BoardSetup bsc = new BoardSetup(5);

    GameController controller = new GameController(in, display, bsu, bsc);

    // check that the StringBuilder is empty to start
    assertEquals("", output.toString());

    // run the workflow
    controller.runBattleSalvo();

    // check that the StringBuilder collected the correct output
    assertEquals("Hello! Welcome to the OOD BattleSalvo Game! \n"
        + "Please enter a valid height and width below:\n"
        + "Uh Oh! You've entered invalid dimensions. Please remember that the height and width\n"
        + "of the game must be in the range (6, 15), inclusive. Try again!\n"
        + "Please enter your fleet in the order [Carrier, Battleship, Destroyer, Submarine].\n"
        + "Remember, your fleet must include at least 1 ship of each type and may not exceed "
        + "size 6 in total.\n"
        + "Uh Oh! You've entered invalid fleet sizes.\n" + "Your Opponents Board: \n"
        + "0 0 0 0 0 0 \n" + "0 0 0 0 0 0 \n" + "0 0 0 0 0 0 \n" + "0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n" + "0 0 0 0 0 0 \n" + "Your Board: \n" + "0 S 0 0 S S \n"
        + "S S 0 0 S S \n" + "S S 0 0 S S \n" + "S S 0 0 S S \n" + "0 S 0 0 S 0 \n"
        + "0 0 0 0 S 0 \n" + "Please Enter 4 Shots:\n" + "Your Opponents Board: \n"
        + "0 H H 0 H 0 \n" + "H 0 0 0 0 0 \n" + "0 0 0 0 0 0 \n" + "0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n" + "0 0 0 0 0 0 \n" + "Your Board: \n" + "M H M M S S \n"
        + "S S 0 0 S S \n" + "S S 0 0 S S \n" + "S S 0 0 S S \n" + "0 S 0 0 S 0 \n"
        + "0 0 0 0 S 0 \n" + "Please Enter 4 Shots:\n" + "Your Opponents Board: \n"
        + "0 H H 0 H 0 \n" + "H H H M 0 0 \n" + "H 0 0 0 0 0 \n" + "0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n" + "0 0 0 0 0 0 \n" + "Your Board: \n" + "M H M M H H \n"
        + "H H 0 0 S S \n" + "S S 0 0 S S \n" + "S S 0 0 S S \n" + "0 S 0 0 S 0 \n"
        + "0 0 0 0 S 0 \n" + "Please Enter 4 Shots:\n" + "Your Opponents Board: \n"
        + "0 H H 0 H 0 \n" + "H H H M H 0 \n" + "H 0 0 0 H 0 \n" + "0 H H 0 0 0 \n"
        + "0 0 0 0 0 0 \n" + "0 0 0 0 0 0 \n" + "Your Board: \n" + "M H M M H H \n"
        + "H H M M H H \n" + "S S 0 0 S S \n" + "S S 0 0 S S \n" + "0 S 0 0 S 0 \n"
        + "0 0 0 0 S 0 \n" + "Please Enter 4 Shots:\n" + "Your Opponents Board: \n"
        + "0 H H 0 H 0 \n" + "H H H M H 0 \n" + "H H H 0 H 0 \n" + "H H H 0 0 0 \n"
        + "H 0 0 0 0 0 \n" + "0 0 0 0 0 0 \n" + "Your Board: \n" + "M H M M H H \n"
        + "H H M M H H \n" + "H H M 0 S S \n" + "S S 0 0 S S \n" + "0 S 0 0 S 0 \n"
        + "0 0 0 0 S 0 \n" + "Please Enter 4 Shots:\n" + "Your Opponents Board: \n"
        + "0 H H 0 H 0 \n" + "H H H M H 0 \n" + "H H H 0 H 0 \n" + "H H H 0 0 0 \n"
        + "H H H 0 0 0 \n" + "0 0 H 0 0 M \n" + "Your Board: \n" + "M H M M H H \n"
        + "H H M M H H \n" + "H H M M H S \n" + "S S 0 0 S S \n" + "0 S 0 0 S 0 \n"
        + "0 0 0 0 S 0 \n" + "You won this game!", output.toString());
  }

  /**
   * tests run study session
   *
   * @throws IOException error
   */
  @Test
  public void testrunStudySession4() throws IOException {

    InputStream in;

    in = new ByteArrayInputStream(
        ("18 11\n" + "6 6\n" + "1 1 0 1\n" + "1 1 1 1\n" + "1 0 2 0 4 0 0 1\n" + "1 1 2 1 3 1 0 2\n"
            + "4 1 4 2 1 3 2 3\n" + "1 2 2 2 0 3 0 4\n" + "1 4 2 4 2 5 5 5\n").getBytes());

    //Readable input = new ByteArrayInputStream(stringArray);
    Appendable output = new StringBuilder();

    Display display = new Display(output);

    BoardSetup bsu = new BoardSetup(4);
    BoardSetup bsc = new BoardSetup(5);

    GameController controller = new GameController(in, display, bsu, bsc);

    // check that the StringBuilder is empty to start
    assertEquals("", output.toString());

    // run the workflow
    controller.runBattleSalvo();

    // check that the StringBuilder collected the correct output
    assertEquals("Hello! Welcome to the OOD BattleSalvo Game! \n"
        + "Please enter a valid height and width below:\n"
        + "Uh Oh! You've entered invalid dimensions. Please remember that the height and width\n"
        + "of the game must be in the range (6, 15), inclusive. Try again!\n"
        + "Please enter your fleet in the order [Carrier, Battleship, Destroyer, Submarine].\n"
        + "Remember, your fleet must include at least 1 ship of each type and may not exceed "
        + "size 6 in total.\n"
        + "Uh Oh! You've entered invalid fleet sizes.\n" + "Your Opponents Board: \n"
        + "0 0 0 0 0 0 \n" + "0 0 0 0 0 0 \n" + "0 0 0 0 0 0 \n" + "0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n" + "0 0 0 0 0 0 \n" + "Your Board: \n" + "0 S 0 0 S S \n"
        + "S S 0 0 S S \n" + "S S 0 0 S S \n" + "S S 0 0 S S \n" + "0 S 0 0 S 0 \n"
        + "0 0 0 0 S 0 \n" + "Please Enter 4 Shots:\n" + "Your Opponents Board: \n"
        + "0 H H 0 H 0 \n" + "H 0 0 0 0 0 \n" + "0 0 0 0 0 0 \n" + "0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n" + "0 0 0 0 0 0 \n" + "Your Board: \n" + "M H M M S S \n"
        + "S S 0 0 S S \n" + "S S 0 0 S S \n" + "S S 0 0 S S \n" + "0 S 0 0 S 0 \n"
        + "0 0 0 0 S 0 \n" + "Please Enter 4 Shots:\n" + "Your Opponents Board: \n"
        + "0 H H 0 H 0 \n" + "H H H M 0 0 \n" + "H 0 0 0 0 0 \n" + "0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n" + "0 0 0 0 0 0 \n" + "Your Board: \n" + "M H M M H H \n"
        + "H H 0 0 S S \n" + "S S 0 0 S S \n" + "S S 0 0 S S \n" + "0 S 0 0 S 0 \n"
        + "0 0 0 0 S 0 \n" + "Please Enter 4 Shots:\n" + "Your Opponents Board: \n"
        + "0 H H 0 H 0 \n" + "H H H M H 0 \n" + "H 0 0 0 H 0 \n" + "0 H H 0 0 0 \n"
        + "0 0 0 0 0 0 \n" + "0 0 0 0 0 0 \n" + "Your Board: \n" + "M H M M H H \n"
        + "H H M M H H \n" + "S S 0 0 S S \n" + "S S 0 0 S S \n" + "0 S 0 0 S 0 \n"
        + "0 0 0 0 S 0 \n" + "Please Enter 4 Shots:\n" + "Your Opponents Board: \n"
        + "0 H H 0 H 0 \n" + "H H H M H 0 \n" + "H H H 0 H 0 \n" + "H H H 0 0 0 \n"
        + "H 0 0 0 0 0 \n" + "0 0 0 0 0 0 \n" + "Your Board: \n" + "M H M M H H \n"
        + "H H M M H H \n" + "H H M 0 S S \n" + "S S 0 0 S S \n" + "0 S 0 0 S 0 \n"
        + "0 0 0 0 S 0 \n" + "Please Enter 4 Shots:\n" + "Your Opponents Board: \n"
        + "0 H H 0 H 0 \n" + "H H H M H 0 \n" + "H H H 0 H 0 \n" + "H H H 0 0 0 \n"
        + "H H H 0 0 0 \n" + "0 0 H 0 0 M \n" + "Your Board: \n" + "M H M M H H \n"
        + "H H M M H H \n" + "H H M M H S \n" + "S S 0 0 S S \n" + "0 S 0 0 S 0 \n"
        + "0 0 0 0 S 0 \n" + "You won this game!", output.toString());
  }

  /**
   * tests run study session
   *
   * @throws IOException error
   */
  @Test
  public void testrunStudySession5() throws IOException {

    InputStream in;

    in = new ByteArrayInputStream(
        ("18 11\n" + "6 6\n" + "1 1 1 0\n" + "1 1 1 1\n" + "1 0 2 0 4 0 0 1\n" + "1 1 2 1 3 1 0 2\n"
            + "4 1 4 2 1 3 2 3\n" + "1 2 2 2 0 3 0 4\n" + "1 4 2 4 2 5 5 5\n").getBytes());

    //Readable input = new ByteArrayInputStream(stringArray);
    Appendable output = new StringBuilder();

    Display display = new Display(output);

    BoardSetup bsu = new BoardSetup(4);
    BoardSetup bsc = new BoardSetup(5);

    GameController controller = new GameController(in, display, bsu, bsc);

    // check that the StringBuilder is empty to start
    assertEquals("", output.toString());

    // run the workflow
    controller.runBattleSalvo();

    // check that the StringBuilder collected the correct output
    assertEquals("Hello! Welcome to the OOD BattleSalvo Game! \n"
        + "Please enter a valid height and width below:\n"
        + "Uh Oh! You've entered invalid dimensions. Please remember that the height and width\n"
        + "of the game must be in the range (6, 15), inclusive. Try again!\n"
        + "Please enter your fleet in the order [Carrier, Battleship, Destroyer, Submarine].\n"
        + "Remember, your fleet must include at least 1 ship of each type and may not exceed "
        + "size 6 in total.\n"
        + "Uh Oh! You've entered invalid fleet sizes.\n" + "Your Opponents Board: \n"
        + "0 0 0 0 0 0 \n" + "0 0 0 0 0 0 \n" + "0 0 0 0 0 0 \n" + "0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n" + "0 0 0 0 0 0 \n" + "Your Board: \n" + "0 S 0 0 S S \n"
        + "S S 0 0 S S \n" + "S S 0 0 S S \n" + "S S 0 0 S S \n" + "0 S 0 0 S 0 \n"
        + "0 0 0 0 S 0 \n" + "Please Enter 4 Shots:\n" + "Your Opponents Board: \n"
        + "0 H H 0 H 0 \n" + "H 0 0 0 0 0 \n" + "0 0 0 0 0 0 \n" + "0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n" + "0 0 0 0 0 0 \n" + "Your Board: \n" + "M H M M S S \n"
        + "S S 0 0 S S \n" + "S S 0 0 S S \n" + "S S 0 0 S S \n" + "0 S 0 0 S 0 \n"
        + "0 0 0 0 S 0 \n" + "Please Enter 4 Shots:\n" + "Your Opponents Board: \n"
        + "0 H H 0 H 0 \n" + "H H H M 0 0 \n" + "H 0 0 0 0 0 \n" + "0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n" + "0 0 0 0 0 0 \n" + "Your Board: \n" + "M H M M H H \n"
        + "H H 0 0 S S \n" + "S S 0 0 S S \n" + "S S 0 0 S S \n" + "0 S 0 0 S 0 \n"
        + "0 0 0 0 S 0 \n" + "Please Enter 4 Shots:\n" + "Your Opponents Board: \n"
        + "0 H H 0 H 0 \n" + "H H H M H 0 \n" + "H 0 0 0 H 0 \n" + "0 H H 0 0 0 \n"
        + "0 0 0 0 0 0 \n" + "0 0 0 0 0 0 \n" + "Your Board: \n" + "M H M M H H \n"
        + "H H M M H H \n" + "S S 0 0 S S \n" + "S S 0 0 S S \n" + "0 S 0 0 S 0 \n"
        + "0 0 0 0 S 0 \n" + "Please Enter 4 Shots:\n" + "Your Opponents Board: \n"
        + "0 H H 0 H 0 \n" + "H H H M H 0 \n" + "H H H 0 H 0 \n" + "H H H 0 0 0 \n"
        + "H 0 0 0 0 0 \n" + "0 0 0 0 0 0 \n" + "Your Board: \n" + "M H M M H H \n"
        + "H H M M H H \n" + "H H M 0 S S \n" + "S S 0 0 S S \n" + "0 S 0 0 S 0 \n"
        + "0 0 0 0 S 0 \n" + "Please Enter 4 Shots:\n" + "Your Opponents Board: \n"
        + "0 H H 0 H 0 \n" + "H H H M H 0 \n" + "H H H 0 H 0 \n" + "H H H 0 0 0 \n"
        + "H H H 0 0 0 \n" + "0 0 H 0 0 M \n" + "Your Board: \n" + "M H M M H H \n"
        + "H H M M H H \n" + "H H M M H S \n" + "S S 0 0 S S \n" + "0 S 0 0 S 0 \n"
        + "0 0 0 0 S 0 \n" + "You won this game!", output.toString());
  }

  /**
   * tests run study session for losing to AI
   *
   * @throws IOException error
   */
  @Test
  public void testrunStudySessionlose() throws IOException {

    InputStream in;

    in = new ByteArrayInputStream(
        ("0 3\n" + "6 6\n" + "5 1 1 3\n" + "1 1 1 1\n" + "0 0 0 0 0 0 0 0\n" + "0 0 0 0 0 0 0 0\n"
            + "0 0 0 0 0 0 0 0\n" + "0 0 0 0 0 0 0 0\n" + "0 0 0 0 0 0 0 0\n" + "0 0 0 0 0 0\n"
            + "0 0 0 0\n" + "0 0\n" + "0 0\n").getBytes());

    //Readable input = new ByteArrayInputStream(stringArray);
    Appendable output = new StringBuilder();

    Display display = new Display(output);

    BoardSetup bsu = new BoardSetup(4);
    BoardSetup bsc = new BoardSetup(5);

    GameController controller = new GameController(in, display, bsu, bsc);

    // check that the StringBuilder is empty to start
    assertEquals("", output.toString());

    // run the workflow
    controller.runBattleSalvo();

    // check that the StringBuilder collected the correct output
    assertEquals(
        "Hello! Welcome to the OOD BattleSalvo Game! \n"
            + "Please enter a valid height and width below:\n"
            + "Uh Oh! You've entered invalid dimensions. Please remember that "
            + "the height and width\n"
            + "of the game must be in the range (6, 15), inclusive. Try again!\n"
            + "Please enter your fleet in the order [Carrier, Battleship, Destroyer, Submarine].\n"
            + "Remember, your fleet must include at least 1 ship of each type and "
            + "may not exceed size 6 in total.\n"
            + "Uh Oh! You've entered invalid fleet sizes.\n"
            + "Your Opponents Board: \n"
            + "0 0 0 0 0 0 \n"
            + "0 0 0 0 0 0 \n"
            + "0 0 0 0 0 0 \n"
            + "0 0 0 0 0 0 \n"
            + "0 0 0 0 0 0 \n"
            + "0 0 0 0 0 0 \n"
            + "Your Board: \n"
            + "0 S 0 0 S S \n"
            + "S S 0 0 S S \n"
            + "S S 0 0 S S \n"
            + "S S 0 0 S S \n"
            + "0 S 0 0 S 0 \n"
            + "0 0 0 0 S 0 \n"
            + "Please Enter 4 Shots:\n"
            + "Your Opponents Board: \n"
            + "M 0 0 0 0 0 \n"
            + "0 0 0 0 0 0 \n"
            + "0 0 0 0 0 0 \n"
            + "0 0 0 0 0 0 \n"
            + "0 0 0 0 0 0 \n"
            + "0 0 0 0 0 0 \n"
            + "Your Board: \n"
            + "M H M M S S \n"
            + "S S 0 0 S S \n"
            + "S S 0 0 S S \n"
            + "S S 0 0 S S \n"
            + "0 S 0 0 S 0 \n"
            + "0 0 0 0 S 0 \n"
            + "Please Enter 4 Shots:\n"
            + "Your Opponents Board: \n"
            + "M 0 0 0 0 0 \n"
            + "0 0 0 0 0 0 \n"
            + "0 0 0 0 0 0 \n"
            + "0 0 0 0 0 0 \n"
            + "0 0 0 0 0 0 \n"
            + "0 0 0 0 0 0 \n"
            + "Your Board: \n"
            + "M H M M H H \n"
            + "H H 0 0 S S \n"
            + "S S 0 0 S S \n"
            + "S S 0 0 S S \n"
            + "0 S 0 0 S 0 \n"
            + "0 0 0 0 S 0 \n"
            + "Please Enter 4 Shots:\n"
            + "Your Opponents Board: \n"
            + "M 0 0 0 0 0 \n"
            + "0 0 0 0 0 0 \n"
            + "0 0 0 0 0 0 \n"
            + "0 0 0 0 0 0 \n"
            + "0 0 0 0 0 0 \n"
            + "0 0 0 0 0 0 \n"
            + "Your Board: \n"
            + "M H M M H H \n"
            + "H H M M H H \n"
            + "S S 0 0 S S \n"
            + "S S 0 0 S S \n"
            + "0 S 0 0 S 0 \n"
            + "0 0 0 0 S 0 \n"
            + "Please Enter 4 Shots:\n"
            + "Your Opponents Board: \n"
            + "M 0 0 0 0 0 \n"
            + "0 0 0 0 0 0 \n"
            + "0 0 0 0 0 0 \n"
            + "0 0 0 0 0 0 \n"
            + "0 0 0 0 0 0 \n"
            + "0 0 0 0 0 0 \n"
            + "Your Board: \n"
            + "M H M M H H \n"
            + "H H M M H H \n"
            + "H H M M S S \n"
            + "S S 0 0 S S \n"
            + "0 S 0 0 S 0 \n"
            + "0 0 0 0 S 0 \n"
            + "Please Enter 4 Shots:\n"
            + "Your Opponents Board: \n"
            + "M 0 0 0 0 0 \n"
            + "0 0 0 0 0 0 \n"
            + "0 0 0 0 0 0 \n"
            + "0 0 0 0 0 0 \n"
            + "0 0 0 0 0 0 \n"
            + "0 0 0 0 0 0 \n"
            + "Your Board: \n"
            + "M H M M H H \n"
            + "H H M M H H \n"
            + "H H M M H H \n"
            + "H H 0 0 S S \n"
            + "0 S 0 0 S 0 \n"
            + "0 0 0 0 S 0 \n"
            + "Please Enter 3 Shots:\n"
            + "Your Opponents Board: \n"
            + "M 0 0 0 0 0 \n"
            + "0 0 0 0 0 0 \n"
            + "0 0 0 0 0 0 \n"
            + "0 0 0 0 0 0 \n"
            + "0 0 0 0 0 0 \n"
            + "0 0 0 0 0 0 \n"
            + "Your Board: \n"
            + "M H M M H H \n"
            + "H H M M H H \n"
            + "H H M M H H \n"
            + "H H M M H H \n"
            + "0 S 0 0 S 0 \n"
            + "0 0 0 0 S 0 \n"
            + "Please Enter 2 Shots:\n"
            + "Your Opponents Board: \n"
            + "M 0 0 0 0 0 \n"
            + "0 0 0 0 0 0 \n"
            + "0 0 0 0 0 0 \n"
            + "0 0 0 0 0 0 \n"
            + "0 0 0 0 0 0 \n"
            + "0 0 0 0 0 0 \n"
            + "Your Board: \n"
            + "M H M M H H \n"
            + "H H M M H H \n"
            + "H H M M H H \n"
            + "H H M M H H \n"
            + "M H M M S 0 \n"
            + "0 0 0 0 S 0 \n"
            + "Please Enter 1 Shots:\n"
            + "Your Opponents Board: \n"
            + "M 0 0 0 0 0 \n"
            + "0 0 0 0 0 0 \n"
            + "0 0 0 0 0 0 \n"
            + "0 0 0 0 0 0 \n"
            + "0 0 0 0 0 0 \n"
            + "0 0 0 0 0 0 \n"
            + "Your Board: \n"
            + "M H M M H H \n"
            + "H H M M H H \n"
            + "H H M M H H \n"
            + "H H M M H H \n"
            + "M H M M H M \n"
            + "M M 0 0 S 0 \n"
            + "Please Enter 1 Shots:\n"
            + "Your Opponents Board: \n"
            + "M 0 0 0 0 0 \n"
            + "0 0 0 0 0 0 \n"
            + "0 0 0 0 0 0 \n"
            + "0 0 0 0 0 0 \n"
            + "0 0 0 0 0 0 \n"
            + "0 0 0 0 0 0 \n"
            + "Your Board: \n"
            + "M H M M H H \n"
            + "H H M M H H \n"
            + "H H M M H H \n"
            + "H H M M H H \n"
            + "M H M M H M \n"
            + "M M M M H M \n"
            + "You lost this game :(", output.toString());

  }
}