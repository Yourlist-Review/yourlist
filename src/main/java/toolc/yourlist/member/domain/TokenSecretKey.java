package toolc.yourlist.member.domain;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

public class TokenSecretKey {
  private String key =
    "c3ByaW5nLWJvb3Qtc2VjdXJpdHktand0LXR1dG9yaWFsLWppd29vbi1zcHJpbmctYm9vdC1zZWN1cml0eS1qd3QtdHV0b3JpYWwK";

  public SecretKey secretKey() {
    return Keys.hmacShaKeyFor(Decoders.BASE64.decode(key));
  }
}
