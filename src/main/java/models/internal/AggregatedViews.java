package models.internal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AggregatedViews {
  private String episodeId;
  // TODO: Model this as Date type.
  private String date;
  private String country;
  private double viewHours;
}
