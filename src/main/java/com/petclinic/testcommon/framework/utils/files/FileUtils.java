package com.petclinic.testcommon.framework.utils.files;

import static java.nio.file.Paths.get;
import static java.util.Objects.requireNonNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

import org.apache.commons.io.IOUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileUtils {

    private static final String DOWNLOAD_FOLDER_PATH = System.getProperty("user.dir") + File.separator + "externalFiles" + File.separator + "downloadFiles";
    private final Path folder = get(DOWNLOAD_FOLDER_PATH);

    /**
     * Encodes file to Base64 string
     *
     * @param stream
     * @return (String) encoded Base64 file
     */
    public static String encodeToBase64(InputStream stream) {
        String base64File = "";
        try {
            byte[] fileContent = IOUtils.toByteArray(stream);
            base64File = Base64.getEncoder().encodeToString(fileContent);
        } catch (FileNotFoundException e) {
            log.error("File not found in {}", stream, e);
        } catch (IOException ioe) {
            log.error("Exception while reading the file", ioe);
        }
        return base64File;
    }

    public void folderCleanUp(Path folder) throws IOException {
        if (folder.toFile().exists()) {
            Function<Path, Stream<Path>> walk = p -> {
                try {
                    return Files.walk(p);
                } catch (IOException e) {
                    log.error(e.getMessage());
                    return Stream.empty();
                }
            };
            Consumer<Path> delete = p -> {
                try {
                    Files.delete(p);
                } catch (IOException e) {
                    log.error(e.getMessage());
                }
            };
            try (Stream<Path> pathStream = Files.list(folder)) {
                pathStream
                        .flatMap(walk)
                        .sorted(Comparator.reverseOrder())
                        .forEach(delete);
            }
        } else {
            log.info("Creating the following folder: {}", folder);
            try {
                new File(folder.toString()).mkdirs();
            } catch (SecurityException se) {
                log.error("File creation was failed with: {}", se.getMessage());
            }
        }
    }

    public Path getDownloadFolderPath() {
        return this.folder;
    }

    public List<String> listFilesForFolder() {
        File folder = this.folder.toFile();
        List<String> fileNamesList = new ArrayList<>(10);
        log.info("folder: {}", folder);
        for (File fileEntry : requireNonNull(folder.listFiles(), "folder should not be null")) {
            fileNamesList.add(fileEntry.getName());
        }
        return fileNamesList;
    }

    public static Properties loadProperties(String config) {

        Properties properties = new Properties();
        try (final InputStream configInputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(config);
             final Reader reader = new InputStreamReader(requireNonNull(configInputStream), StandardCharsets.UTF_8)) {

            properties.load(reader);
        } catch (IOException e) {
            log.error("Error loading the " + config + " file", e);
        }
        return properties;
    }

    public static Properties loadPropertiesWithISO88591(String config) {

        Properties properties = new Properties();
        try (final InputStream configInputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(config);
             final Reader reader = new InputStreamReader(requireNonNull(configInputStream), StandardCharsets.ISO_8859_1)) {

            properties.load(reader);
        } catch (IOException e) {
            log.error("Error loading the " + config + " file", e);
        }
        return properties;
    }

    public void createAndWriteIntoCSV(String fileName, List<List<String>> data) {
        DateTime now = DateTime.now(DateTimeZone.getDefault());
        String dateTimeFolder = now.toString().replace(':', '-');
        String path = String.format("%s\\%s\\", this.folder, dateTimeFolder);
        try {
            new File(path).mkdirs();
        } catch (SecurityException se) {
            log.error("Folder creation was failed with: {}", se.getMessage());
        }

        try (FileWriter csvWriter = new FileWriter(path + fileName)) {
            for (List<String> rowData : data) {
                csvWriter.append(String.join(",", rowData));
                csvWriter.append("\n");
            }
        } catch (IOException ex) {
            log.error("Error writing into the " + fileName + " file", ex);
        }
    }
}
