package cs3500.pa03.controller;

import cs3500.pa03.model.Board;
import cs3500.pa03.model.BoardSetup;
import cs3500.pa03.model.Computer;
import cs3500.pa03.model.Coord;
import cs3500.pa03.model.Ship;
import cs3500.pa03.model.ShipType;
import cs3500.pa03.model.User;
import cs3500.pa03.view.Display;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

/**
 * class to represent the gamecontroller
 */
public class GameController {

  /**
   * field for inputstream to read
   */
  private final InputStream readable;

  /**
   * field to connect to view display
   */
  private Display display;

  /**
   * field for the user player
   */
  private User user;

  /**
   * field for the AI player
   */
  private Computer com;

  /**
   * field for the user boardsetup
   */
  private BoardSetup bsu;

  /**
   * field for the computer boardsetup
   */
  private BoardSetup bsc;

  /**
   * Initializes a GameController
   *
   * @param readable input
   * @param display  uses to display
   * @param bsu      setup for user
   * @param bsc      setup for computer
   */
  public GameController(InputStream readable, Display display, BoardSetup bsu, BoardSetup bsc) {
    this.readable = Objects.requireNonNull(readable);
    this.display = display;
    this.bsu = bsu;
    this.bsc = bsc;
  }

  /**
   * run method for the game
   *
   * @throws IOException exception for scanner
   */
  public void runBattleSalvo() throws IOException {
    Display display = this.display;
    Scanner scanner = new Scanner(this.readable);
    display.displayWelcome();
    ArrayList<Integer> dimList = checkDimesions(scanner, display);
    int height = dimList.get(0);
    int width = dimList.get(1);
    display.displayFleetSelection(Math.min(height, width));
    ArrayList<Integer> fleetList = checkFleet(scanner, display, height, width);
    int carNum = fleetList.get(0);
    int batNum = fleetList.get(1);
    int desNum = fleetList.get(2);
    int subNum = fleetList.get(3);
    Map<ShipType, Integer> specs = new HashMap<>();
    specs.put(ShipType.CARRIER, carNum);
    specs.put(ShipType.BATTLESHIP, batNum);
    specs.put(ShipType.DESTROYER, desNum);
    specs.put(ShipType.SUBMARINE, subNum);
    Board userBoard = new Board();
    Board dumb = new Board();
    com = new Computer(this.bsc, new Board(), userBoard, dumb);
    List<Ship> losc = com.setup(height, width, specs);
    com.myBoard.ships = losc;
    user = new User(this.bsu, userBoard, com.myBoard, dumb);
    user.myBoard.ships = user.setup(height, width, specs);
    this.bsc.makeBoard(width, height, dumb);
    display.displayOpponentBoard(user.dummyBoard.board);
    display.displayUserBoard(user.myBoard.board);

    while (user.myBoard.numOfShipsLeft() > 0 && com.myBoard.numOfShipsLeft() > 0) {
      display.askForShots(Math.min(user.myBoard.numOfShipsLeft(), user.myBoard.numEmptySpaces()));
      user.shots = checkShots(scanner, height, width);
      List<Coord> comHurtList = com.reportDamage(user.takeShots());
      List<Coord> userHurtList = user.reportDamage(com.takeShots());
      user.successfulHits(comHurtList);
      com.successfulHits(userHurtList);
      display.displayOpponentBoard(user.dummyBoard.board);
      display.displayUserBoard(user.myBoard.board);
    }
    int result = user.myBoard.numOfShipsLeft() - com.myBoard.numOfShipsLeft();
    display.displayEnd(result);
  }

  /**
   * helper to validate the dimensions
   *
   * @param s scanner
   * @param display view
   * @return list of validated dimensions
   * @throws IOException error
   */
  private ArrayList<Integer> checkDimesions(Scanner s, Display display) throws IOException {
    ArrayList<Integer> loi = new ArrayList<>();
    int height = s.nextInt();
    int width = s.nextInt();
    if (height > 15 || height < 6 || width > 15 || width < 6) {
      display.displayInvalidDim();
      return checkDimesions(s, display);
    } else {
      loi.add(height);
      loi.add(width);
      return loi;
    }
  }

  /**
   * helper to check the inputted fleet size
   *
   * @param s scanner
   * @param display view
   * @param height inputted height
   * @param width inputted width
   * @return list of validated fleet sizes
   * @throws IOException error
   */
  private ArrayList<Integer> checkFleet(Scanner s, Display display, int height, int width)
      throws IOException {
    ArrayList<Integer> loi = new ArrayList<>();
    int carNum = s.nextInt();
    int batNum = s.nextInt();
    int desNum = s.nextInt();
    int subNum = s.nextInt();
    int sumInput = carNum + batNum + desNum + subNum;
    if (carNum <= 0 || batNum <= 0 || desNum <= 0 || subNum <= 0
        || sumInput > Math.min(height, width)) {
      display.displayInvalidFleetSize();
      return checkFleet(s, display, height, width);
    } else {
      loi.add(carNum);
      loi.add(batNum);
      loi.add(desNum);
      loi.add(subNum);
      return loi;
    }
  }

  /**
   * checks if the shots are valid
   *
   * @param scanner scanner
   * @param height inputted height
   * @param width inputted width
   * @return the validated list of shots
   * @throws IOException error
   */
  private ArrayList<Coord> checkShots(Scanner scanner, int height, int width)
      throws IOException {
    ArrayList<Coord> loc = new ArrayList<>();
    boolean move = true;
    for (int i = 0; i < Math.min(user.myBoard.numOfShipsLeft(), user.myBoard.numEmptySpaces());
         i++) {
      loc.add(new Coord(scanner.nextInt(), scanner.nextInt()));
    }
    for (Coord c : loc) {
      if (!(c.x < width && c.x >= 0 && c.y < height && c.y >= 0)) {
        move = false;
      }
    }
    if (move) {
      return loc;
    } else {
      display.askForShots(Math.min(user.myBoard.numOfShipsLeft(), user.myBoard.numEmptySpaces()));
      return checkShots(scanner, height, width);
    }
  }
}
