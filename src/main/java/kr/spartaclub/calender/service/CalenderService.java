package kr.spartaclub.calender.service;

import kr.spartaclub.calender.dtocalender.*;
import kr.spartaclub.calender.dtoprofile.SessionProfile;
import kr.spartaclub.calender.entity.Calender;
import kr.spartaclub.calender.entity.Profile;
import kr.spartaclub.calender.repository.CalenderRepository;
import kr.spartaclub.calender.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CalenderService {
    private final CalenderRepository calenderRepository;
    private final ProfileRepository profileRepository;

    @Transactional //일정 생성
    public CreateCalenderResponse save(CreateCalenderRequest request, Long profileId) {
        Profile profile = profileRepository.findById(profileId).orElseThrow(
                () -> new IllegalStateException("없는 사용자입니다.")
        );

        Calender calender = new Calender(
                request.getTitle(),
                request.getContent(),
                profile
        );
        Calender savedCalender = calenderRepository.save(calender);
        return new CreateCalenderResponse(savedCalender);
    }

    @Transactional(readOnly = true) //일정 단건 조회
    public GetCalenderResponse findOne(Long calenderId){
        Calender calender = calenderRepository.findById(calenderId).orElseThrow(
                () -> new IllegalStateException("존재하지않는 일정입니다.")
        );
        return new GetCalenderResponse(calender);
    }

    @Transactional(readOnly = true) //일정 전체 조회
    public List<GetCalenderResponse> findAll(Long profileId){
        List<Calender> calenders = calenderRepository.findAll();
        List<GetCalenderResponse> dtos = new ArrayList<>();

        if(profileId == null){
            for (Calender calender : calenders) {
                //if(calender.getProfile() != null) {
                    GetCalenderResponse dto = new GetCalenderResponse(calender);
                    dtos.add(dto);
                //}
           }
        }else {
            for (Calender calender : calenders) {
                if(calender.getProfile().getUserId().equals(profileId)){
                    GetCalenderResponse dto = new GetCalenderResponse(calender);
                    dtos.add(dto);
                }
            }
        }
        dtos.sort(Comparator.comparing(GetCalenderResponse::getModifiedAt).reversed());
        //수정일 순으로 역순 정렬
        return dtos;
    }

    @Transactional // 일정 단건 수정
    public UpdateCalenderResponse updateCalender(Long calenderId, Long profileId, UpdateCalenderRequest request) {
        Calender calender = calenderRepository.findById(calenderId).orElseThrow(
                () -> new IllegalStateException("존재하지않는 일정입니다.")
        );

        if(!calender.getProfile().getUserId().equals(profileId)){
            throw new IllegalArgumentException("타인의 일정은 수정할수없습니다.");
        }
//        if(!calender.getPassword().equals(request.getPassword())){
//            throw new IllegalArgumentException("비밀번호가 일치하지않습니다.");
//        }
        calender.updateCalender(request.getTitle(), request.getContent());
        return new UpdateCalenderResponse(calender);
    }

    @Transactional // 일정 단건 삭제
    public void delete(Long calenderId, Long profileId) {
        Calender calender = calenderRepository.findById(calenderId).orElseThrow(
                () -> new IllegalStateException("존재하지않는 일정입니다.")
        );

        if(!calender.getProfile().getUserId().equals(profileId)){
            throw new IllegalArgumentException("타인의 일정은 삭제할수없습니다.");
        }
//        if(!calender.getPassword().equals(password)){
//            throw new IllegalArgumentException("비밀번호가 일치하지않습니다.");
//        }
        calenderRepository.deleteById(calenderId);
    }
}
