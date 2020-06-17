package demo07;

import javax.imageio.IIOException;
import java.io.*;

public class OutputStreamTest {
    public static void main(String[] args) throws IIOException, FileNotFoundException {
        try (OutputStream output = new FileOutputStream("./日期和时间.md")) {
            output.write("Hello".getBytes("UTF-8")); // Hello
        } //
        catch (IOException e) {
            e.printStackTrace();
        }
        // 实现类
        byte[] data;
        try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            output.write("Hello ".getBytes("UTF-8"));
            output.write("world!".getBytes("UTF-8"));
            data = output.toByteArray();
            System.out.println(new String(data, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
