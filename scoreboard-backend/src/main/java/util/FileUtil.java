package util;

import java.io.*;

/**
 * Created by Eric on 11-09-16.
 */
public  class FileUtil {
    public static boolean writeToFile(InputStream uploadedInputStream, String uploadedFileLocation) {
        try {
            int read = 0;
            byte[] bytes = new byte[1024];

            OutputStream out = new FileOutputStream(new File(uploadedFileLocation));
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
}
