package StudentsGroups.View;

import java.io.File;
import java.io.IOException;

/**
 * Created by molish on 12.11.2016.
 */
public interface Reader {
    boolean read(File file)throws IOException;
}
