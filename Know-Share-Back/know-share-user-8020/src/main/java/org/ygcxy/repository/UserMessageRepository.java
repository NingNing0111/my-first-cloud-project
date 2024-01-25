package org.ygcxy.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ygcxy.entity.UserMessage;

/**
 * @Project: org.ygcxy.repository
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/25 14:32
 * @Description:
 */
@Repository
public interface UserMessageRepository extends JpaRepository<UserMessage, Long> {
    Page<UserMessage> findAllByUserId (Pageable pageable, Long userId);
    UserMessage findUserMessageById(Long id);
}
