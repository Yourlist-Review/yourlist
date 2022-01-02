package toolc.yourlist.auth.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@Getter

public final class NonMemberLoginRequest {
  private final String deviceId;
  private final boolean isPC;

  public NonMemberLoginRequest(String deviceId, boolean isPC) {
    this.isPC = isPC;
    checkNullOrEmpty(deviceId);
    this.deviceId = deviceId;
  }

  private void checkNullOrEmpty(String deviceId) {
    if (deviceId == null || deviceId.equals(""))
      throw new IllegalArgumentException("잘못된 UUID 입니다.");
  }
}