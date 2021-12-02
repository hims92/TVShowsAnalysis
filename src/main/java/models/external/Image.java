package models.external;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"medium", "original"})
@Generated("jsonschema2pojo")
public class Image {

  @JsonProperty("medium")
  private String medium;

  @JsonProperty("original")
  private String original;

  @JsonIgnore private Map<String, Object> additionalProperties = new HashMap<String, Object>();

  @JsonProperty("medium")
  public String getMedium() {
    return medium;
  }

  @JsonProperty("medium")
  public void setMedium(String medium) {
    this.medium = medium;
  }

  @JsonProperty("original")
  public String getOriginal() {
    return original;
  }

  @JsonProperty("original")
  public void setOriginal(String original) {
    this.original = original;
  }

  @JsonAnyGetter
  public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
  }

  @JsonAnySetter
  public void setAdditionalProperty(String name, Object value) {
    this.additionalProperties.put(name, value);
  }
}
