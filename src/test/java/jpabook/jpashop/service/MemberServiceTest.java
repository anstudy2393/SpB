package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager em; // db의 쿼리를 보기 위해 생성

    @Test
    // @Rollback(false) // Transctional이 기본적으로 Rollback을 해버리기 때문에 db의 쿼리를 보기 위해 이용함
    public void 회원가입() throws Exception {
        //given
        Member member = new Member();
        member.setName("kim");

        //when
        Long savedId = memberService.join(member);

        //then
        em.flush(); // db에 쿼리를 날림 @Rollback(false)와 같음
        assertEquals(member, memberRepository.findOne(savedId));
    } 
    
    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        //when
        memberService.join(member1);
        memberService.join(member2); // 원래는 예외가 발생해야함!
                                     // 하지만 (expected = IllegalStateException.class)를 써서 예외처리

        //then
        fail("예외가 발생해야 한다."); // 예외가 발생하면 이곳으로 옴. 오면 안됨.
    } 
}