package rpc4;

import com.company.IUserService;

public class Client {
    public static void main(String[] args) {
        IUserService service=Stub.getStub();
        System.out.println(service.findProductById(123));
    }
}
