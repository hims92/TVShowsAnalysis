package com.tvshows.datapipeline.batchingestion.extractor;

import java.io.IOException;
import java.util.List;

public interface Extractor {
  List<String> extract() throws IOException;
}
