package cs3500.pa04.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa03.model.Coord;
import cs3500.pa03.model.Ship;

/**
 * Represents a tool for adapting an existing Ship into a JSON-readable format
 */
public class ShipAdapter {
  private Coord coord;

  private int length;

  private String dir;

  /**
   * Initializes a ShipAdapter using the given ship
   *
   * @param ship the ship to convert into JSON
   */
  public ShipAdapter(Ship ship) {
    this.coord = ship.location.get(0);
    this.length = ship.kind.size;
    this.dir = directionHelp(ship);
  }

  /**
   * Initializes a Ship in JSON format
   *
   * @param coord  the starting coordinate of the ship
   * @param length the length/size of the ship
   * @param direction    the direction the ship is in (vertical or horizontal)
   */
  @JsonCreator
  public ShipAdapter(
      @JsonProperty("coord") Coord coord,
      @JsonProperty("length") int length,
      @JsonProperty("direction") String direction) {
    this.coord = coord;
    this.length = length;
    this.dir = direction;
  }

  /**
   * A helper to find the direction of the ship (i.e. horizontal or vertical)
   *
   * @param ship the ship to be converted into json
   * @return the direction the ship is going in
   * @throws IllegalArgumentException if a ship's direction is invalid (e.g. diagonal)
   */
  private String directionHelp(Ship ship) {
    if (ship.location.get(0).x - ship.location.get(1).x != 0) {
      return "HORIZONTAL";
    } else {
      return "VERTICAL";
    }
  }

  /**
   * Gets the starting coordinate of a ship
   *
   * @return starting coordinate
   */
  public Coord getCoord() {
    return this.coord;
  }

  /**
   * Gets the length/size of the ship
   *
   * @return the length of the ship
   */
  public int getLength() {
    return this.length;
  }

  /**
   * Gets the direction the ship is in
   *
   * @return the direction of the ship
   */
  public String getDirection() {
    return this.dir;
  }

  /**
   * override equal
   *
   * @param q2 the other ShipAdapter
   * @return whether the ShipAdapters are equal
   */
  @Override
  public boolean equals(Object q2) {
    if (q2 instanceof ShipAdapter) {
      ShipAdapter other = (ShipAdapter) q2;
      return (this.coord.equals(other.coord)
          && this.length == (other.length)
          && this.dir.equals(other.dir));
    }
    return false;
  }
}
