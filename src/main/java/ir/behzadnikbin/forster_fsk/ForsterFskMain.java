package ir.behzadnikbin.forster_fsk;

import ir.behzadnikbin.forster_fsk.dto.FskDto;
import ir.behzadnikbin.forster_fsk.logic.FskDataProcessor;
import ir.behzadnikbin.forster_fsk.utils.SerializationUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * The main file for processing FSK data
 *
 * @author Behzad
 */
public class ForsterFskMain {

    private static final Logger logger = Logger.getLogger(ForsterFskMain.class.getName());

    /**
     * @param args input arguments of program(optional): args[0]: input file path, args[1]: output file path
     */
    public static void main(String[] args) {
        //  input and output file config
        String INPUT_FILE_PATH = "assets.json";
        String OUTPUT_FILE_PATH = "output.json";
        if (args.length == 2) {
            INPUT_FILE_PATH = args[0];
            OUTPUT_FILE_PATH = args[1];
        }

        List<FskDto> list = null;
        //  opening file
        try (FileInputStream inStream = new FileInputStream(INPUT_FILE_PATH)) {
            //  deserializing json from stream to FskDto list
            list = SerializationUtils.instance.deserializeFromFile(inStream);
        } catch (FileNotFoundException e) {
            logger.severe("Input file not found at " + INPUT_FILE_PATH);
        } catch (IOException e) {
            logger.severe("Deserialization problem for " + INPUT_FILE_PATH);
        }
        if (list != null) {
            logger.info("File " + INPUT_FILE_PATH + " opened successfully.");
            //  grouping data
            SortedMap<String, List<FskDto>> map = FskDataProcessor.instance.getFskGroupByLevelFacet(list);
            Set<FskDto> fskSet = map.values().stream().flatMap(Collection::stream).collect(Collectors.toSet());
            logger.info(fskSet.size() + " data items is grouped to " + map.keySet().size());
            //  serializing to file
            try (FileOutputStream outStream = new FileOutputStream(OUTPUT_FILE_PATH)) {
                SerializationUtils.instance.serializeToStream(map, outStream);
            } catch (FileNotFoundException e) {
                logger.severe("Cannot write output file at " + OUTPUT_FILE_PATH);
            } catch (IOException e) {
                logger.severe("Cannot serialize to json file at " + OUTPUT_FILE_PATH);
            }
        }
    }
}
