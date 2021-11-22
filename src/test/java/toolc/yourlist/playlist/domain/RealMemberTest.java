package toolc.yourlist.playlist.domain;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class RealMemberTest {

  @Test
  void 회원() {
    assertThat(new RealMember().matches(
      SaveRequest.builder()
        .isMember(true)
        .build()), is(true));
  }

  @Test
  void 비회원() {
    assertThat(new RealMember().matches(
      SaveRequest.builder()
        .isMember(false)
        .build()), is(false));
  }
}