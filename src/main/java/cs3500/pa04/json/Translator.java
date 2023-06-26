package cs3500.pa04.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa03.model.Coord;
import cs3500.pa03.model.Ship;
import cs3500.pa04.model.GameType;
import cs3500.pa04.model.ShipAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a translator
 */
public class Translator {
  private final ObjectMapper mapper;

  /**
   * Initializes a Translator with a new object mapper
   */
  public Translator() {
    mapper = new ObjectMapper();
  }

  /**
   * Creates a json fleet from a given list of ships
   *
   * @param shipList a list of ships to make into json message fleet
   * @return the ships as a fleet in json-format
   */
  private FleetJson fleetToJson(List<Ship> shipList) {
    List<ShipAdapter> adapterList = new ArrayList<ShipAdapter>();
    for (Ship ship : shipList) {
      ShipAdapter adaptedShip = new ShipAdapter(ship);
      ShipAdapter shipForJson = new ShipAdapter(adaptedShip.getCoord(),
          adaptedShip.getLength(), adaptedShip.getDirection());
      adapterList.add(shipForJson);
    }
    return new FleetJson(adapterList);
  }

  /**
   * Converts a given name and given type of game into a 'join' json message
   *
   * @param name the GitHub username of a player
   * @param type the type of game to be played (i.e. SINGLE or MULTI)
   * @return a join message in json format
   */

  public JoinJson joinToJson(String name, String type) {
    return new JoinJson(name, type);
  }

  /**
   * Converts a given list of shots into a volley in json-format
   *
   * @param shots a list of coordinates/shots
   * @return a volley in json-format
   */

  private VolleyJson shotsToJson(List<Coord> shots) {
    VolleyJson json = new VolleyJson(shots);
    return json;
  }

  /**
   * Converts a given name and given type of game into a json node
   *
   * @param name the GitHub username of a player
   * @param type the type of game to be played (i.e. SINGLE or MULTI)
   * @return join message to send to server
   */
  public JsonNode clientJoin(String name, GameType type) {
    JsonNode node = JsonUtils.serializeRecord(joinToJson(name, type.toString()));
    return JsonUtils.serializeRecord(new MessageJson("join", node));
  }

  /**
   * Converts a given list of ships into a json node
   *
   * @param shipList the ships to use in the fleet
   * @return the setup message to send to te server
   */
  public JsonNode clientSetup(List<Ship> shipList) {
    JsonNode node = JsonUtils.serializeRecord(fleetToJson(shipList));
    return JsonUtils.serializeRecord(new MessageJson("setup", node));
  }

  /**
   * Converts a given list of coordinates into a take-shots json node
   *
   * @param shots the shots to take at opponent
   * @return a json message of shots to send to the server
   */
  public JsonNode clientTakeShots(List<Coord> shots) {
    JsonNode node = JsonUtils.serializeRecord(shotsToJson(shots));
    return JsonUtils.serializeRecord(new MessageJson("take-shots", node));
  }

  /**
   * Converts a given list of coordinates into a report-damage json node
   *
   * @param shots the shots that were made against the player
   * @return a json message of shots that hit the player
   */
  public JsonNode clientReportDamage(List<Coord> shots) {
    JsonNode node = JsonUtils.serializeRecord(shotsToJson(shots));
    return JsonUtils.serializeRecord(new MessageJson("report-damage", node));
  }

  /**
   * Generates a successful-hits json node
   *
   * @return a json message representing successful hits
   */
  public JsonNode clientSuccessfulHits() {
    return JsonUtils.serializeRecord(new MessageJson("successful-hits", null));
  }

  /**
   * Generates an end-game json  node
   *
   * @return a json message representing the end of the game
   */
  public JsonNode clientEndGame() {
    return JsonUtils.serializeRecord(new MessageJson("end-game", null));
  }

}
