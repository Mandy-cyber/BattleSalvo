package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * JSON format of this record:
 * <p>
 * <code>
 * {
 *   "method-name": "the name of the method",
 *   "arguments": {"array of other json objects"}
 * }
 * </code>
 * </p>
 *
 * @param methodName the name of the method
 * @param arguments the arguments the method takes in
 */
public record MessageJson(
    @JsonProperty("method-name") String methodName,
    @JsonProperty("arguments") JsonNode arguments) {

  /**
   * Initializes a MessageJson (primarily for testing purposes)
   */
  public MessageJson(String methodName, JsonNode arguments) {
    this.methodName = methodName;
    this.arguments = arguments;
  }

}

