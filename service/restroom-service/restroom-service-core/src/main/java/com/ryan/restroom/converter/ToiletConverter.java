package com.ryan.restroom.converter;

import com.ryan.restroom.entity.ToiletEntity;
import com.ryan.restroom.pojo.Toilet;

/**
 * @author ryan
 * @version Id: ToiletConverter, v 0.1 2023/4/27 2:45 PM ryan Exp $
 */
public class ToiletConverter {

    public static Toilet convert(ToiletEntity entity) {
        return Toilet.builder().id(entity.getId()).clean(entity.isClean()).available(entity.isAvailable()).build();
    }
}
