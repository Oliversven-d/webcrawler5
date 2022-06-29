package com.udacity.webcrawler.json;

import java.io.Reader;
import java.nio.file.Path;
import java.util.Objects;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonGenerator;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A static utility class that loads a JSON configuration file.
 */
public final class ConfigurationLoader {

    private final Path path;

    /**
     * Create a {@link ConfigurationLoader} that loads configuration from the
     * given {@link Path}.
     */
    public ConfigurationLoader(Path path) {
        this.path = Objects.requireNonNull(path);
    }

    /**
     * Loads configuration from this {@link ConfigurationLoader}'s path
     *
     * @return the loaded {@link CrawlerConfiguration}.
     */
    public CrawlerConfiguration load() {
        Reader reader = null;
        try {
            // TODO: Fill in this method.
            reader = new FileReader(path.toString());
            CrawlerConfiguration crawConfig = read(reader);
            reader.close();
            return crawConfig;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    /**
     * Loads crawler configuration from the given reader.
     *
     * @param reader a Reader pointing to a JSON string that contains crawler
     * configuration.
     * @return a crawler configuration
     */
    public static CrawlerConfiguration read(Reader reader) {
        // This is here to get rid of the unused variable warning.
        try {
            Objects.requireNonNull(reader);
            // TODO: Fill in this method
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false);
            CrawlerConfiguration crawConfig = mapper.readValue(reader, CrawlerConfiguration.class);
            return crawConfig;
        } catch (Exception ex) {
            return null;
        }
    }
}
