package com.ryan.restroom.service;

import com.ryan.restroom.api.IRestroomService;
import com.ryan.restroom.converter.ToiletConverter;
import com.ryan.restroom.dao.ToiletDAO;
import com.ryan.restroom.entity.ToiletEntity;
import com.ryan.restroom.pojo.Toilet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ryan
 * @version Id: RestroomService, v 0.1 2023/4/27 2:39 PM ryan Exp $
 */
@Slf4j
@RestController
@RequestMapping("toilet-service")
public class RestroomService implements IRestroomService {

    @Autowired
    private ToiletDAO toiletDAO;

    @Override
    @GetMapping("/checkAvailable")
    public List<Toilet> getAvailableToilet() {
        List<ToiletEntity> result = toiletDAO.findAllByCleanAndAvailable(true, true);
        return result.stream().map(ToiletConverter::convert).collect(Collectors.toList());
    }

    @Override
    @PostMapping("/occupy")
    public Toilet occupy(Long id) {
        ToiletEntity toilet = toiletDAO.findById(id).orElseThrow(() -> new RuntimeException("Toilet not found"));

        if (!toilet.isAvailable() || !toilet.isClean()) {
            throw new RuntimeException("restroom not available or unclean");
        }
        toilet.setAvailable(false);
        toilet.setClean(false);
        toiletDAO.save(toilet);
        return ToiletConverter.convert(toilet);
    }

    @Override
    @PostMapping("/release")
    public Toilet release(Long id) {
        ToiletEntity toilet = toiletDAO.findById(id).orElseThrow(() -> new RuntimeException("Toilet not found"));

        toilet.setAvailable(true);
        toilet.setClean(true);
        toiletDAO.save(toilet);
        return ToiletConverter.convert(toilet);
    }

    @Override
    @GetMapping("/checkAvailability")
    public boolean checkAvailability(Long id) {
        ToiletEntity toilet = toiletDAO.findById(id).orElseThrow(() -> new RuntimeException("Toilet not found"));
        return toilet.isAvailable();
    }

}
