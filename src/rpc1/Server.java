package rpc1;

import com.company.IUserService;
import com.company.User;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author lqc
 */
public class Server {
    public static boolean running=true;

    public static void main(String[] args) throws Exception {
        ServerSocket ss=new ServerSocket(8888);
        while (running) {
            Socket s = ss.accept();
            process(s);
            s.close();
        }
        ss.close();
    }
    private static void process(Socket s)throws Exception{
        InputStream in=s.getInputStream();
        OutputStream out=s.getOutputStream();
        DataInputStream dis=new DataInputStream(in);
        DataOutputStream dos=new DataOutputStream(out);
        int id=dis.readInt();
        System.out.println(id);
        IUserService service=new UserserviceImpl();
        User user=service.findProductById(id);
        dos.writeInt(user.getId());
        dos.writeUTF(user.getName());
        dos.flush();
    }

}
