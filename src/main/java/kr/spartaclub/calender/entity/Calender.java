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

    public Calender(String title, String content, Profile profile){
        this.title = title;
        this.content = content;
        this.profile = profile;
    }

    public void updateCalender(String title, String content){
        this.title = title;
        this.content = content;
    }
}
