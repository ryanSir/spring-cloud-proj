package com.ryan.employee.entity;

import java.util.Date;

import com.ryan.employee.pojo.ActivityType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * @author ryan
 * @version Id: EmployeeActivity, v 0.1 2023/4/27 5:30 PM ryan Exp $
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "employee_activity")
@Entity
public class EmployeeActivityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long         id;

    @Column(name = "employee_id", nullable = false)
    private Long         employeeId;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "activity_type")
    private ActivityType activityType;

    @Column(name = "resource_id")
    private Long         resourceId;

    @CreatedDate
    @Column(name = "start_time")
    private Date         startTime;

    @Column(name = "end_time")
    private Date         endTime;

    private boolean      active;
}
