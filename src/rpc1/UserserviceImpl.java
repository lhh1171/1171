package rpc1;

import com.company.IUserService;
import com.company.User;

/**
 * @author lqc
 */
public class UserserviceImpl implements IUserService {
    @Override
    public User findProductById(Integer id) {
        return new User(id,"AAAA");
    }
//    @Override
//    public User findUserById(Integer id){
//        return new User(id,"AAAA");
//    }

}
