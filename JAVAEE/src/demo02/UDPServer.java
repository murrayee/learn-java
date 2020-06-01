package demo02;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class UDPServer {
    public static void main(String[] args) throws IOException {
        DatagramSocket ds = new DatagramSocket(6666); // 监听指定端口
        System.out.println("server is running...");
        for (;;) {
            // 接收:
            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            ds.receive(packet);
            String cmd = new String(packet.getData(), packet.getOffset(), packet.getLength(), StandardCharsets.UTF_8);
            // 发送:
            String resp = switch (cmd) {
                case "date" -> LocalDate.now().toString();
                case "time" -> LocalTime.now().withNano(0).toString();
                case "datetime" -> LocalDateTime.now().withNano(0).toString();
                case "weather" -> "sunny, 10~15 C.";
                default -> "bad command";
            };
            System.out.println(cmd + " >>> " + resp);
            packet.setData(resp.getBytes(StandardCharsets.UTF_8));
            ds.send(packet);
        }
    }
}
