package cn.graduation.bbs.test;

import cn.graduation.bbs.GraduationApplication;
import cn.graduation.bbs.dao.UserDao;
import cn.graduation.bbs.entity.mongo.MongoTest;
import cn.graduation.bbs.service.PostService;
import cn.graduation.bbs.service.PostTypeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

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

    @Autowired
    private PostTypeService postTypeService;

    @Autowired
    private MongoTemplate mongoTemplate;

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
    public void testTranslation() {
        postTypeService.testTranslation();
//        postService.testTranslation();
    }

    @Test
    public void testMongoDB() {
        Query query = new Query(Criteria.where("name").is("zzz"));
        MongoTest mongoTest = mongoTemplate.findOne(query, MongoTest.class);
        System.out.println(mongoTest);

        MongoTest mongoTest1 = new MongoTest();
        mongoTest1.setName("111");
        mongoTemplate.insert(mongoTest1);
        List<MongoTest> all = mongoTemplate.findAll(MongoTest.class);
        System.out.println(all);
    }

}
