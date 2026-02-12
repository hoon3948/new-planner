package kr.spartaclub.calender.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "profiles")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Profile extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;

    public Profile(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public void updateProfile(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }


}
