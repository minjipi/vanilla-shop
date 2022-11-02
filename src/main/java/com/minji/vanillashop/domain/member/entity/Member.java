package com.minji.vanillashop.domain.member.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.minji.vanillashop.domain.order.entity.Order;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "member_id")
    private Long id;

    @Column(length = 100, nullable = false, unique = true)
    private String email;

    @Column(length = 20, nullable = false)
    private String name;

    @Column(length = 100, nullable = false)
    private String password;

    @ElementCollection(fetch = FetchType.LAZY)
    private Set<MemberRole> roleSet;

    public void addMemberRole(MemberRole memberRole) {
        roleSet.add(memberRole);
    }

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private List<Order> orders;

    public void registerOrder(Order order) {
        if (orders == null) orders = new ArrayList<>();
        orders.add(order);
    }

    @Builder
    public Member(String name, String email, String password, MemberRole roleSet) {
        Assert.notNull(name, "이름은 필수값 입니다.");
        Assert.notNull(email, "이메일은 필수값 입니다.");
        Assert.notNull(password, "비밀번호는 필수값 입니다.");

        this.name = name;
        this.email = email;
        this.password = password;
    }

    public void updateMember(String name) {
        this.name = name;
    }
}

