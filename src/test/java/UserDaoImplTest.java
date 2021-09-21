import com.shici.dao.UserDao;
import com.shici.dao.impl.UserDaoImpl;
import com.shici.pojo.User;
import org.junit.Test;

/**
 * FileName: UserDaoImplTest
 * Description:
 * Author: CSH
 * Date: 2021/1/3 19:46
 * Version: 1.0
 */
public class UserDaoImplTest {
    UserDao userDao = new UserDaoImpl();

    @Test
    public void queryUserByUsername(){
        if (userDao.queryUserByUsername("lml123") == null){
            System.out.println("用户名可用！");
        }else {
            System.out.println("用户名已存在！");
        }
    }

    @Test
    public void queryUserByUsernameAndPassword(){
        if (userDao.queryUserByUsernameAndPassword("admin","admin") == null){
            System.out.println("用户名或密码错误，登陆失败");
        }else {
            System.out.println("查询成功");
        }
    }

    @Test
    public void savaUser(){
        System.out.println(userDao.saveUser(new User(null,"sy123","123456","sy123@qq.com")));

    }
}
