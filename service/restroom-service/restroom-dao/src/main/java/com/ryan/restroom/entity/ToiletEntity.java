package com.ryan.restroom.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * @author ryan
 * @version Id: Toilet, v 0.1 2023/4/27 2:00 PM ryan Exp $
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "toilet")
@Entity
public class ToiletEntity {

    @Id
    // 主键生成类型 IDENTITY表示交给数据库生成主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long    id;

    // nullable 非空
    @Column(name = "clean", nullable = false)
    private boolean clean;

    @Column(name = "available", nullable = false)
    private boolean available;
}
