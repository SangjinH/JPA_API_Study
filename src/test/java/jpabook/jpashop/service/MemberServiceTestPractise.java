package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MemberServiceTestPractise {

    @InjectMocks
    MemberService memberService;
    @Mock
    MemberRepository memberRepository;

    @Test
    @DisplayName("회원가입_성공_테스트")
    public void 회원가입_테스트() throws Exception {
        // given
        String name = "member1";

        Member member = new Member(name);

        doNothing().when(memberRepository).save(any(Member.class));
        doReturn(new Member(name)).when(memberRepository)
                .findOne(any(Long.class));

        // when
        memberService.join(member);
        Member findMember = memberService.findOne(1L);

        // then
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }


    @Test
    public void 모든_회원_조회() throws Exception {
        // given
        int memberCount = 3;
        ArrayList<Member> memberList = new ArrayList<>();
        memberList.add(new Member("member1"));
        memberList.add(new Member("member2"));
        memberList.add(new Member("member3"));


        doReturn(memberList).when(memberRepository)
                .findAll();
        // when
        List<Member> members = memberService.findMembers();

        // then
        assertThat(members.size()).isEqualTo(memberCount);
    }

    @Test
    @DisplayName("같은 이름으로 등록된 회원은 가입되지 않는다.")
    public void 중복_회원_검증() throws IllegalStateException {
        // given
        Member member = new Member("duplicateMember");

        doThrow(IllegalStateException.class).when(memberRepository).save(member);

        // when
        Assertions.assertThrows(IllegalStateException.class, () ->
                memberService.join(member)
        );

    }
}
