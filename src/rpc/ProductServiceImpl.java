package rpc;

import com.company.IProductService;
import com.company.Product;


public class ProductServiceImpl implements IProductService {
    @Override
    public Product findUserById(Integer id) {
        return new Product(88,"BBB");
    }
}
