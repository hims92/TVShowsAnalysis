package com.tvshows.datapipeline.batchingestion.extractor;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.HttpStatus;

public class ShowsExtractor implements Extractor {

  private static final String tvShowsUrl = "http://api.tvmaze.com/schedule/full";

  public List<String> extract() throws IOException {
    StringWriter writer = new StringWriter();
    try (CloseableHttpClient client = HttpClients.createDefault(); ) {
      HttpGet request = new HttpGet(tvShowsUrl);
      try (CloseableHttpResponse httpResponse = client.execute(request)) {
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        if (statusCode == HttpStatus.OK.value()) {
          IOUtils.copy(
              httpResponse.getEntity().getContent(), writer, StandardCharsets.UTF_8.name());
        } else {
          throw new IOException("Get TvShows call failed with status code - " + statusCode);
        }
      }
    }
    return Arrays.asList(writer.toString());
  }
}
