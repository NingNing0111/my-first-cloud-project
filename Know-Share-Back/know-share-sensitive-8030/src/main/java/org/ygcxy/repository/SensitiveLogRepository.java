package org.ygcxy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ygcxy.entity.SensitiveLog;

/**
 * @Project: org.ygcxy.repository
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/23 15:34
 * @Description:
 */
@Repository
public interface SensitiveLogRepository extends JpaRepository<SensitiveLog, Long> {

}
