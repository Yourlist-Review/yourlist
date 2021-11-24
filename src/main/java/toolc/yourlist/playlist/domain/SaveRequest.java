package toolc.yourlist.playlist.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class SaveRequest {
  private final String loginId;
  private final String title;
  private final boolean isMember;
  private final long playlistCount;

  @Builder
  public SaveRequest(String loginId, String title, boolean isMember, long playlistCount) {
    this.loginId = loginId;
    this.title = title;
    this.isMember = isMember;
    this.playlistCount = playlistCount;
  }
}