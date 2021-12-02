package com.tvshows.datapipeline.batchingestion.transformer;

import java.util.ArrayList;
import java.util.List;
import models.internal.Triple;

public class ViewEventsTransformer implements Transformer {
  @Override
  public List<Triple> transform(List<String> extractedData) {
    List<Triple> triples = new ArrayList<>();
    for (String viewData : extractedData) {
      String[] viewDataTokens = viewData.split("\t");
      String viewId = "view/episode_" + viewDataTokens[0] + "/country_" + viewDataTokens[1];
      triples.add(createTriple("episode/" + viewDataTokens[0], "view", viewId, true));
      triples.add(createTriple(viewId, "belongsTo", "episode/" + viewDataTokens[0], true));
      triples.add(createTriple(viewId, "country", "country/" + viewDataTokens[1], true));
      triples.add(createTriple(viewId, "viewHours", viewDataTokens[2], false));
    }

    return triples;
  }

  private Triple createTriple(
      String subject, String predicateSuffix, String object, boolean isObjectEntity) {
    String subjectNode = "https://com.tvshows/entity/" + subject;
    String predicateNode = "https://com.tvshows/p/" + predicateSuffix;
    String objectNode = isObjectEntity ? "https://com.tvshows/entity/" + object : object;
    return new Triple(subjectNode, predicateNode, objectNode);
  }
}
