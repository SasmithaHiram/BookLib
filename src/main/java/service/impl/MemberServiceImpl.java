package service.impl;

import dto.Member;
import entity.MemberEntity;
import jakarta.inject.Inject;
import org.modelmapper.ModelMapper;
import repository.custom.MemberDao;
import repository.custom.impl.MemberDaoImpl;
import service.custom.MemberService;

import java.util.ArrayList;
import java.util.List;

public class MemberServiceImpl implements MemberService {
    @Inject
    MemberDao dao;

    @Override
    public boolean addMember(Member member) {
        MemberEntity map = new ModelMapper().map(member, MemberEntity.class);
        return dao.save(map);
    }

    @Override
    public Member searchMember(String id) {
        MemberEntity search = dao.search(id);

        if (search != null) {
            return new ModelMapper().map(search, Member.class);
        }
        return null;
    }

    @Override
    public boolean updateBook(Member member) {
        MemberEntity map = new ModelMapper().map(member, MemberEntity.class);
        return dao.update(map);
    }

    @Override
    public boolean deleteMember(String text) {
        boolean deleted = dao.delete(text);
        return deleted;
    }

    @Override
    public ArrayList<Member> getAllMembers() {
        List<MemberEntity> all = dao.getAll();

        ArrayList<Member> members = new ArrayList<>();

        all.forEach(memberEntity -> {
            Member map = new ModelMapper().map(memberEntity, Member.class);
            members.add(map);
        });
       return members;
    }

}
