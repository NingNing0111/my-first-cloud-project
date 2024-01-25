package org.ygcxy.repository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.ygcxy.entity.User;


/**
 * @Project: org.ygcxy.repository
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/9 10:10
 * @Description:
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Query("SELECT u FROM User u JOIN FETCH u.userRoles where u.userEmail = :email")
    User findUserByUserEmail(String email);
    void deleteUserByUserId(Long id);
    void deleteUserByUserEmail(String email);
    Page<User> findAll(Pageable pageable);
    Long countUsersByIsUserBan(Boolean isUserBan);
    long count();
    Page<User> findUsersByIsUserBan(Pageable pageable,Boolean isUserBan);
    @Query("SELECT u FROM User u JOIN FETCH u.userRoles WHERE u.userId = :userId")
    User findUserByUserId(Long userId);


}
