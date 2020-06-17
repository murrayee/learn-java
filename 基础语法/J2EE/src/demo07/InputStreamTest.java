package demo07;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class InputStreamTest {
    public static void main(String[] args) throws IOException {

        try (InputStream input = new FileInputStream("./日期和时间.md")) {
            byte[] buffer = new byte[1000];
            int n;
            while ((n = input.read(buffer)) != -1) {
                System.out.println("read " + n + " bytes.");
            }
        } // 编译器在此自动为我们写入finally并调用close()

        // 实现类

        byte[] data = {72, 101, 108, 108, 111, 33};
        try (InputStream input = new ByteArrayInputStream(data)) {
            int n;
            while ((n = input.read()) != -1) {
                System.out.println((char) n);
            }
            String s = readFileString(input);
            System.out.println(s);
        }


        try (InputStream input = new FileInputStream("./日期和时间.md")) {
            String s = readFileString(input);
            System.out.println(s);
        }

    }

    public static String readFileString(InputStream input) throws IOException {
        int n;
        StringBuilder sb = new StringBuilder();
        while ((n = input.read()) != -1) {
            sb.append((char) n);
        }
        return sb.toString();
    }
}
