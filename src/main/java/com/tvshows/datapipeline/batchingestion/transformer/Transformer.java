package com.tvshows.datapipeline.batchingestion.transformer;

import java.util.List;
import models.internal.Triple;

public interface Transformer {
  List<Triple> transform(List<String> extractedData);
}
