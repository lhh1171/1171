package rpc4;

import com.company.IUserService;
import com.company.User;

class UserServiceImpl implements IUserService {
    @Override
    public User findProductById(Integer id) {
        return new User(id,"AAAA");
    }
}
