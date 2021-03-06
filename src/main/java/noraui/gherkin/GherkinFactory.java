package noraui.gherkin;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.apache.log4j.Logger;

import noraui.utils.Constants;
import noraui.utils.Context;

public class GherkinFactory {

    private static final Logger logger = Logger.getLogger(GherkinFactory.class);

    private static final String DATA = "#DATA";
    private static final String DATA_END = "#END";

    /**
     * Private constructor
     */
    private GherkinFactory() {
    }

    /**
     * @param filename
     *            name of input Gherkin file.
     * @param lines
     *            is a table of data (line by line and without headers).
     */
    public static void injectDataInGherkinExamples(String filename, List<String[]> lines) {
        try {
            int indexOfUnderscore = filename.lastIndexOf('_');
            String path = indexOfUnderscore != -1
                    ? Context.getResourcesPath() + Context.getScenarioProperty(filename.substring(0, indexOfUnderscore)) + filename.substring(0, indexOfUnderscore) + ".feature"
                    : Context.getResourcesPath() + Context.getScenarioProperty(filename) + filename + ".feature";
            Path file = Paths.get(path);
            String fileContent = new String(Files.readAllBytes(file), Charset.forName(Constants.DEFAULT_ENDODING));

            StringBuilder examples = new StringBuilder();
            examples.append("    ");
            for (int j = 0; j < lines.size(); j++) {
                examples.append("|");
                examples.append(j + 1);
                for (String col : lines.get(j)) {
                    examples.append("|");
                    examples.append(col);
                }
                examples.append("|\n    ");
            }

            fileContent = fileContent.replaceAll("(" + DATA + "\r?\n.*\r?\n)[\\s\\S]*(" + DATA_END + ")", "$1" + examples.toString() + "$2");

            try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), Charset.forName(Constants.DEFAULT_ENDODING)));) {
                bw.write(fileContent);
            }
        } catch (IOException e) {
            logger.error(e);
        }
    }
}
