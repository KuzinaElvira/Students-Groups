package studentsgroups.view;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by molish on 16.11.2016.
 */
public interface Writer {
    void write(File file) throws IOException, JAXBException, FileNotFoundException;
}
