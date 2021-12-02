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
@JsonPropertyOrder({
  "id",
  "url",
  "name",
  "season",
  "number",
  "type",
  "airdate",
  "airtime",
  "airstamp",
  "runtime",
  "rating",
  "image",
  "summary",
  "_links",
  "_embedded"
})
@Generated("jsonschema2pojo")
public class Episode {

  @JsonProperty("id")
  private Integer id;

  @JsonProperty("url")
  private String url;

  @JsonProperty("name")
  private String name;

  @JsonProperty("season")
  private Integer season;

  @JsonProperty("number")
  private Integer number;

  @JsonProperty("type")
  private String type;

  @JsonProperty("airdate")
  private String airdate;

  @JsonProperty("airtime")
  private String airtime;

  @JsonProperty("airstamp")
  private String airstamp;

  @JsonProperty("runtime")
  private Integer runtime;

  @JsonProperty("rating")
  private Rating rating;

  @JsonProperty("image")
  private Object image;

  @JsonProperty("summary")
  private Object summary;

  @JsonProperty("_links")
  private Links links;

  @JsonProperty("_embedded")
  private Embedded embedded;

  @JsonIgnore private Map<String, Object> additionalProperties = new HashMap<String, Object>();

  @JsonProperty("id")
  public Integer getId() {
    return id;
  }

  @JsonProperty("id")
  public void setId(Integer id) {
    this.id = id;
  }

  @JsonProperty("url")
  public String getUrl() {
    return url;
  }

  @JsonProperty("url")
  public void setUrl(String url) {
    this.url = url;
  }

  @JsonProperty("name")
  public String getName() {
    return name;
  }

  @JsonProperty("name")
  public void setName(String name) {
    this.name = name;
  }

  @JsonProperty("season")
  public Integer getSeason() {
    return season;
  }

  @JsonProperty("season")
  public void setSeason(Integer season) {
    this.season = season;
  }

  @JsonProperty("number")
  public Integer getNumber() {
    return number;
  }

  @JsonProperty("number")
  public void setNumber(Integer number) {
    this.number = number;
  }

  @JsonProperty("type")
  public String getType() {
    return type;
  }

  @JsonProperty("type")
  public void setType(String type) {
    this.type = type;
  }

  @JsonProperty("airdate")
  public String getAirdate() {
    return airdate;
  }

  @JsonProperty("airdate")
  public void setAirdate(String airdate) {
    this.airdate = airdate;
  }

  @JsonProperty("airtime")
  public String getAirtime() {
    return airtime;
  }

  @JsonProperty("airtime")
  public void setAirtime(String airtime) {
    this.airtime = airtime;
  }

  @JsonProperty("airstamp")
  public String getAirstamp() {
    return airstamp;
  }

  @JsonProperty("airstamp")
  public void setAirstamp(String airstamp) {
    this.airstamp = airstamp;
  }

  @JsonProperty("runtime")
  public Integer getRuntime() {
    return runtime;
  }

  @JsonProperty("runtime")
  public void setRuntime(Integer runtime) {
    this.runtime = runtime;
  }

  @JsonProperty("rating")
  public Rating getRating() {
    return rating;
  }

  @JsonProperty("rating")
  public void setRating(Rating rating) {
    this.rating = rating;
  }

  @JsonProperty("image")
  public Object getImage() {
    return image;
  }

  @JsonProperty("image")
  public void setImage(Object image) {
    this.image = image;
  }

  @JsonProperty("summary")
  public Object getSummary() {
    return summary;
  }

  @JsonProperty("summary")
  public void setSummary(Object summary) {
    this.summary = summary;
  }

  @JsonProperty("_links")
  public Links getLinks() {
    return links;
  }

  @JsonProperty("_links")
  public void setLinks(Links links) {
    this.links = links;
  }

  @JsonProperty("_embedded")
  public Embedded getEmbedded() {
    return embedded;
  }

  @JsonProperty("_embedded")
  public void setEmbedded(Embedded embedded) {
    this.embedded = embedded;
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
