package org.ygcxy.handler.type;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @Project: org.ygcxy.handler.type
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/11 23:19
 * @Description:
 */
@MappedJdbcTypes(JdbcType.VARCHAR)
@MappedTypes(List.class)
@Slf4j
public class ListToVarcharTypeHandler implements TypeHandler<List<String>> {
    @Override
    public void setParameter(PreparedStatement ps, int i, List<String> parameter, JdbcType jdbcType) throws SQLException {
        // 遍历List类型的入参，拼装为String类型，使用Statement对象插入数据库
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < parameter.size(); j++) {
            if (j == parameter.size() - 1) {
                sb.append(parameter.get(j));
            } else {
                sb.append(parameter.get(j)).append(",");
            }
        }
        ps.setString(i, sb.toString());
    }

    @Override
    public List<String> getResult(ResultSet rs, String columnName) throws SQLException {
        String result = rs.getString(columnName);
        return List.of(result.split(","));
    }

    @Override
    public List<String> getResult(ResultSet rs, int columnIndex) throws SQLException {
        String result = rs.getString(columnIndex);

        return List.of(result.split(","));
    }

    @Override
    public List<String> getResult(CallableStatement cs, int columnIndex) throws SQLException {
        String result = cs.getString(columnIndex);

        return List.of(result.split(","));
    }
}