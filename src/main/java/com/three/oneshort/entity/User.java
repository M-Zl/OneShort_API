package com.three.oneshort.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;  // 로그인할 때 사용할 이름 (예: 이메일 또는 별명)

    private String password;  // 나중에 비밀번호 암호화 추가 가능
}
