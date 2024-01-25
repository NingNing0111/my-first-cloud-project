package org.ygcxy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ygcxy.entity.SensitiveWord;

/**
 * @Project: org.ygcxy.repository
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/11 09:40
 * @Description:
 */
@Repository
public interface SensitiveWordRepository extends JpaRepository<SensitiveWord,Long> {
    void deleteByWord(String word);
    void removeByWord(String word);
}
