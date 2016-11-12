package studentsgroups.view;

import java.io.File;
import java.io.IOException;

/**
 * Created by molish on 12.11.2016.
 */
public interface Writer {
    boolean write(File file) throws IOException;
}
