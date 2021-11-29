package cn.graduation.bbs.test;

import cn.graduation.bbs.GraduationApplication;
import cn.graduation.bbs.dao.PostDao;
import cn.graduation.bbs.dao.UserDao;
import cn.graduation.bbs.entity.UserEntity;
import cn.graduation.bbs.service.PostService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @desc: 测试数据库连接
 * @author: zyb
 * @since: 2020/1/10 11:18
 */
@RunWith(SpringRunner.class)
@EnableTransactionManagement
@SpringBootTest(classes = GraduationApplication.class)
public class DBConnectionTest {

    @Autowired
    UserDao userDao;

    @Autowired
    DataSource dataSource;

    @Autowired
    private PostService postService;

    @Test
    public void conTest() throws SQLException {
        System.out.println("数据源" + dataSource.getClass());
        Connection connection = dataSource.getConnection();
        System.out.println("连接" + connection);
        System.out.println("连接地址" + connection.getMetaData().getURL());
        connection.close();
    }

    @Test
    public void dbTest() {
//        List<UserEntity> zhang = userDao.queryUserList("zhang");
//        System.out.println(zhang);
    }

    @Test
    public void testTranslation(){
        postService.testTranslation();
    }
}
