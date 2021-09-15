package rpc;

import com.company.IProductService;
import com.company.IUserService;

public class Client {
    public static void main(String[] args) {
        IProductService service= (IProductService)Stub.getStub(IProductService.class);
        System.out.println(service.findUserById(123));
    }
}
