package org.ygcxy;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.ygcxy.entity.KnowCount;
import org.ygcxy.entity.SensitiveCount;
import org.ygcxy.mapper.KnowledgeMapper;
import org.ygcxy.utils.AnalyzeUtils;

import java.util.List;

/**
 * @Project: org.ygcxy
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/23 17:27
 * @Description:
 */
@SpringBootTest
public class KnowledgeContentAnalyzeApplicationTests {

    @Autowired
    private KnowledgeMapper mapper;

    @Test
    public void queryTest(){
        SensitiveCount query = mapper.query();
        System.out.println(query);
    }
    @Test
    public void queryCountByMonthTest(){
        List<KnowCount> knowCounts = mapper.queryCount(2024);
        System.out.println(knowCounts);
    }
    @Test
    public void genMonths() {
        List<String> strings = AnalyzeUtils.velocityListByYear(2024);
        System.out.println(strings);
    }
}
