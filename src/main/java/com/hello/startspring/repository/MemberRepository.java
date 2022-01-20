package com.hello.startspring.repository;

import com.hello.startspring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
  Member save(Member member);
  Optional<Member> findById(Long id);
  Optional<Member> findByName(String name);
  List<Member> findAll();
}
