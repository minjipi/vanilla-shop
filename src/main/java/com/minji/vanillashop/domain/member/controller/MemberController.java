package com.minji.vanillashop.domain.member.controller;

import com.minji.vanillashop.domain.member.dto.request.PostMemberDto;
import com.minji.vanillashop.domain.member.dto.request.UpdateMemberDto;
import com.minji.vanillashop.domain.member.entity.Member;
import com.minji.vanillashop.domain.member.service.MemberService;
import lombok.Data;
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
    @PostMapping("/member/join")
    public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest request) {

        Member member = Member.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();

        Long id = memberService.joinMember(member);
        return new CreateMemberResponse(id);
    }

    @PostMapping("/api/member/join")
    public ResponseEntity<Long> joinMember(@Valid @RequestBody PostMemberDto postMemberDto){
        return new ResponseEntity<>(memberService.apijoinMember(postMemberDto).getId(), HttpStatus.OK);
    }


    /**
     * 수정
     */
//    @PutMapping("/api/members/{id}")
//    public UpdateMemberResponse updateMember(@PathVariable("id") String id, @RequestBody @Valid UpdateMemberDto updateMemberDto) {
//        memberService.update(id, updateMemberDto.getName());
//        Optional<Member> findMember = memberService.findOne(id);
//        return new UpdateMemberResponse(findMember.get().getId(), findMember.get().getName());
//    }

    @PutMapping("/api/members/{id}")
    public ResponseEntity<Long> updateMember(@PathVariable("id") Long id, @RequestBody @Valid UpdateMemberDto updateMemberDto) {

        return new ResponseEntity<>(memberService.update(id, updateMemberDto).getId(),HttpStatus.CREATED);
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

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);

        return "/members/memberList";
    }


    @Data
    static class CreateMemberResponse {
        private Long id;
        public CreateMemberResponse(Long id) {
            this.id = id;
        }
    }

    @Data
    static class CreateMemberRequest {
        private String name;
        private String email;
        private String password;
    }


}
