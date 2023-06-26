package cs3500.pa03.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * class to represent a coordinat
 */
public class Coord {
  /**
   * field for x position
   */
  public int x;
  /**
   * field for y position
   */
  public int y;


  /**
   * constructor to make a coord
   *
   * @param x the x position
   * @param y the y position
   */
  @JsonCreator
  public Coord(@JsonProperty("x") int x,
               @JsonProperty("y") int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * overide equals
   *
   * @param obj other object to check
   * @return whether to two objects are equal
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Coord other = (Coord) obj;
    return x == other.x && y == other.y;
  }
}
