package com.tvshows.datapipeline.onlineprocessors;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import models.internal.AggregatedViews;
import org.apache.commons.io.FileUtils;

public class PerDayAggregator {
  public void aggregate(String eventsFile) throws IOException {
    Map<String, AggregatedViews> aggregatedViewsMap = new HashMap<>();
    try (FileReader reader = new FileReader(eventsFile);
        BufferedReader bufferedReader = new BufferedReader(reader)) {
      String line;
      while ((line = bufferedReader.readLine()) != null) {
        String[] tokens = line.split("\t");
        // TODO: Define proper column schema instead of using indexes.
        String key = tokens[0] + "_" + tokens[1] + "_" + tokens[2];
        AggregatedViews aggregatedViews =
            aggregatedViewsMap.getOrDefault(
                key, new AggregatedViews(tokens[0], tokens[1], tokens[2], 0));
        aggregatedViews.setViewHours(
            aggregatedViews.getViewHours() + Double.parseDouble(tokens[3]));
        aggregatedViewsMap.put(key, aggregatedViews);
      }
    } catch (FileNotFoundException e) {
      // TODO: Proper error logging and exception handling
      e.printStackTrace();
    }

    File file =
        new File(
            "src/main/resources/viewEvents/aggregatedViewEvents/perDay/viewEventsAggregated.tsv");
    for (Map.Entry<String, AggregatedViews> aggregatedView : aggregatedViewsMap.entrySet()) {
      FileUtils.writeStringToFile(
          file,
          aggregatedView.getValue().getEpisodeId()
              + "\t"
              + aggregatedView.getValue().getDate()
              + "\t"
              + aggregatedView.getValue().getCountry()
              + "\t"
              + aggregatedView.getValue().getViewHours()
              + "\n",
          StandardCharsets.UTF_8,
          true);
    }
  }
}
