package com.qf;

import com.qf.dao.IUserDao;
import com.qf.domain.QueryVo;
import com.qf.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class MybastisCRUDTest {

    private InputStream is;
    private SqlSession sqlSession;
    private IUserDao userDao;



    @Before//每个测试方法执行前,都会执行这个方法
    public void init() throws IOException {
        //        第一步：读取配置文件
         is = Resources.getResourceAsStream("SqlMapConfig.xml");
//        第二步：创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(is);
//        第三步：创建SqlSession
         sqlSession = factory.openSession();
        //        第四步：创建Dao接口的代理对象
         userDao = sqlSession.getMapper(IUserDao.class);
    }


    @After//每个测试方法执行之后,都会执行这个方法
    public void destroy() throws IOException {
        //        第六步：释放资源
        sqlSession.close();
        is.close();
    }



    @Test
    public void testFindById() throws IOException {

//        第五步：执行dao中的方法
        User user = userDao.findById(48);
        System.out.println(user);

    }

    @Test
    public void testSaveUser(){
        User user = new User();
        user.setUserName("wowo");
        user.setUserBirthday(new Date());
        user.setUserSex("男");
        user.setUserAddress("广州天河");
        userDao.saveUser(user);
        System.out.println("插入之后user:"+user);

        //提交事务
        sqlSession.commit();
    }


    /**
     * 测试更新操作
     */
    @Test
    public void testUpdate(){
        User user = new User();
        user.setUserId(52);
        user.setUserName("QWQWQWQWQW");
        user.setUserAddress("北京");
        user.setUserSex("女");
        user.setUserBirthday(new Date());

        //5.执行保存方法
        userDao.updateUser(user);
        //提交事务
        sqlSession.commit();
    }


    /**
     * 测试删除操作
     */
    @Test
    public void testDelete(){
        //5.执行删除方法
        userDao.deleteUser(48);
    }

    /**
     * 测试模糊查询操作
     */
    @Test
    public void testFindByName(){
        //List<User> userList= userDao.findByName("%老%");
        List<User> userList= userDao.findByName("老");
        for (User user : userList) {
            System.out.println(user);
        }
    }

    /**
     * 测试查询总记录条数
     */
    @Test
    public void testFindTotal(){
        //5.执行查询一个方法
        int count = userDao.findTotal();
        System.out.println(count);
    }

    /**
     * 测试使用QueryVo作为查询条件
     */
    @Test
    public void testFindByVo(){
        QueryVo vo = new QueryVo();
        User user = new User();
        user.setUserName("%王%");
        vo.setUser(user);
        //5.执行查询一个方法
        List<User> users = userDao.findUserByVo(vo);
        for(User u : users){
            System.out.println(u);
        }
    }



}
