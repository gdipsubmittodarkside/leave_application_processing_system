package nus.iss.team2.ADProjectTECHS.Service.Impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import lombok.AllArgsConstructor;
import nus.iss.team2.ADProjectTECHS.Repository.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import nus.iss.team2.ADProjectTECHS.Model.Member;
import nus.iss.team2.ADProjectTECHS.Service.MemberService;

@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;
    private PasswordEncoder encoder;


    @Override
    public Member findById(Long id) {

        return memberRepository.findMemberById(id);
    }

    @Override
    public Member createMember(Member member) {

        Member memberInDb = memberRepository.findMemberByUsername(member.getUsername()).orElse(null);
        if (memberInDb != null) throw new RuntimeException("User with username :" + memberInDb.getUsername() + " already exist");

        String encodedPassword = encoder.encode(member.getPassword());
        member.setPassword(encodedPassword);
        return memberRepository.save(member);
    }

    @Override
    public Boolean processOAuthPostLogin(String username,String email) {

        Member existMember = memberRepository.findMemberByUsername(username).orElse(null);

        if (existMember == null) {
            Member newMember = new Member();
            newMember.setUsername(username);
            newMember.setEmail(email);
            memberRepository.save(newMember);
            return false;
        }

        return true;

    }

    @Override
    public Boolean updateMember(Member member) {
        // TODO Auto-generated method stub
        Member memberInDb = memberRepository.findMemberByMemberId(member.getMemberId()).orElse(null);
        if (memberInDb == null) throw new RuntimeException("cannot find member");

        Member member1 = loadMemberByUsername(member.getUsername());
        if (member1 != null) throw new RuntimeException("username has exits");

        memberRepository.saveAndFlush(member);

        
        return null;
    }

    @Override
    public Boolean deleteMember(Long id) {
        // TODO Auto-generated method stub

        try {
            memberRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public List<Member> getAllMembers() {
        // TODO Auto-generated method stub
        return memberRepository.findAll();
    }

    @Override
    public Member loadMemberByUsername(String username) {
        return memberRepository.findMemberByUsername(username).orElse(null);
    }

    @Override
    @Transactional
    public void save(Member member){
        memberRepository.save(member);
    }

    @Override
    public void changePassword(Member member, String newPassword) {
        String encodedPassword = encoder.encode(newPassword);
        member.setPassword(encodedPassword);
        memberRepository.save(member);
    }

}
