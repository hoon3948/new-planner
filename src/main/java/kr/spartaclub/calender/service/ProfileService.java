package kr.spartaclub.calender.service;

import jakarta.validation.Valid;
import kr.spartaclub.calender.dtoprofile.*;
import kr.spartaclub.calender.entity.Profile;
import kr.spartaclub.calender.exception.ErrorCode;
import kr.spartaclub.calender.exception.ProfileException;
import kr.spartaclub.calender.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;

    @Transactional // 프로필 생성
    public void save(CreateProfileRequest request) {
        if(profileRepository.existsByEmail(request.getEmail())){
            throw new ProfileException(ErrorCode.DUPLICATE_EMAIL);
        }// 이메일 중복 확인


        Profile profile = new Profile(
                request.getName(),
                request.getEmail(),
                request.getPassword()
        );
        Profile savedProfile = profileRepository.save(profile);
    }

    @Transactional(readOnly = true) // 프로필 단건 조회
    public GetProfileResponse findOne(Long userId) {
        Profile profile = profileRepository.findById(userId).orElseThrow(
                () -> new ProfileException(ErrorCode.PROFILE_NOT_FOUND)
        );
        return new GetProfileResponse(profile);
    }

    @Transactional(readOnly = true) //프로필 전체 조회
    public List<GetProfileResponse> findAll() {
        List<Profile> profiles = profileRepository.findAll();
        List<GetProfileResponse> dtos = new ArrayList<>();

        for (Profile profile : profiles) {
            GetProfileResponse dto = new GetProfileResponse(profile);
            dtos.add(dto);
        }
        dtos.sort(Comparator.comparing(GetProfileResponse::getName));
        // 이름순으로 정렬
        return dtos;
    }

    @Transactional //프로필 단건 수정
    public UpdateProfileResponse updateProfile(Long userId, UpdateProfileRequest request) {
        Profile profile = profileRepository.findById(userId).orElseThrow(
                () -> new ProfileException(ErrorCode.PROFILE_NOT_FOUND)
        );
//        if(!profile.getPassword().equals(request.getPassword())){
//            throw new IllegalArgumentException("비밀번호가 일치하지않습니다");
//        }
        profile.updateProfile(
                request.getName(),
                request.getEmail(),
                request.getPassword()
        );
        return new UpdateProfileResponse(profile);
    }

    @Transactional // 프로필 단건 삭제
    public void deleteProfile(Long userId){
        Profile profile = profileRepository.findById(userId).orElseThrow(
                () -> new ProfileException(ErrorCode.PROFILE_NOT_FOUND)
        );
        profileRepository.deleteById(userId);
    }

    @Transactional(readOnly = true) // 로그인
    public SessionProfile login(@Valid LoginRequest request) {
        Profile profile = profileRepository.findByEmail(request.getEmail()).orElseThrow(
                () -> new ProfileException(ErrorCode.PROFILE_NOT_FOUND)
        );

        if (!profile.getPassword().equals(request.getPassword())){
            throw new ProfileException(ErrorCode.INVALID_PASSWORD);
        }
        return new SessionProfile(
                profile.getUserId(),
                profile.getName(),
                profile.getEmail(),
                profile.getPassword()
        );
    }
}
