package com.ryan.restroom.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ryan
 * @version Id: Toilet, v 0.1 2023/4/27 2:00 PM ryan Exp $
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Toilet {

    private Long    id;

    private boolean clean;

    private boolean available;

}
