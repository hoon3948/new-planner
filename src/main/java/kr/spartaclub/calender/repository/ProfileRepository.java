package kr.spartaclub.calender.repository;

import kr.spartaclub.calender.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
