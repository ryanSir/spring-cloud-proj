package com.ryan.employee.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @author ryan
 * @version Id: EmployeeActivity, v 0.1 2023/4/27 5:30 PM ryan Exp $
 */
@Data
public class EmployeeActivity {

    private Long         id;

    private Long         employeeId;

    private ActivityType activityType;

    private Long         resourceId;

    private Date         startTime;

    private Date         endTime;

    private boolean      active;
}
