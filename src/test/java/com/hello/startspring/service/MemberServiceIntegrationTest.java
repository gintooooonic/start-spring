package com.hello.startspring.service;

import com.hello.startspring.domain.Member;
import com.hello.startspring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {
  @Autowired MemberService memberService;
  @Autowired MemberRepository memberRepository;

  @Test
  void join() {
    // given
    Member member = new Member();
    member.setName("spring");

    // when
    Long saveId = memberService.join(member);

    // then
    Member findMember = memberService.findOne(saveId).get();
    assertThat(member.getName()).isEqualTo(findMember.getName());
  }

  @Test
  void duplicatedJoin() {
    // given
    Member member1 = new Member();
    member1.setName("spring");

    Member member2 = new Member();
    member2.setName("spring");

    // when, then
    memberService.join(member1);
    IllegalStateException e = assertThrows(IllegalStateException.class,
            () -> memberService.join(member2));

    assertThat(e.getMessage()).isEqualTo("Name already exists");
  }
}