package com.tvshows.datapipeline.batchingestion.writer;

import java.io.IOException;
import java.util.List;
import models.internal.Triple;

public interface Writer {
  void writeTriples(List<Triple> triples, String datasetName, String datasetVersion)
      throws IOException;
}
