package toolc.yourlist.member.domain;

import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;

import java.time.Period;

import static io.vavr.control.Either.left;
import static io.vavr.control.Either.right;

@RequiredArgsConstructor
public class VisitorAuthProvider {

  private final TokenProvider tokenProvider;
  private final TokenReader tokenReader;
  private final AllVisitor allVisitor;

  public Either<String, Token> registerVisitor(VisitorRegisterRequest request) {
    if (allVisitor.isExistByUUID(request.uuid())) {
      return left("Already registered uuid");
    }
    allVisitor.registerVisitor(request.uuid());
    return right(getVisitorToken(request.uuid(), request.isPC()).get());
  }

  Either<String, Token> getVisitorToken(String uuid, boolean isPC) {
    if (allVisitor.isNotExistByUUID(uuid)) {
      return left("등록되어 있지 않은 방문자 입니다.");
    } else {
      Long id = allVisitor.findIdByUUID(uuid);
      Period refreshTokenExpiration = isPC ? Period.ofDays(7) : Period.ofDays(90);

      return right(tokenProvider.makeToken(id, refreshTokenExpiration));
    }
  }

  Either<String, Token> reissueToken(String accessToken, String refreshToken, boolean isPC) {
    Long id = tokenReader.getId(accessToken);
    if (allVisitor.isNotExistById(id)) {
      // todo :: usecase에는 없다. 하지만 서버 작동 중 문제가 생겨서 발생했을 가능성은? -> 예외로?
      throw new IllegalArgumentException();
    }

    Period refreshTokenExpiration = isPC ? Period.ofDays(7) : Period.ofDays(90);

    return right(tokenProvider.makeToken(id, refreshTokenExpiration));
  }
}
