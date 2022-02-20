package toolc.yourlist.playlist.infra;

import org.junit.jupiter.api.Test;
import toolc.yourlist.member.infra.MemberEntity;
import toolc.yourlist.playlist.domain.Member;
import toolc.yourlist.playlist.domain.NoExistMemberException;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberEntityMapperTest {
  @Test
  void toMember() {
    var mapper = new MemberEntityMapper();

    var actual = mapper.toMember(
      new MemberEntity("oh980225", "qwer1234!"));

    var expected = Member.builder()
      .loginId("oh980225")
      .password("qwer1234!")
      .build();

    assertThat(actual, is(expected));
  }

  @Test
  void toMember_null() {
    var mapper = new MemberEntityMapper();

    assertThrows(NoExistMemberException.class, () -> mapper.toMember((MemberEntity) null));
  }

  @Test
  void toMember_empty_entity() {
    var mapper = new MemberEntityMapper();

    assertThrows(NoExistMemberException.class, () -> mapper.toMember(Optional.empty()));
  }
}