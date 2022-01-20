package com.hello.startspring.repository;

import com.hello.startspring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class MemoryMemberRepositoryTest {
  MemoryMemberRepository repository = new MemoryMemberRepository();

  @AfterEach
  public void afterEach() {
    repository.clearStore();
  }

  @Test
  public void save() {
    // given
    Member member = new Member();
    member.setName("spring");

    // when
    repository.save(member);

    // then
    Member result = repository.findById(member.getId()).get();
    Assertions.assertThat(result).isEqualTo(member);
  }

  @Test
  public void findByName() {
    // given
    String name = "spring";
    Member member = new Member();
    member.setName(name);
    repository.save(member);

    // when
    Member result = repository.findByName(name).get();

    // then
    Assertions.assertThat(result).isEqualTo(member);
  }

  @Test
  public void findAll() {
    // given
    Member member1 = new Member();
    member1.setName("spring1");
    repository.save(member1);

    Member member2 = new Member();
    member2.setName("spring2");
    repository.save(member2);

    // when
    List<Member> result = repository.findAll();

    // then
    Assertions.assertThat(result.size()).isEqualTo(2);
  }
}
