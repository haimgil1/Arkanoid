import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * A LevelSpecificationReader interface.
 *
 * @author Shurgil and barisya
 */
public class LevelSpecificationReader {
    private List<LevelInformation> levelsList = new ArrayList<LevelInformation>();
    private List<String> stringsInfo = new ArrayList<String>();
/**
 * @param reader reader.
 */
    public LevelSpecificationReader(java.io.Reader reader) {
        this.levelsList = this.fromReader(reader);
    }
/**
 * @param reader reader
 * @return list of level imfo
 */
    public List<LevelInformation> fromReader(java.io.Reader reader) {

        // read lines
        BufferedReader br = (BufferedReader) reader;

        try {

            // read each line
            String line = br.readLine();
            while (line != null) {
                if (line.startsWith("START_LEVEL")) {
                    line = br.readLine();
                }

                if (line.startsWith("END_LEVEL")) {
                    LevelInfoReader levelReader = new LevelInfoReader(
                            this.stringsInfo);
                    this.levelsList.add(levelReader.getLevel());
                }
                this.stringsInfo.add(line);
                line = br.readLine();
            }

        } catch (FileNotFoundException e) {
            System.err.println("Unable to find file");
        } catch (IOException e) {
            System.err.println("Failed reading file" + ", message:"
                    + e.getMessage());
            e.printStackTrace(System.err);
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file");
            }
        }
        return levelsList;
    }
/**
 * @return list of levels.
 */
    public List<LevelInformation> getListlevel() {
        return this.levelsList;
    }
}
