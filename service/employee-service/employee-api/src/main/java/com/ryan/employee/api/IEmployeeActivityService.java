package com.ryan.employee.api;

import com.ryan.employee.pojo.EmployeeActivity;

/**
 * @author ryan
 * @version Id: IEmployeeActivityService, v 0.1 2023/4/27 5:41 PM ryan Exp $
 */
public interface IEmployeeActivityService {

    EmployeeActivity useToilet(Long employeeId);

    EmployeeActivity restoreToilet(Long employeeId);
}
