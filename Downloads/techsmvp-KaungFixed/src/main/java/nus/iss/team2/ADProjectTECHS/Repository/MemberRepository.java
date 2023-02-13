package nus.iss.team2.ADProjectTECHS.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import nus.iss.team2.ADProjectTECHS.Model.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query("select m from Member m where m.email = :email ")
    Optional<Member> findMemberByEmail(@Param("email") String email);

    Optional<Member> findMemberByCurrentCity(String city);

    Optional<Member> findMemberByUsername(String username);

    Optional<Member> findMemberByMemberId(Long id);

    @Query("select m from Member m where m.memberId = :id")
    Member findMemberById(@Param("id")Long id);

}
