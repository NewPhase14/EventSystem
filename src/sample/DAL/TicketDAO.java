package sample.DAL;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO {

    public List<File> getFiles() {
        File[] directory = new File("resources/data/tickets").listFiles();
        List<File> fileList = new ArrayList<>();
        for (File file : directory) {
            fileList.add(new File(String.valueOf(file)));
        }
        return fileList;
    }

}
