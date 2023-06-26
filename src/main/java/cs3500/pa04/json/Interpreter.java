package cs3500.pa04.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa03.model.Coord;
import cs3500.pa03.model.GameResult;
import cs3500.pa03.model.ShipType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a tool for interpreting JSON messages
 */
public class Interpreter {
  /**
   * The message to be interpreted
   */
  private final MessageJson message;

  /**
   * The arguments of the message being interpreted
   */
  private final JsonNode arguments;
  private final ObjectMapper mapper;

  /**
   * Initializes an Interpreter with a given message to be interpreted
   */
  public Interpreter(MessageJson message) {
    this.message = message;
    this.arguments = message.arguments();
    mapper = new ObjectMapper();
  }


  /**
   * Interprets a 'setup' message and gets the desired board dimensions
   *
   * @return the dimensions of the board as a list in the order height, width
   */
  public List<Integer> interpretSetupDims() {
    int height = arguments.get("height").intValue();
    int width = arguments.get("width").intValue();
    return new ArrayList<>(List.of(height, width));
  }

  /**
   * Interprets a 'setup' message and gets the desired fleet specifications
   *
   * @return the specifications (number of different ship types) of the desired fleet
   */
  public Map<ShipType, Integer> interpretSetupSpecs() {
    JsonNode jsonSpecs = arguments.get("fleet-spec");
    Map<ShipType, Integer> specs = new HashMap<>();
    specs.put(ShipType.CARRIER, jsonSpecs.get("CARRIER").intValue());
    specs.put(ShipType.BATTLESHIP, jsonSpecs.get("BATTLESHIP").intValue());
    specs.put(ShipType.DESTROYER, jsonSpecs.get("DESTROYER").intValue());
    specs.put(ShipType.SUBMARINE, jsonSpecs.get("SUBMARINE").intValue());
    return specs;
  }

  /**
   * Interprets a message that contains a coordinate list (i.e. reportDamage and successfulHits)
   *
   * @return the list of shots that hit a player (i.e. the damage)
   */
  public List<Coord> interpretCoordList() {
    List<Coord> coords = new ArrayList<>();
    // turn each json coordinate in the json array into a Coord
    for (JsonNode jsonCoord : arguments.get("coordinates")) {
      Coord coord = mapper.convertValue(jsonCoord, Coord.class);
      coords.add(coord);
    }
    return coords;
  }


  /**
   * Interprets an 'end-game' message and returns the result
   *
   * @return the result of the end of the game
   */
  public GameResult interpretEndGameResult() {
    return GameResult.stringToResult(String.valueOf((arguments.get("result"))));
  }

  /**
   * Interprets an 'end-game' message and returns the reason
   *
   * @return the reason for the end of the game
   */
  public String interpretEndGameReason() {
    return arguments.get("reason").toString();
  }

}
