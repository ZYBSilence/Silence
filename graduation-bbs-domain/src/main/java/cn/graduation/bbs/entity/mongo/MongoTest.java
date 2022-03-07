package cn.graduation.bbs.entity.mongo;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @description:
 * @author: zyb
 * @date: 2022/3/7 13:10
 */
@Data
@Document(collection = "test")
public class MongoTest implements Serializable {
    private static final long serialVersionUID = 1542388868516396230L;
    private String id;
    private String name;
}
