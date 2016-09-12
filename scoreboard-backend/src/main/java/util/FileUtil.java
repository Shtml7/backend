package util;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;

/**
 * Created by Eric on 11-09-16.
 */
public  class FileUtil {

    private static final String path = "C:\\inetpub\\wwwroot\\images";

    public static boolean writeToFile(InputStream uploadedInputStream, String uploadedFileLocation) {
        try {
            int read = 0;
            byte[] bytes = new byte[1024];

            File file = new File(uploadedFileLocation);
            if(checkIfDirectoryExists(path)) {
                file = new File(path + "\\" + uploadedFileLocation);
            }
            OutputStream out = new FileOutputStream(file);
            while ((read = uploadedInputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
            return true;
        } catch (IOException e) {

            e.printStackTrace();
        }
        return false;
    }

    private static boolean checkIfDirectoryExists(String path) {
        File file = new File(path);
        return file.isDirectory();
    }
}
