package kr.spartaclub.calender.repository;

import jakarta.validation.constraints.NotBlank;
import kr.spartaclub.calender.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Optional<Profile> findByEmail(@NotBlank String email);

    boolean existsByEmail(String email);
}
