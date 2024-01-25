package org.ygcxy;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.ygcxy.entity.KnowledgeContent;
import org.ygcxy.mapper.KnowMapper;
import org.ygcxy.service.KnowService;

import java.util.List;

/**
 * @Project: org.ygcxy
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/11 23:33
 * @Description:
 */
@SpringBootTest
public class KnowledgeContentApplicationTests {
    @Autowired
    private KnowMapper mapper;
    @Autowired
    private KnowService knowService;
    @Test
    public void addKcTest(){
        mapper.insert(
                KnowledgeContent
                        .builder()
                        .kcTitle("这是文章标题")
                        .kcOverview("这是文章大纲")
                        .kcCategory(List.of("测试", "列表"))
                        .build()
        );
    }
    @Test
    public void findKcByIdTest(){
        List<KnowledgeContent> knowledgeContents = mapper.selectList(null);
        knowledgeContents.forEach(e -> {
            System.out.println(e);
        });
    }

    @Test
    public void collectByUserIdTest() {
        Page<KnowledgeContent> knowledgeContents = mapper.queryCollectPageByUserId(26L, new Page(0, 10));
        System.out.println(knowledgeContents);
    }

    @Test
    public void deleteById(){
        knowService.deleteKnow(44);
    }

}
