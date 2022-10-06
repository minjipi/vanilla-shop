package com.minji.vanillashop.domain.member.service;


import com.minji.vanillashop.domain.member.entity.Member;
import com.minji.vanillashop.domain.member.repository.MemberRepository;
import com.minji.vanillashop.global.exceptions.MemberEmailDuplicateException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public Long joinMember(Member member) {
        validMemberEmailDuplicate(member.getEmail());
        memberRepository.save(member);
        return member.getId();
    }

    //회원 아이디 중복검증
    private void validMemberEmailDuplicate(String email) {
        if (memberRepository.existsByEmail(email))
            throw new MemberEmailDuplicateException("이미 사용중인 이메일 입니다.");
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }


}
