package cs3500.pa04.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa03.model.Coord;
import cs3500.pa03.model.ShipType;
import cs3500.pa04.Driver;
import cs3500.pa04.json.JoinJson;
import cs3500.pa04.json.JsonUtils;
import cs3500.pa04.json.MessageJson;
import cs3500.pa04.json.SetupJson;
import cs3500.pa04.json.VolleyJson;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.org.webcompere.systemstubs.stream.SystemOut;

/**
 * Represents tests for a GameModel
 */
class GameModelTest {
  GameModel model;
  List<Coord> sampleShots;
  JsonNode shotsArgs;
  MessageJson setupMessage;
  ObjectMapper mapper;

  /**
   * Initializes a GameModel and json arguments for testing
   */
  @BeforeEach
  public void setup() {
    model = new GameModel();
    mapper = new ObjectMapper();
    sampleShots = new ArrayList<>(List.of(
        new Coord(0, 0),
        new Coord(1, 1),
        new Coord(2, 2)
    ));
    shotsArgs = JsonUtils.serializeRecord(new VolleyJson(sampleShots));

    Map<ShipType, Integer> fleet = new HashMap<>();
    fleet.put(ShipType.CARRIER, 2);
    fleet.put(ShipType.BATTLESHIP, 4);
    fleet.put(ShipType.DESTROYER, 1);
    fleet.put(ShipType.SUBMARINE, 3);
    SetupJson setup = new SetupJson(10, 10, fleet);
    setupMessage = new MessageJson("setup", JsonUtils.serializeRecord(setup));
    JsonNode setupNode = model.handleSetup(setupMessage);
    MessageJson setupMsg = mapper.convertValue(setupNode, MessageJson.class);
    assertEquals(setupMsg.methodName(), "setup");
    assertEquals(setupMsg.arguments().size(), 1);
  }

  /**
   * Tests that the correct JsonNode join message is returned
   */
  @Test
  void handleJoin() {
    assertEquals(JsonUtils.serializeRecord(new MessageJson("join",
        JsonUtils.serializeRecord(new JoinJson("jhippler",
            GameType.SINGLE.toString())))), this.model.handleJoin());
  }


  /**
   * Tests that the correct player name is returned
   */
  @Test
  void getPlayerName() {
    assertEquals(this.model.getPlayerName(), "jhippler");
  }

  /**
   * Tests that the correct game type is returned
   */
  @Test
  void getPlayerGameType() {
    assertEquals(this.model.getPlayerGameType(), GameType.SINGLE);
  }


  /**
   * Tests that the correct computer of the model is returned
   */
  @Test
  void getComputer() {
    assertNotNull(this.model.getComputer());
  }

  /**
   * Tests that the correct JsonNode setup message is returned
   */
  @Test
  void handleSetup() {
    JsonNode setupNode = model.handleSetup(setupMessage);
    MessageJson setupMsg = mapper.convertValue(setupNode, MessageJson.class);
    assertEquals(setupMsg.methodName(), "setup");
    assertEquals(setupMsg.arguments().size(), 1);
  }

  /**
   * Tests that the correct JsonNode take-shots message is returned
   */
  @Test
  void handleTakeShots() {
    model.handleSetup(setupMessage);
    List<Coord> shots = model.getComputer().takeShots();
    assertEquals(mapper.convertValue(model.handleTakeShots().get("arguments"),
            VolleyJson.class).coordinates().size(), shots.size());
  }

  /**
   * Tests that the correct JsonNode report-damage message is returned
   */
  @Test
  void handleReportDamage() {
    model.handleSetup(setupMessage);
    MessageJson reportMessage = new MessageJson("report-damage", shotsArgs);
    List<Coord> shotsThatHit = model.getComputer().reportDamage(sampleShots);
    assertEquals(model.handleReportDamage(reportMessage),
        JsonUtils.serializeRecord(new MessageJson("report-damage",
            JsonUtils.serializeRecord(new VolleyJson(new ArrayList<>())))));
  }

  /**
   * Tests that the correct JsonNode successful-hits is returned
   */
  @Test
  void handleSuccessfulHits() {
    model.handleSetup(setupMessage);
    MessageJson successHits = new MessageJson("successful-hits", shotsArgs);
    assertEquals(model.handleSuccessfulHits(successHits),
        JsonUtils.serializeRecord(new MessageJson("successful-hits", null)));
  }

  /**
   * Tests that the correct JsonNode end-game message is returned
   */
  @Test
  void endBattleSalvo() {
    MessageJson endMessage = new MessageJson("end-game", null);
    assertEquals(model.endBattleSalvo(endMessage), JsonUtils.serializeRecord(endMessage));
  }
}