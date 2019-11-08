import com.qf.dao.IUserDao;
import com.qf.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MybatisTest {
    public static void main(String[] args) throws IOException {
//        第一步：读取配置文件
        InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");
//        第二步：创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(is);
//        第三步：创建SqlSession
        SqlSession sqlSession = factory.openSession();
//        第四步：创建Dao接口的代理对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
//        第五步：执行dao中的方法
        List<User> userList = userDao.findAll();
        for (User user : userList) {
            System.out.println(user);
        }
//        第六步：释放资源
        sqlSession.close();
        is.close();


    }
}
