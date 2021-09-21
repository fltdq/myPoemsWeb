import com.shici.dao.PoemDao;
import com.shici.dao.impl.PoemDaoImpl;
import com.shici.pojo.Poem;
import org.junit.Test;

import java.sql.SQLException;

/**
 * FileName: PoemDaoImplTest
 * Description:
 * Author: CSH
 * Date: 2021/1/4 16:47
 * Version: 1.0
 */
public class PoemDaoImplTest {
    private PoemDao poemDao = new PoemDaoImpl();

  /*  @Test
    public void addPoem(){
        poemDao.addPoem(new Poem(null, "游洞庭湖五首·其二", "唐", "李白",
                "南湖秋水夜无烟",
                "耐可乘流直上天",
                "且就洞庭奢月色",
                "将船买酒白云边"));
    }*/

    @Test
    public void queryPoemById(){
        System.out.println(poemDao.queryPoemById(3));
    }

    @Test
    public void queryForPageTotalCountByAuthor(){
        System.out.println(poemDao.queryForPageTotalCountByAuthor("李白"));
    }

    @Test
    public void queryForPageItemsByAuthor() throws SQLException {
        for (Poem poem : poemDao.queryForPageItemsByAuthor(0, 1,"李白")){
            System.out.println(poem);
        }
    }
}
