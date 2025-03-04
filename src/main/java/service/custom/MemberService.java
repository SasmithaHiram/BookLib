package service.custom;

import dto.Member;

import java.util.ArrayList;

public interface MemberService {
    boolean addMember(Member member);
    Member searchMember(String id);
    boolean updateBook(Member member);
    boolean deleteMember(String text);
    ArrayList<Member> getAllMembers();

}
