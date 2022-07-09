package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class NewTest {
    @Mock
    MemberService memberService;

    @Test
    public void 회원가입() {
        Member member = new Member();
        member.setName("han");

        memberService.join(member);

//        when(userRepository.save(any(User.class))).then(AdditionalAnswers.returnFirstArg());

        List<Member> memberList = memberService.findMembers();

        for (Member member1 : memberList) {
            System.out.println("member1 = " + member1);
        }

        assertThat(memberList.size()).isEqualTo(3);
    }

}
