package com.epam.runner.file;

import java.io.*;
import java.net.Socket;

public class HttpConnect extends Thread {

        private Socket sock;
        HttpConnect(Socket s) {
            sock = s;
        }

        public void run(){
            try{
                BufferedReader b = new BufferedReader(new InputStreamReader(sock.getInputStream()));
                String req = b.readLine();
                String request=req.substring(5,req.length()-9);
                System.out.print(sock.getInetAddress()+" "+request+" ");
                BufferedInputStream in=new BufferedInputStream (new FileInputStream ("HTTP/"+request));
                BufferedOutputStream out=new BufferedOutputStream(sock.getOutputStream());
                int ch=in.read();
                while(ch!=-1){
                    out.write(ch);
                    ch=in.read();
                }
                out.flush();
                out.close();
                in.close();
                sock.close();
            }
            catch(IOException ioe){
                System.out.println("Unable to get access");
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
}
