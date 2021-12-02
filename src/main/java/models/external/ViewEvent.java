package models.external;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ViewEvent {
  private String episodeId;
  // TODO: Model this as Date type.
  private String date;
  private String country;
  private double viewHours;
  private String viewerId;
}
