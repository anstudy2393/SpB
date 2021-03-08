package jpabook.jpashop;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class Member { // Entity 정보를 보고 test코드에서 테이블 생성

    @Id @GeneratedValue
    private Long id;
    private String username;

}
