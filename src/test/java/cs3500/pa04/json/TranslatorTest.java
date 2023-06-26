package cs3500.pa04.json;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa03.model.Coord;
import cs3500.pa03.model.Ship;
import cs3500.pa03.model.ShipType;
import cs3500.pa04.model.GameType;
import cs3500.pa04.model.ShipAdapter;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * class for testing the translator
 */
class TranslatorTest {

  /**
   * field for instantiating translator
   */
  private Translator translator;
  /**
   * field for instantiating object mapper
   */
  private ObjectMapper mapper;

  /**
   * Initializes a Translator and Mapper for testing
   */
  @BeforeEach
  void sendSetup() {
    this.translator = new Translator();
    this.mapper = new ObjectMapper();
  }

  /**
   * Tests that the client's join message is translated correctly
   */
  @Test
  void testClientJoin() {
    JsonNode actualNode = this.translator.clientJoin("jhippler", GameType.SINGLE);
    MessageJson actual = this.mapper.convertValue(actualNode, MessageJson.class);
    assertEquals("join", actual.methodName());
    JsonNode actualArguments = actual.arguments();
    JoinJson join = this.mapper.convertValue(actualArguments, JoinJson.class);

    assertEquals("SINGLE", join.gameType());
    assertEquals("jhippler", join.name());
  }

  /**
   * Tests that the client's setup message is translated correctly
   */
  @Test
  void testClientSetup() {

    ArrayList<Coord> b1loc = new ArrayList<Coord>();
    b1loc.add(new Coord(1, 2));
    b1loc.add(new Coord(1, 3));
    b1loc.add(new Coord(1, 4));
    b1loc.add(new Coord(1, 5));
    b1loc.add(new Coord(1, 6));

    ArrayList<Coord> b2loc = new ArrayList<Coord>();
    b2loc.add(new Coord(2, 2));
    b2loc.add(new Coord(2, 3));
    b2loc.add(new Coord(2, 4));
    b2loc.add(new Coord(2, 5));
    b2loc.add(new Coord(2, 6));

    Ship ship1 = new Ship(ShipType.BATTLESHIP, b1loc);
    Ship ship2 = new Ship(ShipType.BATTLESHIP, b2loc);

    List<Ship> shipList = new ArrayList<>();
    shipList.add(ship1);
    shipList.add(ship2);

    List<ShipAdapter> adapterList = new ArrayList<>();
    adapterList.add(new ShipAdapter(new Coord(1, 2), 5, "VERTICAL"));
    adapterList.add(new ShipAdapter(new Coord(2, 2), 5, "VERTICAL"));

    JsonNode actualNode = this.translator.clientSetup(shipList);
    MessageJson actual = this.mapper.convertValue(actualNode, MessageJson.class);
    assertEquals("setup", actual.methodName());
    JsonNode actualArguments = actual.arguments();
    FleetJson fleet = this.mapper.convertValue(actualArguments, FleetJson.class);


    assertEquals(adapterList, fleet.fleet());
  }


  /**
   * Tests that the client's take-shots message is translated correctly
   */
  @Test
  void testClientTakeShots() {
    List<Coord> shots = new ArrayList<Coord>();
    shots.add(new Coord(2, 2));
    shots.add(new Coord(2, 3));
    shots.add(new Coord(2, 4));
    shots.add(new Coord(2, 5));
    shots.add(new Coord(2, 6));


    JsonNode actualNode = this.translator.clientTakeShots(shots);
    MessageJson actual = this.mapper.convertValue(actualNode, MessageJson.class);
    assertEquals("take-shots", actual.methodName());
    JsonNode actualArguments = actual.arguments();
    VolleyJson takeShots = this.mapper.convertValue(actualArguments, VolleyJson.class);
    assertEquals(shots, takeShots.coordinates());
  }


  /**
   * Tests that the client's report-damage message is translated correctly
   */
  @Test
  void testClientReportDamage() {
    List<Coord> shots = new ArrayList<Coord>();
    shots.add(new Coord(2, 2));
    shots.add(new Coord(2, 3));
    shots.add(new Coord(2, 4));
    shots.add(new Coord(2, 5));
    shots.add(new Coord(2, 6));

    JsonNode actualNode = this.translator.clientReportDamage(shots);
    MessageJson actual = this.mapper.convertValue(actualNode, MessageJson.class);
    assertEquals("report-damage", actual.methodName());
    JsonNode actualArguments = actual.arguments();
    VolleyJson reportDamage = this.mapper.convertValue(actualArguments, VolleyJson.class);
    assertEquals(shots, reportDamage.coordinates());
  }


  /**
   * Tests that the client's successful-hits message is translated correctly
   */
  @Test
  void testClientSuccessfulHits() {
    JsonNode actualNode = this.translator.clientSuccessfulHits();
    MessageJson actual = this.mapper.convertValue(actualNode, MessageJson.class);
    assertEquals("successful-hits", actual.methodName());
    JsonNode actualArguments = actual.arguments();
    JsonNode expected = JsonUtils.serializeRecord(new MessageJson("successful-hits", null));
    assertEquals(expected, actualNode);
  }

  /**
   * Tests that the client's end-game message is translated correctly
   */
  @Test
  void testClientEndGame() {
    JsonNode actualNode = this.translator.clientEndGame();
    MessageJson actual = this.mapper.convertValue(actualNode, MessageJson.class);
    assertEquals("end-game", actual.methodName());
    JsonNode actualArguments = actual.arguments();
    JsonNode expected = JsonUtils.serializeRecord(
        new MessageJson("end-game", null));
    assertEquals(expected, actualNode);
  }
}