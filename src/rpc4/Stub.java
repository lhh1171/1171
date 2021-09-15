package rpc4;


import com.company.IUserService;
import com.company.User;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

public class Stub {
   public static IUserService getStub(){
       InvocationHandler h=new InvocationHandler() {
           @Override
           public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
               Socket s = new Socket("127.0.0.1", 8888);

               ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());

               String methodName=method.getName();
               Class[] paramentsTypes= method.getParameterTypes();
               oos.writeUTF(methodName);
               oos.writeObject(paramentsTypes);
               oos.writeObject(args);
               oos.flush();

               DataInputStream dis = new DataInputStream(s.getInputStream());
               int id = dis.readInt();
               String name = dis.readUTF();
               User user = new User(id, name);

               oos.close();
               s.close();
               return user;
           }
       };
       Object o=Proxy.newProxyInstance(IUserService.class.getClassLoader(),new Class[]{IUserService.class},h);
       System.out.println(o.getClass().getName());
       return (IUserService)o;
   }
}
