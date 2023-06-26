package cs3500.pa03.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * class to represent a computer player
 */
public class Computer implements Player {

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
   * field for the dummy board
   */
  public Board dummyBoard;

  /**
   * constructor to make a computer
   *
   * @param bs            boardsetup
   * @param board         my board
   * @param opponentBoard oppoinenets board
   * @param dummyBoard    dummmy bioard
   */
  public Computer(BoardSetup bs, Board board, Board opponentBoard, Board dummyBoard) {
    this.bs = bs;
    this.myBoard = board;
    this.opponentBoard = opponentBoard;
    this.dummyBoard = dummyBoard;
  }

  /**
   * helper method to initialize shots
   */
  private void initShots() {
    this.shots = new ArrayList<Coord>();
    for (int i = 0; i < this.myBoard.board.length; i++) {
      for (int j = 0; j < this.myBoard.board[0].length; j++) {
        this.shots.add(new Coord(j, i));
      }
    }
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
    this.bs.makeBoard(width, height, this.myBoard);
    initShots();
    return this.bs.addShips(this.myBoard, specifications);
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
    for (int i = 0; i < Math.min(this.myBoard.numOfShipsLeft(),
        this.myBoard.numEmptySpaces()); i++) {
      comShots.add(this.shots.remove(0));
    }
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