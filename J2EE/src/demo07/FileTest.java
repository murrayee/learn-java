package demo07;

import java.io.File;
import java.io.IOException;

public class FileTest {
    public static void main(String[] args) throws IOException {
        File f = new File("./fileTest.txt");
        File f1 = new File("../JAVAEE");
//        f.createNewFile();
//        f.delete();
        System.out.println(f.getPath());
        System.out.println(f.getAbsoluteFile());
        System.out.println(f.getCanonicalPath());

        System.out.println(f.isFile());
        System.out.println(f1.isDirectory());

    }
}
