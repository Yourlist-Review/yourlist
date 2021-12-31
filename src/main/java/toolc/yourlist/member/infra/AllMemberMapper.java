package toolc.yourlist.member.infra;

import lombok.RequiredArgsConstructor;
import toolc.yourlist.member.domain.AllMember;
import toolc.yourlist.member.domain.Member;
import toolc.yourlist.playlist.domain.NoExistMemberException;

@RequiredArgsConstructor
public class AllMemberMapper implements AllMember {

  private final JpaAllMemberEntity jpaAllMemberEntity;
  private final MemberEntityMapper mapper = new MemberEntityMapper();

  @Override
  public Member findByLoginId(String loginId) {
    return mapper.toMember(jpaAllMemberEntity.findByLoginId(loginId));
  }

  @Override
  public Member findById(Long id) {
    return mapper.toMember(jpaAllMemberEntity.findById(id));
  }

  @Override
  public boolean exist(Long id) {
    try {
      findById(id);
      return true;
    } catch (NoExistMemberException e) {
      return false;
    }
  }
}
