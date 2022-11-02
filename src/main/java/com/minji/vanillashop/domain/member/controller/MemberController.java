package com.minji.vanillashop.domain.member.controller;

import com.minji.vanillashop.domain.member.dto.domain.MemberDetailInfo;
import com.minji.vanillashop.domain.member.dto.request.MemberListQuery;
import com.minji.vanillashop.domain.member.dto.request.PostMemberDto;
import com.minji.vanillashop.domain.member.dto.request.UpdateMemberDto;
import com.minji.vanillashop.domain.member.entity.Member;
import com.minji.vanillashop.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/join")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    @PostMapping("/members/join")
    public String create(@Valid MemberForm memberForm, BindingResult result) {

        if (result.hasErrors()) {
            return "members/createMemberForm";
        }

        Member member = Member.builder()
                .name(memberForm.getName())
                .email(memberForm.getEmail())
                .password(memberForm.getPassword())
                .build();

        memberService.joinMember(member);
        return "redirect:/";
    }

    //    맴버생성
    @PostMapping("/api/member/join")
    public ResponseEntity<Long> joinMember(@Valid @RequestBody PostMemberDto postMemberDto) {
        return new ResponseEntity<>(memberService.apijoinMember(postMemberDto).getId(), HttpStatus.OK);
    }


    /**
     * 수정
     */
    @PutMapping("/api/members/{id}")
    public ResponseEntity<Long> updateMember(@PathVariable("id") Long id, @RequestBody @Valid UpdateMemberDto updateMemberDto) {

        return new ResponseEntity<>(memberService.update(id, updateMemberDto).getId(), HttpStatus.CREATED);
    }


    /**
     * 조회
     */
//    @GetMapping("/api/members")
//    public ResponseEntity<List<MemberDto>> membersV2() {
//
//        List<Member> findMembers = memberService.findMembers();
//        //엔티티 -> DTO 변환
//        List<MemberDto> collect = findMembers.stream()
//                .map(m -> new MemberDto(m.getName()))
//                .collect(Collectors.toList());
//
//        return new ResponseEntity<>(collect, HttpStatus.OK);
//    }

//    @GetMapping("/members")
//    public String list(Model model) {
//        List<Member> members = memberService.findMembers();
//        model.addAttribute("members", members);
//        return "/members/memberList";
//    }

//    @GetMapping("/members")
//    public ResponseEntity<List<MemberDetailInfo>> memberList(@RequestParam(required = false, value = "offset", defaultValue = "0") int offset,
//                                                             @RequestParam(required = false, value = "limit", defaultValue = "0") int limit) {
//
//        List<Member> members = memberService.readMemberList();
//        List<MemberDetailInfo> collect = members.stream()
//                .map(m -> new MemberDetailInfo(m.getEmail(), m.getName(), m.getModDate()))
//                .collect(Collectors.toList());
//
//        return new ResponseEntity<>(collect, HttpStatus.OK);
//    }

    @GetMapping("/members")
    public ResponseEntity<List<MemberDetailInfo>> memberList(@RequestParam(required = false) String memberEmail,
                                                             @RequestParam(required = false, value = "offset", defaultValue = "0") int offset,
                                                             @RequestParam(required = false, value = "limit", defaultValue = "0") int limit) {

        MemberListQuery query = MemberListQuery.builder()
                .memberEmail(memberEmail)
                .offset(offset)
                .limit(limit)
                .build();

//        List<Member> members = memberService.readMemberList();
//        List<MemberDetailInfo> collect = members.stream()
//                .map(m -> new MemberDetailInfo(m.getEmail(), m.getName(), m.getModDate()))
//                .collect(Collectors.toList());

        return new ResponseEntity<>(memberService.readMemberListByEmail(query), HttpStatus.OK);
    }

}
