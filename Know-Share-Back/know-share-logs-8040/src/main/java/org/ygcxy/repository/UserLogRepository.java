package org.ygcxy.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ygcxy.entity.UserLog;

/**
 * @Project: org.ygcxy.repository
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/10 16:06
 * @Description:
 */
@Repository
public interface UserLogRepository extends JpaRepository<UserLog,Long> {
    Page<UserLog> findAll(Pageable pageable);
}
