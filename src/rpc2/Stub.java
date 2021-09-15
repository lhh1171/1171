package rpc2;


import com.company.IUserService;
import com.company.User;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

public class Stub {


    public User findUserById(int i) throws IOException {
       Socket s=new Socket("127.0.0.1",8888);
       ByteArrayOutputStream baos=new ByteArrayOutputStream();
        DataOutputStream dos=new DataOutputStream(baos);
        dos.writeInt(123);

        s.getOutputStream().write(baos.toByteArray());
        s.getOutputStream().flush();

        DataInputStream dis=new DataInputStream(s.getInputStream());
        int id=dis.readInt();
        String name= dis.readUTF();
        User user=new User(id,name);

        dos.close();
        s.close();
        return user;
    }



    }
