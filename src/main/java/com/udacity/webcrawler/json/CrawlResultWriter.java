package com.udacity.webcrawler.json;

import java.io.Writer;
import java.nio.file.Path;
import java.util.Objects;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonGenerator;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;  
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Utility class to write a {@link CrawlResult} to file.
 */
public final class CrawlResultWriter {
  private final CrawlResult result;

  /**
   * Creates a new {@link CrawlResultWriter} that will write the given {@link CrawlResult}.
   */
  public CrawlResultWriter(CrawlResult result) {
    this.result = Objects.requireNonNull(result);
  }

  /**
   * Formats the {@link CrawlResult} as JSON and writes it to the given {@link Path}.
   *
   * <p>If a file already exists at the path, the existing file should not be deleted; new data
   * should be appended to it.
   *
   * @param path the file path where the crawl result data should be written.
   */
  public void write(Path path) {
      try {
          // This is here to get rid of the unused variable warning.
          Objects.requireNonNull(path);
          // TODO: Fill in this method.
          Writer writer = new FileWriter(path.toString());
          write(writer);
          writer.close();
      } catch (Exception ex) {
          System.out.println(ex.getMessage());
      }
  }

  /**
   * Formats the {@link CrawlResult} as JSON and writes it to the given {@link Writer}.
   *
   * @param writer the destination where the crawl result data should be written.
   */
  public void write(Writer writer) {
      try {
          // This is here to get rid of the unused variable warning.
          Objects.requireNonNull(writer);
          // TODO: Fill in this method.
          String jsonString = "{ \"wordCounts\":{";
          for (Map.Entry<String,Integer> entry : result.getWordCounts().entrySet()){
              jsonString += "\""+entry.getKey()+"\":"+entry.getValue()+",";
          }
          jsonString += "},";
          jsonString += "\"urlsVisted\":"+ result.getUrlsVisited()+"}";
          ObjectMapper mapper = new ObjectMapper();
          mapper.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false);
          CrawlResult.Builder obj = mapper.readValue(jsonString,CrawlResult.Builder.class);
          String mappedObj = mapper.writeValueAsString(obj);
          writer.write(mappedObj);
      } catch (Exception ex) {
          System.out.println(ex.getMessage());
      }
  }
}
