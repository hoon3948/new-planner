package kr.spartaclub.calender.controller;


import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import kr.spartaclub.calender.dtoprofile.*;
import kr.spartaclub.calender.service.ProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;

    @PostMapping("/signup") //회원가입
    public ResponseEntity<CreateProfileResponse> createProfile(@RequestBody CreateProfileRequest request){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(profileService.save(request));
    }

    @PostMapping("/login") // 로그인
    public ResponseEntity<Void> login(@Valid @RequestBody LoginRequest request, HttpSession session) {
        SessionProfile sessionProfile = profileService.login(request);
        session.setAttribute("loginProfile", sessionProfile);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/logout") //로그아웃
    public ResponseEntity<Void> logout(
            @SessionAttribute(name = "loginProfile", required = false) SessionProfile sessionProfile,
            HttpSession session
    ) {
        if (sessionProfile == null) {
            return ResponseEntity.badRequest().build();
        }
        session.invalidate();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/profiles/{profileId}") //프로필 단건 조회
    public ResponseEntity<GetProfileResponse> getProfile(
            @PathVariable Long profileId
    ){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(profileService.findOne(profileId));
    }

    @GetMapping("/profiles") // 프로필 전체 조회
    public ResponseEntity<List<GetProfileResponse>> getProfiles(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(profileService.findAll());
    }

    @PutMapping("/myprofile") //내 프로필 수정
    public ResponseEntity<UpdateProfileResponse> updateProfile(
            @SessionAttribute(name = "loginProfile", required = false) SessionProfile sessionProfile,
            @RequestBody UpdateProfileRequest request
            ){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(profileService.updateProfile(sessionProfile.getId(),request));
    }

    @DeleteMapping("/myprofile") //회원탈퇴
    public ResponseEntity<Void> deleteProfile(
            @SessionAttribute(name = "loginProfile", required = false) SessionProfile sessionProfile
    ){
        profileService.deleteProfile(sessionProfile.getId());
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
