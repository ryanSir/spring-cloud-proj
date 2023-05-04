package com.ryan.restroom.api;

import com.ryan.restroom.pojo.Toilet;

import java.util.List;

/**
 * @author ryan
 * @version Id: IRestroomService, v 0.1 2023/4/27 2:09 PM ryan Exp $
 */
public interface IRestroomService {

    List<Toilet> getAvailableToilet();

    Toilet occupy(Long id);

    Toilet release(Long id);

    boolean checkAvailability(Long id);

    Toilet testZip(String input);
}
