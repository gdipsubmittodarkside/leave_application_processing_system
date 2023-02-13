package nus.iss.team2.ADProjectTECHS.Service;

import java.util.List;
import java.util.Optional;

import nus.iss.team2.ADProjectTECHS.Model.Member;

public interface MemberService {
    Member findById(Long id);
    Member createMember(Member member);
    Boolean updateMember(Member member);
    Boolean deleteMember(Long id);
    List<Member> getAllMembers();

    Member loadMemberByUsername(String username);

    Boolean processOAuthPostLogin(String username,String email);

    void save(Member member);
    
    void changePassword(Member member, String newPassword);
}