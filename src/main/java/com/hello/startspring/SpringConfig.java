package com.hello.startspring;

import com.hello.startspring.repository.*;
import com.hello.startspring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {
  private final MemberRepository memberRepository;

  @Autowired
  public SpringConfig(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  @Bean
  public MemberService memberSerivce() {
    return new MemberService(memberRepository);
  }

//  @Bean
//  public MemberRepository memberRepository() {
//    return new JpaMemberRepository(em);
//  }
}
