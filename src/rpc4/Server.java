package rpc4;

import com.company.IUserService;
import com.company.User;
import rpc1.UserserviceImpl;

import java.io.*;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

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
        ObjectInputStream ois=new ObjectInputStream(in);
        DataOutputStream dos=new DataOutputStream(out);

        String methodName=ois.readUTF();
        Class[] parameterType=(Class[])ois.readObject();
        Object[] args=(Object[])ois.readObject();

        System.out.println(methodName);
        IUserService service=new UserserviceImpl();
        Method method=service.getClass().getMethod(methodName,parameterType);
        User user=(User)method.invoke(service,args);

        dos.writeInt(user.getId());
        dos.writeUTF(user.getName());
        dos.flush();
    }

}

