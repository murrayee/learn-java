package demo07;

import java.io.File;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipStreamTest {
    public static void main(String[] args) {

        // 读取

//        try (ZipInputStream zip = new ZipInputStream(new FileInputStream(...))) {
//            ZipEntry entry = null;
//            while ((entry = zip.getNextEntry()) != null) {
//                String name = entry.getName();
//                if (!entry.isDirectory()) {
//                    int n;
//                    while ((n = zip.read()) != -1) {
//                ...
//                    }
//                }
//            }
//        }

        // 写入zip
//        try (ZipOutputStream zip = new ZipOutputStream(new FileOutputStream(...))) {
//            File[] files = ...
//            for (File file : files) {
//                zip.putNextEntry(new ZipEntry(file.getName()));
//                zip.write(getFileDataAsBytes(file));
//                zip.closeEntry();
//            }
//        }
    }
}
