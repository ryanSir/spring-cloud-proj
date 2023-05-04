package com.ryan.employee.feign;

import com.ryan.restroom.api.IRestroomService;
import com.ryan.restroom.pojo.Toilet;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author ryan
 * @version Id: RestroomFeignClient, v 0.1 2023/4/28 4:09 PM ryan Exp $
 */
@FeignClient(value = "restroom-service")
public interface RestroomFeignClient {

    @GetMapping("/toilet-service/checkAvailable")
    List<Toilet> getAvailableToilet();

    @PostMapping("/toilet-service/occupy")
    Toilet occupy(@RequestParam("id") Long id);

    @PostMapping("/toilet-service/release")
    Toilet release(@RequestParam("id") Long id);

    @GetMapping("/toilet-service/checkAvailability")
    boolean checkAvailability(@RequestParam("id") Long id);

    @GetMapping("/toilet-service/testZip")
    ResponseEntity<byte[]> testZip(@RequestParam("input") String input);
}
