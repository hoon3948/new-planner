package kr.spartaclub.calender.controller;


import kr.spartaclub.calender.dtoprofile.*;
import kr.spartaclub.calender.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profiles")
public class ProfileController {
    private final ProfileService profileService;

    @PostMapping //프로필 생성
    public ResponseEntity<CreateProfileResponse> createProfile(@RequestBody CreateProfileRequest request){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(profileService.save(request));
    }

    @GetMapping("/{userId}") //프로필 단건 조회
    public ResponseEntity<GetProfileResponse> getProfile(@PathVariable Long userId){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(profileService.findOne(userId));
    }

    @GetMapping // 프로필 전체 조회
    public ResponseEntity<List<GetProfileResponse>> getProfiles(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(profileService.findAll());
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UpdateProfileResponse> updateProfile(
            @PathVariable Long userId,
            @RequestBody UpdateProfileRequest request
            ){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(profileService.updateProfile(userId,request));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteProfile(
            @PathVariable Long userId,
            @RequestBody DeleteProfileRequest request
    ){
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
