package studentsgroups.view;

import studentsgroups.model.Faculty;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;

/**
 * Created by molish on 16.11.2016.
 */
public interface Reader {
    void read(File file) throws IOException, ClassNotFoundException, JAXBException;
}
