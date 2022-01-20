package com.hello.startspring.service;

import com.hello.startspring.domain.Member;
import com.hello.startspring.repository.MemberRepository;
import com.hello.startspring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
  private final MemberRepository memberRepository;

  public MemberService(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  public Long join(Member member) {
    validateDuplicatedMember(member);
    memberRepository.save(member);
    return member.getId();
  }

  private void validateDuplicatedMember(Member member) {
    memberRepository.findByName(member.getName())
            .ifPresent(m -> {
              throw new IllegalStateException("Name already exists");
            });
  }

  public List<Member> findMembers() {
    return memberRepository.findAll();
  }

  public Optional<Member> findOne(Long memberId) {
    return memberRepository.findById(memberId);
  }
}
