package com.tvshows.datapipeline;

import com.tvshows.datapipeline.batchingestion.extractor.Extractor;
import com.tvshows.datapipeline.batchingestion.extractor.ShowsExtractor;
import com.tvshows.datapipeline.batchingestion.extractor.ViewEventsExtractor;
import com.tvshows.datapipeline.batchingestion.transformer.ShowsTransformer;
import com.tvshows.datapipeline.batchingestion.transformer.Transformer;
import com.tvshows.datapipeline.batchingestion.transformer.ViewEventsTransformer;
import com.tvshows.datapipeline.batchingestion.writer.TripleFileWriter;
import com.tvshows.datapipeline.batchingestion.writer.Writer;
import com.tvshows.datapipeline.onlineprocessors.ViewEventsKafkaConsumer;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import models.internal.Triple;

public class TvShowsPipeLine {
  public static void main(String[] args) throws IOException {
    String pipelineRunVersion =
        DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss").format(LocalDateTime.now());

    Extractor showsExtractor = new ShowsExtractor();
    List<String> extractedShows = showsExtractor.extract();

    Transformer showsTransformer = new ShowsTransformer();
    List<Triple> showsGraphTriples = showsTransformer.transform(extractedShows);

    Writer fileWriter = new TripleFileWriter();
    fileWriter.writeTriples(showsGraphTriples, "Shows", pipelineRunVersion);

    ViewEventsKafkaConsumer kafkaConsumer = new ViewEventsKafkaConsumer();
    kafkaConsumer.processEvents();

    Extractor viewsExtractor = new ViewEventsExtractor();
    List<String> extractedViews = viewsExtractor.extract();

    Transformer viewsTransformer = new ViewEventsTransformer();
    List<Triple> viewGraphTriples = viewsTransformer.transform(extractedViews);

    fileWriter.writeTriples(viewGraphTriples, "Views", pipelineRunVersion);

    viewGraphTriples.addAll(showsGraphTriples);
    fileWriter.writeTriples(viewGraphTriples, "MergedGraph", pipelineRunVersion);
  }
}
