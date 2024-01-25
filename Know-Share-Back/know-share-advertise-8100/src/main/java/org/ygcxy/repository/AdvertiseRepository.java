package org.ygcxy.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ygcxy.entity.Advertise;

/**
 * @Project: org.ygcxy.repository
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/10 11:32
 * @Description:
 */

@Repository
public interface AdvertiseRepository extends JpaRepository<Advertise,Long> {
    Advertise findAdvertiseById(Long id);
    void deleteAdvertiseById(Long id);
    long count();
    Long countAdvertisesByIsShow(Boolean isShow);
    Page<Advertise> findAll(Pageable pageable);
    Page<Advertise> findAdvertisesByIsShow(Pageable pageable,Boolean isShow);
}
