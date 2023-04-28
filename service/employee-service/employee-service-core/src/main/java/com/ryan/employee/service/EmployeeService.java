package com.ryan.employee.service;

import com.ryan.employee.api.IEmployeeActivityService;
import com.ryan.employee.dao.EmployeeActivityDAO;
import com.ryan.employee.entity.EmployeeActivityEntity;
import com.ryan.employee.pojo.ActivityType;
import com.ryan.employee.pojo.EmployeeActivity;
import com.ryan.restroom.pojo.Toilet;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ryan
 * @version Id: EmployeeService, v 0.1 2023/4/27 5:55 PM ryan Exp $
 */
@Slf4j
@RestController
@RequestMapping("employee")
public class EmployeeService implements IEmployeeActivityService {

    @Autowired
    private EmployeeActivityDAO employeeActivityDAO;

    @Autowired
    private RestTemplate        restTemplate;

    @Autowired
    private DiscoveryClient     discoveryClient;

    @Override
    @PostMapping("/toilet-break")
    @Transactional
    public EmployeeActivity useToilet(Long employeeId) {
        List<ServiceInstance> instances = discoveryClient.getInstances("restroom-service");

        int count = employeeActivityDAO.countByEmployeeIdAndActivityTypeAndActive(employeeId, ActivityType.TOILET_BREAK, true);
        if (count > 0) {
            throw new RuntimeException("快拉！");
        }
        // 发起远程调用
        Toilet[] toilets = restTemplate.getForObject("http://restroom-service/toilet-service/checkAvailable/", Toilet[].class);
        if (ArrayUtils.isEmpty(toilets)) {
            throw new RuntimeException("shit in urinal");
        }
        // 抢坑
        MultiValueMap<String, Object> args = new LinkedMultiValueMap<>();
        args.add("id", toilets[0].getId());
        Toilet toilet = restTemplate.postForObject("http://restroom-service/toilet-service/occupy", args, Toilet.class);

        // 保存如厕记录
        EmployeeActivityEntity toiletBreak = EmployeeActivityEntity.builder().employeeId(employeeId).active(true).activityType(ActivityType.TOILET_BREAK).resourceId(toilet.getId())
            .build();
        employeeActivityDAO.save(toiletBreak);
        EmployeeActivity result = new EmployeeActivity();
        BeanUtils.copyProperties(toiletBreak, result);
        return result;
    }

    @Override
    @Transactional
    @PostMapping("/done")
    public EmployeeActivity restoreToilet(Long activityId) {
        EmployeeActivityEntity record = employeeActivityDAO.findById(activityId).orElseThrow(() -> new RuntimeException("record not found"));

        if (!record.isActive()) {
            throw new RuntimeException("activity is no longer active");
        }
        // 释坑
        MultiValueMap<String, Object> args = new LinkedMultiValueMap<>();
        args.add("id", record.getResourceId());
        Toilet toilet = restTemplate.postForObject("http://restroom-service/toilet-service/release", args, Toilet.class);
        record.setActive(false);
        record.setEndTime(new Date());
        employeeActivityDAO.save(record);

        EmployeeActivity result = new EmployeeActivity();
        BeanUtils.copyProperties(record, result);
        return result;
    }

    @GetMapping("/testRibbon")
    public void testRibbon() {
        restTemplate.getForObject("http://restroom-service/toilet-service/checkAvailable/", Toilet[].class);
    }
}
