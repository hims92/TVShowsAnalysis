package com.tvshows.datapipeline.batchingestion.writer;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import models.internal.Triple;
import org.apache.commons.io.FileUtils;

public class TripleFileWriter implements Writer {
  // TODO: Inject this properly instead of hardcoding.
  private String rootFolder = "src/main/resources/generatedGraph/";

  @Override
  public void writeTriples(List<Triple> triples, String datasetName, String datasetVersion)
      throws IOException {

    Set<Triple> uniqueTriples = triples.stream().collect(Collectors.toSet());

    List<Triple> sortedTriples =
        uniqueTriples.stream()
            .sorted(
                Comparator.comparing(Triple::getSubject)
                    .thenComparing(Triple::getPredicate)
                    .thenComparing(Triple::getObject))
            .collect(Collectors.toList());

    File file =
        new File(
            rootFolder
                .concat(datasetName)
                .concat("/")
                .concat(datasetVersion)
                .concat("/")
                .concat("triplesGraph.tsv"));

    FileUtils.writeStringToFile(
        file,
        "subject" + "\t" + "predicate" + "\t" + "object" + "\n",
        StandardCharsets.UTF_8,
        true);

    for (Triple t : sortedTriples) {
      FileUtils.writeStringToFile(
          file,
          t.getSubject() + "\t" + t.getPredicate() + "\t" + t.getObject() + "\n",
          StandardCharsets.UTF_8,
          true);
    }
  }
}
