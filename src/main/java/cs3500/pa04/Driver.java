package cs3500.pa04;

import cs3500.pa03.controller.GameController;
import cs3500.pa03.model.Board;
import cs3500.pa03.model.BoardSetup;
import cs3500.pa03.model.Computer;
import cs3500.pa03.view.Display;
import cs3500.pa04.controller.ProxyController;
import cs3500.pa04.model.AiComputer;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;


/**
 * The main driver of this project.
 */
public class Driver {
  /**
   * This method connects to the server at the given host and port, builds a proxy referee
   * to handle communication with the server, and sets up a client player.
   *
   * @param host the server host
   * @param port the server port
   * @throws IOException if there is a communication issue with the server
   */
  private static void runClient(String host, int port)
      throws IOException, IllegalStateException {
    // Initializing values to create controller
    Socket server = new Socket(host, port);
    BoardSetup bsCom = new BoardSetup();
    Board myBoard = new Board();
    Board opponentBoard = new Board();
    Board dummyBoard = new Board();

    // creating controller and starting the game
    ProxyController proxyController = new ProxyController(server, new AiComputer(bsCom, myBoard,
        opponentBoard, dummyBoard));
    proxyController.runBattleSalvo();
  }


  /**
   * Project entry point
   *
   * @param args - no command line args required
   */
  public static void main(String[] args) {
    if (args.length > 0) {
      try {
        runClient(args[0], Integer.parseInt(args[1]));
      } catch (IOException e) {
        System.out.println("Unable to connect to server");
      }
    } else {
      Display dis = new Display(System.out);
      BoardSetup bsu = new BoardSetup();
      BoardSetup bsc = new BoardSetup();
      GameController gc = new GameController(System.in, dis, bsu, bsc);
      try {
        gc.runBattleSalvo();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }
}


