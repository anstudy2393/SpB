package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded // 내장 타입을 포함
    private Address address;

    @OneToMany(mappedBy = "member") // 1 : * , order테이블에 있는 member필드에 의해 맵핑됨
                                    // 읽기 전용(값을 넣는다하더라도 foreign key 값이 변동되지 않음)
    private List<Order> orders = new ArrayList<>();
}
