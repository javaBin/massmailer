package no.java.massmailer;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 * Reads evaluation results from a comma separated file.
 * @author Tobias K Torrissen
 */
public class PersonalizedInfoReader {

    /**
     * Reads the evaluations and returns a list of evaluationresults.
     * @return a list of evaluations.
     */
    public List<PersonalizedInfo> readFile(String fileName){

        List<PersonalizedInfo> result =  new ArrayList<PersonalizedInfo>();
        try {
            FileReader file = new FileReader(fileName);
            BufferedReader InputFile = new BufferedReader(file);

            String currentRecord = InputFile.readLine();
            while (currentRecord != null) {

                final StringTokenizer st = new StringTokenizer(currentRecord, "|");
                final String speakerName = st.nextToken().trim();
                final String email = st.nextToken().trim();
                final String presentationName = st.nextToken().trim();
                final String url = st.nextToken().trim();

                Person person = new Person(speakerName, email, url, presentationName);
                Map<Integer, String> record = new HashMap<Integer, String>();
                int i=0;
                while (st.hasMoreTokens()){
                    record.put(i, st.nextToken());
                    i++;
                }
                result.add(new PersonalizedInfo(person, record));
                currentRecord = InputFile.readLine();
            }
        } catch (FileNotFoundException fnfx) {
            fnfx.printStackTrace();
            throw new RuntimeException(fnfx.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return result;
    }
}
