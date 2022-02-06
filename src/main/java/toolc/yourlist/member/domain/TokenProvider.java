package toolc.yourlist.member.domain;

import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;

import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class TokenProvider {

  private final TokenSecretKey tokenSecretKey;
  private final TimeServer timeServer;

  Token makeToken(Long id, Period refreshTokenExpiration, UserType userType) {

    Map<String, Object> payloads = new HashMap<>();
    payloads.put("Id", id);
    payloads.put("UserType", userType);
    final var accessToken = Jwts.builder()
      .setClaims(payloads)
      .setExpiration(Date.from(timeServer.nowTime().plus(30, ChronoUnit.MINUTES).toInstant()))
      .signWith(tokenSecretKey.secretKey())
      .compact();

    final var refreshToken = Jwts.builder()
      .setExpiration(Date.from(timeServer.nowTime().plus(refreshTokenExpiration).toInstant()))
      .signWith(tokenSecretKey.secretKey())
      .compact();

    return new Token(accessToken, refreshToken);
  }
}

