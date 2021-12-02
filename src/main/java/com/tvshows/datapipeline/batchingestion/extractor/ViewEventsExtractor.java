package com.tvshows.datapipeline.batchingestion.extractor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ViewEventsExtractor implements Extractor {

  public List<String> extract() {

    List<String> extractedViews = new ArrayList<>();
    try (FileReader reader =
            new FileReader(
                "src/main/resources/viewEvents/aggregatedViewEvents/totalPerCountry/viewEventsAggregated.tsv");
        BufferedReader bufferedReader = new BufferedReader(reader)) {
      String view;
      while ((view = bufferedReader.readLine()) != null) {
        extractedViews.add(view);
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return extractedViews;
  }
}
