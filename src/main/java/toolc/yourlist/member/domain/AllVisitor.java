package toolc.yourlist.member.domain;

public interface AllVisitor {
  boolean isExistByUUID(String uuid);

  boolean isNotExistByUUID(String uuid);

  boolean isNotExistById(Long id);

  Visitor registerVisitor(String uuid);

  Long findIdByUUID(String uuid);
}
