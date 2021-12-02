package com.tvshows.datapipeline.onlineprocessors;

import java.io.IOException;

/**
 * This class mocks a kafka consumer. Here the events from a resource file are parsed and aggregated
 * as if they are coming from kafka topic. In actual setup, this will be a consumer, using kafka
 * streams to aggregate the event data per day and dump daily view hours to graph database.
 */
public class ViewEventsKafkaConsumer {

  // TODO: Inject/add constructors instead of initializing here.
  private PerDayAggregator perDayAggregator = new PerDayAggregator();
  private PerCountryAggregator perCountryAggregator = new PerCountryAggregator();

  public void processEvents() throws IOException {
    // In actual setup events would be read from kafka topic instead of file.
    String eventsFile =
        "src/main/resources/viewEvents/rawViewEvents/viewEventsFromKafka_smaller.tsv";

    perDayAggregator.aggregate(eventsFile);
    perCountryAggregator.aggregate(eventsFile);
    // TODO: Add more aggregators if needed. Or can be done during online queries as well.
  }
}
