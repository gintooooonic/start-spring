package com.hello.startspring;

import com.hello.startspring.repository.MemberRepository;
import com.hello.startspring.repository.MemoryMemberRepository;
import com.hello.startspring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
  @Bean
  public MemberService memberSerivce() {
    return new MemberService(memberRepository());
  }

  @Bean
  public MemberRepository memberRepository() {
    return new MemoryMemberRepository();
  }
}
