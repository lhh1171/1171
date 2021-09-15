package rpc;

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

        String clazzname= ois.readUTF();
        String methodName=ois.readUTF();
        Class[] parameterType=(Class[])ois.readObject();
        Object[] args=(Object[])ois.readObject();


        Class clazz=null;
        clazz=ProductServiceImpl.class;

       // System.out.println(methodName);

        Method method=clazz.getMethod(methodName,parameterType);
        Object o= method.invoke(clazz.newInstance(),args);

        ObjectOutputStream oos=new ObjectOutputStream(out);
        oos.writeObject(o);
        oos.flush();
    }

}

