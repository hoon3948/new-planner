package kr.spartaclub.calender.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "calenders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Calender extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long calenderId;
    @Column(length = 30, nullable = false)
    private String title;
    @Column(length = 200, nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private Profile profile;

//    @Column(nullable = false)
//    private String author;
    @Column(nullable = false)
    private String password;

    public Calender(String title, String content, Profile profile, String password){
        this.title = title;
        this.content = content;
        this.profile = profile;
//        this.author = author;
        this.password = password;
    }

    public void updateCalender(String title){
        this.title = title;
//        this.author = author;
    }
}
