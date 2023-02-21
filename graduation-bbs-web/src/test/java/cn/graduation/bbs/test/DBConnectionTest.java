package cn.graduation.bbs.test;

import cn.graduation.bbs.GraduationApplication;
import cn.graduation.bbs.common.WebResponse;
import cn.graduation.bbs.dao.UserDao;
//import cn.graduation.bbs.dao.es.ElasticsearchTestDao;
import cn.graduation.bbs.dto.user.UserDTO;
import cn.graduation.bbs.entity.UserEntity;
//import cn.graduation.bbs.entity.es.ElasticsearchTest;
import cn.graduation.bbs.entity.mongo.MongoTest;
import cn.graduation.bbs.service.PostService;
import cn.graduation.bbs.service.PostTypeService;
import cn.graduation.bbs.utils.EsUtil;
import cn.graduation.bbs.vo.post.PostTypeFilter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

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
    private UserDao userDao;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private PostService postService;

    @Autowired
    private PostTypeService postTypeService;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private TransactionTemplate transactionTemplate;

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
        System.out.println("ssssssssssss");
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("zxczxczxvz");
        List<UserEntity> zhang = userDao.queryUserList(userDTO);
        System.out.println(zhang);
    }

    @Test
    public void testTranslation() {
        for (int i = 0; i < 10; i++) {
            transactionTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
            transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
            transactionTemplate.execute(transactionStatus -> {
                postTypeService.testTranslation();
                return null;
            });
        }

//        postTypeService.testTranslation();
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

    @Test
    public void testTransaction() throws InterruptedException {
        PostTypeFilter postTypeFilter = new PostTypeFilter();
        postTypeFilter.setPostType("asfdas");
        WebResponse save = postTypeService.save(postTypeFilter);
        System.out.println(save);
        CompletableFuture.runAsync(() -> {
            postTypeFilter.setId(111);
            WebResponse webResponse = postTypeService.queryPostTypeById(postTypeFilter);
            System.out.println(webResponse);
        });

        Thread.sleep(1000*5);
        postTypeFilter.setId(11);
        WebResponse webResponse = postTypeService.queryPostTypeById(postTypeFilter);
        System.out.println(webResponse);
    }

    @Autowired
    private EsUtil esUtil;
//    @Autowired
//    private ElasticsearchTestDao elasticsearchTestDao;

    @Test
    public void testSave() throws Exception {
//        ElasticsearchTest elasticsearchTest = new ElasticsearchTest();
//        elasticsearchTest.setTitle("测试");
//        elasticsearchTestDao.save(elasticsearchTest);

//        esUtil.createIndex("mrgtest");
        esUtil.deleteIndex("mrgtest");
    }
}
