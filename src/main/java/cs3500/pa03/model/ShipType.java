package cs3500.pa03.model;

/**
 * enumeration of ship types
 */
public enum ShipType {

  /**
   * assign sizes
   */
  CARRIER(6), BATTLESHIP(5), DESTROYER(4), SUBMARINE(3);

  /**
   * field for size
   */
  public final int size;

  /**
   * constructor
   *
   * @param size size as num
   */
  ShipType(int size) {
    this.size = size;
  }

}
