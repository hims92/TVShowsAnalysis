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
@JsonPropertyOrder({"self", "previousepisode", "nextepisode"})
@Generated("jsonschema2pojo")
public class Links__1 {

  @JsonProperty("self")
  private Self__1 self;

  @JsonProperty("previousepisode")
  private Previousepisode previousepisode;

  @JsonProperty("nextepisode")
  private Nextepisode nextepisode;

  @JsonIgnore private Map<String, Object> additionalProperties = new HashMap<String, Object>();

  @JsonProperty("self")
  public Self__1 getSelf() {
    return self;
  }

  @JsonProperty("self")
  public void setSelf(Self__1 self) {
    this.self = self;
  }

  @JsonProperty("previousepisode")
  public Previousepisode getPreviousepisode() {
    return previousepisode;
  }

  @JsonProperty("previousepisode")
  public void setPreviousepisode(Previousepisode previousepisode) {
    this.previousepisode = previousepisode;
  }

  @JsonProperty("nextepisode")
  public Nextepisode getNextepisode() {
    return nextepisode;
  }

  @JsonProperty("nextepisode")
  public void setNextepisode(Nextepisode nextepisode) {
    this.nextepisode = nextepisode;
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
