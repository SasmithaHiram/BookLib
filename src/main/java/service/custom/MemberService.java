package service.custom;

import dto.Member;

public interface MemberService {
    boolean addMember(Member member);
    Member searchMember(String id);
    boolean updateBook(Member member);
}
