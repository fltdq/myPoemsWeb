import com.shici.pojo.User;
import com.shici.service.impl.UserServiceImpl;
import org.junit.Test;

/**
 * FileName: UserServiceTest
 * Description:
 * Author: CSH
 * Date: 2021/1/3 20:50
 * Version: 1.0
 */
public class UserServiceTest {

    UserServiceImpl userService = new UserServiceImpl();

    @Test
    public void registUser(){
        userService.registUser(new User(null,"bbj168","666666","bbj168@168.com"));
        userService.registUser(new User(null,"abc168","666666","abc168@168.com"));
    }

    @Test
    public void login(){
        System.out.println(userService.login(new User(null,"bbj168","666666","bbj168@qq.com")));

    }

    @Test
    public void existsUsername(){
        if (userService.existsUsername("bbj168")){
            System.out.println("用户名已存在！");
        }else {
            System.out.println("用户名可用！");
        }
    }
}
