package com.hello.startspring.service;

import com.hello.startspring.domain.Member;
import com.hello.startspring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
  MemberService memberService;
  MemoryMemberRepository memberRepository;

  @BeforeEach
  void beforeEach() {
    memberRepository = new MemoryMemberRepository();
    memberService = new MemberService(memberRepository);
  }

  @AfterEach
  void afterEach() {
    memberRepository.clearStore();
  }

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