package com.tvshows.datapipeline.batchingestion.transformer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import models.external.Episode;
import models.internal.Triple;

public class ShowsTransformer implements Transformer {
  private ObjectMapper mapper = new ObjectMapper();

  @Override
  public List<Triple> transform(List<String> jsonData) {
    List<Episode> episodes = new ArrayList<>();
    for (String json : jsonData) {
      try {
        episodes = mapper.readValue(json, new TypeReference<List<Episode>>() {});
      } catch (JsonProcessingException e) {
        // TODO: Do proper exception handling and error reporting.
        e.printStackTrace();
      }
    }

    List<Triple> triples = new ArrayList<>();
    for (Episode e : episodes) {
      // Properties of episode.
      triples.add(
          createTriple("episode/" + e.getId(), "episode/" + "id", e.getId().toString(), false));
      triples.add(createTriple("episode/" + e.getId(), "episode/" + "url", e.getUrl(), false));
      triples.add(createTriple("episode/" + e.getId(), "episode/" + "name", e.getName(), false));
      triples.add(
          createTriple(
              "episode/" + e.getId(), "episode/" + "season", e.getSeason().toString(), false));
      triples.add(createTriple("episode/" + e.getId(), "episode/" + "type", e.getType(), false));
      triples.add(
          createTriple("episode/" + e.getId(), "episode/" + "airdate", e.getAirdate(), false));
      triples.add(
          createTriple("episode/" + e.getId(), "episode/" + "airtime", e.getAirtime(), false));
      if (e.getRuntime() != null) {
        triples.add(
            createTriple(
                "episode/" + e.getId(), "episode/" + "runtime", e.getRuntime().toString(), false));
      }

      if (e.getRating().getAverage() != null) {
        triples.add(
            createTriple(
                "episode/" + e.getId(),
                "episode/" + "avgRating",
                e.getRating().getAverage().toString(),
                false));
      }

      if (e.getSummary() != null) {
        triples.add(
            createTriple(
                "episode/" + e.getId(), "episode/" + "summary", e.getSummary().toString(), false));
      }

      // Relation between show and episode.
      triples.add(
          createTriple(
              "episode/" + e.getId(),
              "episode/" + "show",
              "show/" + e.getEmbedded().getShow().getId(),
              true));
      triples.add(
          createTriple(
              "show/" + e.getEmbedded().getShow().getId(),
              "show/" + "episode",
              "episode/" + e.getId(),
              true));

      // Properties of show
      triples.add(
          createTriple(
              "show/" + e.getEmbedded().getShow().getId(),
              "show/" + "id",
              e.getEmbedded().getShow().getId().toString(),
              false));
      triples.add(
          createTriple(
              "show/" + e.getEmbedded().getShow().getId(),
              "show/" + "url",
              e.getEmbedded().getShow().getUrl(),
              false));
      triples.add(
          createTriple(
              "show/" + e.getEmbedded().getShow().getId(),
              "show/" + "language",
              e.getEmbedded().getShow().getLanguage(),
              false));
      triples.add(
          createTriple(
              "show/" + e.getEmbedded().getShow().getId(),
              "show/" + "name",
              e.getEmbedded().getShow().getName(),
              false));
      triples.add(
          createTriple(
              "show/" + e.getEmbedded().getShow().getId(),
              "show/" + "status",
              e.getEmbedded().getShow().getStatus(),
              false));

      if (e.getEmbedded().getShow().getRuntime() != null) {
        triples.add(
            createTriple(
                "show/" + e.getEmbedded().getShow().getId(),
                "show/" + "runtime",
                e.getEmbedded().getShow().getRuntime().toString(),
                false));
      }

      triples.add(
          createTriple(
              "show/" + e.getEmbedded().getShow().getId(),
              "show/" + "officialSite",
              e.getEmbedded().getShow().getOfficialSite(),
              false));

      if (e.getEmbedded().getShow().getRating() != null
          && e.getEmbedded().getShow().getRating().getAverage() != null) {
        triples.add(
            createTriple(
                "show/" + e.getEmbedded().getShow().getId(),
                "show/" + "avgRating",
                e.getEmbedded().getShow().getRating().getAverage().toString(),
                false));
      }

      if (e.getEmbedded().getShow().getSummary() != null) {
        triples.add(
            createTriple(
                "show/" + e.getEmbedded().getShow().getId(),
                "show/" + "summary",
                e.getEmbedded().getShow().getSummary(),
                false));
      }

      if (e.getEmbedded().getShow().getWebChannel() != null
          && e.getEmbedded().getShow().getWebChannel().getName() != null) {
        triples.add(
            createTriple(
                "show/" + e.getEmbedded().getShow().getId(),
                "show/" + "channelName",
                e.getEmbedded().getShow().getWebChannel().getName(),
                false));
      }

      if (e.getEmbedded().getShow().getWebChannel() != null
          && e.getEmbedded().getShow().getWebChannel().getCountry() != null) {
        triples.add(
            createTriple(
                "show/" + e.getEmbedded().getShow().getId(),
                "show/" + "channelCountry",
                "country/" + e.getEmbedded().getShow().getWebChannel().getCountry().getCode(),
                true));
        triples.add(
            createTriple(
                "country/" + e.getEmbedded().getShow().getWebChannel().getCountry().getCode(),
                "show/" + "id",
                "show/" + e.getEmbedded().getShow().getId().toString(),
                true));

        // Properties of country
        triples.add(
            createTriple(
                "country/" + e.getEmbedded().getShow().getWebChannel().getCountry().getCode(),
                "country/" + "code",
                e.getEmbedded().getShow().getWebChannel().getCountry().getCode(),
                false));
        triples.add(
            createTriple(
                "country/" + e.getEmbedded().getShow().getWebChannel().getCountry().getCode(),
                "country/" + "name",
                e.getEmbedded().getShow().getWebChannel().getCountry().getName(),
                false));
        triples.add(
            createTriple(
                "country/" + e.getEmbedded().getShow().getWebChannel().getCountry().getCode(),
                "country/" + "timezone",
                e.getEmbedded().getShow().getWebChannel().getCountry().getTimezone(),
                false));
      }

      for (String genre : e.getEmbedded().getShow().getGenres()) {
        triples.add(
            createTriple(
                "show/" + e.getEmbedded().getShow().getId(),
                "show/" + "genre",
                "genre/" + genre,
                true));
        triples.add(
            createTriple(
                "genre/" + genre,
                "show/" + "id",
                "show/" + e.getEmbedded().getShow().getId().toString(),
                true));
        triples.add(createTriple("genre/" + genre, "genre/" + "name", genre, false));
      }
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
