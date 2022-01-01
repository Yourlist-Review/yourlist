package toolc.yourlist.auth.infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import toolc.yourlist.auth.domain.*;
import toolc.yourlist.auth.token.domain.*;
import toolc.yourlist.member.infra.JpaAllMemberEntity;


@Configuration
public class BeanConfig {

  @Bean
  LoginIdFactory loginIdFactory() {
    return new LoginIdFactory(new AllLoginPolicy());
  }

  @Bean
  PasswordFactory passwordFactory() {
    return new PasswordFactory(new AllPasswordPolicy());
  }

  @Bean
  RealLoginRequestMapperFromJson realLoginRequestMapperFromJson() {
    return new RealLoginRequestMapperFromJson(loginIdFactory(), passwordFactory());
  }

  @Bean
  NonLoginRequestMapperFromJson nonLoginRequestMapperFromJson() {
    return new NonLoginRequestMapperFromJson();
  }

  CurrentTimeServer currentTimeServer = new RealTimeServer();

  @Bean
  TokenExpirationConfig expirationConfig() {
    return new TokenExpirationConfig(currentTimeServer);
  }

  @Autowired
  JwtSetConfigSecretKeyYamlAdapter jwtSetConfigSecretKeyYamlAdapter;


  @Autowired
  JpaAllMemberEntity jpaAllMemberEntity;

  @Bean
  AllMember allMember() {
    return new JpaAllMember(jpaAllMemberEntity, new MemberDomainAdapter());
  }

  @Bean
  AllNonMember allNonMember() {
    return new JpaAllNonMember(jpaAllMemberEntity, new NonMemberDomainAdapter());
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new PasswordEncoderSpringAdapter();
  }

  @Bean
  public CheckPassword checkPassword() {
    return new CheckPassword(passwordEncoder());
  }

  @Bean
  public TokenProvider tokenProvider() {
    return new JwtProvider(jwtSetConfigSecretKeyYamlAdapter.toJwtSetConfig(), expirationConfig());
  }

  @Bean
  public MemberLogin memberLogin() {
    return new MemberLogin(allMember(), tokenProvider(), checkPassword());
  }

  @Bean
  public NonMemberLogin nonMemberLogin() {
    return new NonMemberLogin(allNonMember(), tokenProvider());
  }
}
