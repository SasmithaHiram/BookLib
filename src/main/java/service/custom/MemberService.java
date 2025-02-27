package service.custom;

import dto.Member;

import java.awt.*;
import java.util.List;

public interface MemberService {
    boolean addMember(Member member);
    Member searchMember(String id);
    boolean updateBook(Member member);
    boolean deleteMember(String text);
    List<Member> getAllMembers();
}
