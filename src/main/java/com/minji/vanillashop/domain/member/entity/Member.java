package com.minji.vanillashop.domain.member.entity;


import lombok.*;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@ToString(exclude = "roleSet")
public class Member extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(length = 100, nullable = false, unique = true)
    private String email;

    @Column(length = 20, nullable = false)
    private String name;

    @Column(length = 100, nullable = false)
    private String password;

    private boolean fromSocial;

    @ElementCollection(fetch = FetchType.LAZY)
    private Set<MemberRole> roleSet;

    public void addMemberRole(MemberRole memberRole) {
        roleSet.add(memberRole);
    }

    @Builder
    public Member(String name, String email, String password) {
        Assert.notNull(name, "이름은 필수값 입니다.");
        Assert.notNull(email, "이메일은 필수값 입니다.");

        Assert.notNull(password, "비밀번호는 필수값 입니다.");

        this.name = name;
        this.email = email;
        this.password = password;
    }

}

