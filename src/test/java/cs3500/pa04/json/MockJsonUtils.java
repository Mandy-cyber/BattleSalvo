package cs3500.pa04.json;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Represents a way of 'mocking' the behavior of a JsonUtils
 */
public class MockJsonUtils extends JsonUtils {

  /**
   * Always returns an exception when a record is being serialized
   */
  public static JsonNode serializeRecord(Record record) {
    throw new IllegalArgumentException("Given record cannot be serialized");
  }
}
