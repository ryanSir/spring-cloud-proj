package com.ryan.restroom.dao;

import com.ryan.restroom.entity.ToiletEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ryan
 * @version Id: ToiletDAO, v 0.1 2023/4/27 2:23 PM ryan Exp $
 */
@Repository
public interface ToiletDAO extends JpaRepository<ToiletEntity, Long> {


    List<ToiletEntity> findAllByCleanAndAvailable(boolean clean, boolean available);
}
