package rpc;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class HessianUtil {
    public static byte[] serialize(Object o) throws IOException {
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        Hessian2Output output=new Hessian2Output(baos);
        output.writeObject(o);
        output.flush();
        byte[] bytes=baos.toByteArray();
        baos.close();
        output.close();
        return bytes;
    }

    public static Object deserialize(byte[] bytes) throws IOException {
        ByteArrayInputStream bais=new ByteArrayInputStream(bytes);
        Hessian2Input input=new Hessian2Input(bais);
        Object o=input.readObject();
        bais.close();
        input.close();
        return o;
    }

}
