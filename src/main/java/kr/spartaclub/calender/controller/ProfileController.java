package kr.spartaclub.calender.controller;


import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import kr.spartaclub.calender.common.ApiResponse;
import kr.spartaclub.calender.dtoprofile.*;
import kr.spartaclub.calender.exception.ErrorCode;
import kr.spartaclub.calender.exception.ProfileException;
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
    public ResponseEntity<ApiResponse<CreateProfileResponse>> createProfile(@Valid @RequestBody CreateProfileRequest request){
        profileService.save(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(CreateProfileResponse.of(request.getName(), "회원가입이 완료되었습니다.")));
    }

    @PostMapping("/login") // 로그인
    public ResponseEntity<ApiResponse<LoginResponse>> login(@Valid @RequestBody LoginRequest request, HttpSession session) {
        SessionProfile sessionProfile = profileService.login(request);
        session.setAttribute("loginProfile", sessionProfile);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(LoginResponse.of("로그인 성공!!")));
    }

    @PostMapping("/logout") //로그아웃
    public ResponseEntity<ApiResponse<LoginResponse>> logout(
            @SessionAttribute(name = "loginProfile", required = false) SessionProfile sessionProfile,
            HttpSession session
    ) {
        if (sessionProfile == null) {
            throw new ProfileException(ErrorCode.STATE_NOT_LOGIN);
        }
        session.invalidate();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ApiResponse.success(LoginResponse.of("로그아웃 완료")));
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
    public ResponseEntity<ApiResponse<UpdateProfileResponse>> updateProfile(
            @SessionAttribute(name = "loginProfile", required = false) SessionProfile sessionProfile,
            @RequestBody UpdateProfileRequest request
            ){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(
                        UpdateProfileResponse.of(request.getName(), "내 프로필 수정이 완료되었습니다."))
                );
    }

    @DeleteMapping("/myprofile") //회원탈퇴
    public ResponseEntity<Void> deleteProfile(
            @SessionAttribute(name = "loginProfile", required = false) SessionProfile sessionProfile
    ){
        profileService.deleteProfile(sessionProfile.id());
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
