package cs3500.pa04.model;

import cs3500.pa03.model.Board;
import cs3500.pa03.model.BoardSetup;
import cs3500.pa03.model.Coord;
import cs3500.pa03.model.GameResult;
import cs3500.pa03.model.Player;
import cs3500.pa03.model.Ship;
import cs3500.pa03.model.ShipType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * class to represent a computer player
 */
public class AiComputer implements Player {

  /**
   * field to hold my board
   */
  public Board myBoard;
  /**
   * field to hold the opppnents board
   */
  Board opponentBoard;
  /**
   * field for setting up board
   */
  BoardSetup bs;

  /**
   * field to hold the list of shots
   */
  List<Coord> shots;

  /**
   * list of shots taken so far
   */
  Set<Coord> shotsTaken;

  /**
   * field for the dummy board
   */
  public Board dummyBoard;

  /**
   * constructor to make a computer
   *
   * @param bs            board setup
   * @param board         my board
   * @param opponentBoard opponents board
   * @param dummyBoard    dummy board
   */
  public AiComputer(BoardSetup bs, Board board, Board opponentBoard, Board dummyBoard) {
    this.bs = bs;
    this.myBoard = board;
    this.opponentBoard = opponentBoard;
    this.dummyBoard = dummyBoard;
  }

  /**
   * Initializes the computer's shots
   */
  private void initShots() {
    ArrayList<Coord> coords = new ArrayList<Coord>();
    for (int i = 0; i < this.myBoard.board.length; i++) {
      for (int j = 0; j < this.myBoard.board[0].length; j++) {
        coords.add(new Coord(j, i));
      }
    }
    Collections.shuffle(coords);
    this.shots = coords;
    this.shotsTaken = new HashSet<Coord>();
  }

  /**
   * Get the player's name.
   *
   * @return the player's name
   */
  @Override
  public String name() {
    return null;
  }

  /**
   * Given the specifications for a BattleSalvo board, return a list of ships with their locations
   * on the board.
   *
   * @param height         the height of the board, range: [6, 15] inclusive
   * @param width          the width of the board, range: [6, 15] inclusive
   * @param specifications a map of ship type to the number of occurrences each ship should
   *                       appear on the board
   * @return the placements of each ship on the board
   */
  @Override
  public List<Ship> setup(int height, int width, Map<ShipType, Integer> specifications) {
    // setup boards
    this.bs.makeBoard(width, height, this.myBoard);
    new BoardSetup().makeBoard(width, height, this.dummyBoard);
    new BoardSetup().makeBoard(width, height, this.opponentBoard);
    // setup list of ships and list of shots
    initShots();
    List<Ship> myShips = this.bs.addShips(this.myBoard, specifications);
    this.myBoard.ships = myShips;
    System.out.println(this.myBoard.ships);
    return myShips;
  }

  /**
   * Returns this player's shots on the opponent's board. The number of shots returned should
   * equal the number of ships on this player's board that have not sunk.
   *
   * @return the locations of shots on the opponent's board
   */
  @Override
  public List<Coord> takeShots() {
    List<Coord> comShots = new ArrayList<>();
    int numShotsToTake = this.myBoard.numOfShipsLeft();
    System.out.println("How many shots to take: " + numShotsToTake);

    System.out.println("Shots taking: " + comShots);
    //int count = 0;
    for (int i = 0; i < numShotsToTake; i++) {
      if (this.shots.size() > 0) {
        Coord shot = this.shots.remove(0);
        comShots.add(shot);
      }
    }
    System.out.println("Shots taking: " + comShots);
    return comShots;
  }

  /**
   * Given the list of shots the opponent has fired on this player's board, report which
   * shots hit a ship on this player's board.
   *
   * @param opponentShotsOnBoard the opponent's shots on this player's board
   * @return a filtered list of the given shots that contain all locations of shots that hit a
   *     ship on this board
   */
  @Override
  public List<Coord> reportDamage(List<Coord> opponentShotsOnBoard) {
    ArrayList<Coord> listOfHurt = new ArrayList<>();
    for (Coord c : opponentShotsOnBoard) {
      if (this.myBoard.checkHit(c)) {
        listOfHurt.add(c);
        this.myBoard.updateBoard(c, 'H');
      } else if (this.myBoard.board[c.y][c.x] == '0') {
        this.myBoard.updateBoard(c, 'M');
        this.dummyBoard.updateBoard(c, 'M');
      }
    }
    return listOfHurt;
  }

  /**
   * Reports to this player what shots in their previous volley returned from takeShots()
   * successfully hit an opponent's ship.
   *
   * @param shotsThatHitOpponentShips the list of shots that successfully hit the opponent's ships
   */
  @Override
  public void successfulHits(List<Coord> shotsThatHitOpponentShips) {
    for (Coord c : shotsThatHitOpponentShips) {
      if (this.opponentBoard.checkHit(c)) {
        this.opponentBoard.updateBoard(c, 'H');
      }
    }
  }


  /**
   * Notifies the player that the game is over.
   * Win, lose, and draw should all be supported
   *
   * @param result if the player has won, lost, or forced a draw
   * @param reason the reason for the game ending
   */
  @Override
  public void endGame(GameResult result, String reason) {
  }
}
