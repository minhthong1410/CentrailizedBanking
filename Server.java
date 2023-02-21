/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package program;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author geniu
 */
public class Server {

    private int kho = 100000000;
    private ArrayList<Socket> listClient;

    public static void main(String[] args) throws IOException {

        ServerSocket server = new ServerSocket(1209);
        Server sv = new Server();
        sv.listClient = new ArrayList<>();
        FileWriter fw = null;
        try {
            fw = new FileWriter("D:\\token.txt");
            fw.write(49);
        } finally {
            if (fw != null) {
                fw.close();
            }
        }

        while (true) {
            Socket client = server.accept();
            sv.listClient.add(client);
            DataInputStream dis = new DataInputStream(client.getInputStream());
            DataOutputStream dos = new DataOutputStream(client.getOutputStream());

            int tien = dis.readInt();
            sv.kho -= tien;
            System.out.println(sv.kho);

            sv.listClient.remove(client);
            if (sv.listClient.size() == 0) {
                try {
                    fw = new FileWriter("D:\\token.txt");
                    fw.write(49);
                } finally {
                    if (fw != null) {
                        fw.close();
                    }
                }
            }
            
            client.close();
        }

    }

}
