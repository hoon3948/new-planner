package kr.spartaclub.calender.service;

import kr.spartaclub.calender.dtoprofile.*;
import kr.spartaclub.calender.entity.Profile;
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
    public CreateProfileResponse save(CreateProfileRequest request) {
        Profile profile = new Profile(
                request.getName(),
                request.getEmail(),
                request.getPassword()
        );
        Profile savedProfile = profileRepository.save(profile);
        return new CreateProfileResponse(savedProfile);
    }

    @Transactional(readOnly = true) // 프로필 단건 조회
    public GetProfileResponse findOne(Long userId) {
        Profile profile = profileRepository.findById(userId).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 사용자입니다.")
        );
        return new GetProfileResponse(profile);
    }

    @Transactional(readOnly = true) //프로필 전체 조회
    public List<GetProfileResponse> findAll() {
        List<Profile> profiles = profileRepository.findAll();
        List<GetProfileResponse> dtos = new ArrayList<>();

        for (Profile profile : profiles) {
            GetProfileResponse dto = new GetProfileResponse();
            dtos.add(dto);
        }
        dtos.sort(Comparator.comparing(GetProfileResponse::getName));
        // 이름순으로 정렬
        return dtos;
    }

    @Transactional
    public UpdateProfileResponse updateProfile(Long userId, UpdateProfileRequest request) {
        Profile profile = profileRepository.findById(userId).orElseThrow(
                () -> new IllegalStateException("존재하지않는 사용자입니다.")
        );
        if(!profile.getPassword().equals(request.getPassword())){
            throw new IllegalArgumentException("비밀번호가 일치하지않습니다"):
        }
        profile.updateProfile(
                request.getName(),
                request.getEmail(),
                request.getPassword()
        );
        return new UpdateProfileResponse(profile);
    }

    @Transactional
    public void deleteProfile(Long userId, String password){
        Profile profile = profileRepository.findById(userId).orElseThrow(
                () -> new IllegalStateException("존재하지않는 사용자입니다.")
        );
        if(!profile.getPassword().equals(password)){
            throw new IllegalArgumentException("비밀번호가 일치하지않습니다.");
        }
        profileRepository.deleteById(userId);
    }
}
