package com.minji.vanillashop.domain.member.service;


import com.minji.vanillashop.domain.member.dto.domain.MemberDetailInfo;
import com.minji.vanillashop.domain.member.dto.request.MemberListQuery;
import com.minji.vanillashop.domain.member.dto.request.PostMemberDto;
import com.minji.vanillashop.domain.member.dto.request.UpdateMemberDto;
import com.minji.vanillashop.domain.member.entity.Member;
import com.minji.vanillashop.domain.member.repository.MemberQuerydslRepository;
import com.minji.vanillashop.domain.member.repository.MemberRepository;
import com.minji.vanillashop.global.exceptions.MemberEmailDuplicateException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberQuerydslRepository memberQuerydslRepository;

    @Transactional
    public Long joinMember(Member member) {
        validMemberEmailDuplicate(member.getEmail());
        memberRepository.save(member);
        return member.getId();
    }

    @Transactional
    public Member apijoinMember(PostMemberDto postMemberDto) {
        validMemberEmailDuplicate(postMemberDto.getEmail());
        postMemberDto.validation();
        return memberRepository.save(postMemberDto.toEntity());
    }

    //회원 아이디 중복검증
    private void validMemberEmailDuplicate(String email) {
        if (memberRepository.existsByEmail(email))
            throw new MemberEmailDuplicateException("이미 사용중인 이메일 입니다.");
    }


    public List<Member> readMemberList() {
        return memberRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<MemberDetailInfo> readMemberListByEmail(MemberListQuery query){
        return memberQuerydslRepository.findOrderDetailByMemberBy(query);
    }


//    @Transactional(readOnly = true)
//    public List<MemberOrderDetail> readMemberOrderDetailList(String memberEmail){
//        return memberRepository.findMemberOrderDetailListBy(memberEmail);
//    }


//    public Optional<Member> findOne(String memberId) {
//        return memberRepository.findById(memberId);
//    }

    /**
     * 회원 수정
     * @return
     */
//    @Transactional
//    public void update(String id, String name) {
//        Member member = memberRepository.findById(id).orElseThrow(
//                () -> new NoSuchElementException());
//        member.updateMember(name);
//    }

    @Transactional
    public Member update(Long id, UpdateMemberDto updateMemberDto) {
        Member member = memberRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException());
        member.updateMember(updateMemberDto.getName());

        return member;
    }

}
