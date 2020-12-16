package ru.job4j.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (true) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                    String str;
                    Boolean flag = true;
                    while (!(str = in.readLine()).isEmpty()) {
                        System.out.println(str);
                        if (str.contains("Bye")) {
                            flag = false;
                        }
                    }
                    if (flag) {
                        out.write("HTTP/1.1 200 OK\r\n\\".getBytes());
                    } else {
                        out.write("HTTP/1.1 521 Server Is Down\r\n\\".getBytes());
                        socket.close();
                        break;
                    }
                }
            }
        }
    }
}
