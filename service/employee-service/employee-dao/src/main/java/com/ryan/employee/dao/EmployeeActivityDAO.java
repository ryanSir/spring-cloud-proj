package com.ryan.employee.dao;

import com.ryan.employee.entity.EmployeeActivityEntity;
import com.ryan.employee.pojo.ActivityType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author ryan
 * @version Id: EmployeeActivityDAO, v 0.1 2023/4/27 5:51 PM ryan Exp $
 */
public interface EmployeeActivityDAO extends JpaRepository<EmployeeActivityEntity, Long> {

    int countByEmployeeIdAndActivityTypeAndActive(Long employeeId, ActivityType activityType, boolean active);
}
