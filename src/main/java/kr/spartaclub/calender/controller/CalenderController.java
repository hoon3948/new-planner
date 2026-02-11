package kr.spartaclub.calender.controller;

import kr.spartaclub.calender.dtocalender.*;
import kr.spartaclub.calender.dtoprofile.SessionProfile;
import kr.spartaclub.calender.service.CalenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/calenders")
public class CalenderController {
    private final CalenderService calenderService;

    @PostMapping //일정생성
    public ResponseEntity<CreateCalenderResponse> createCalender(
            @SessionAttribute(name = "loginProfile", required = false) SessionProfile sessionProfile,
            @RequestBody CreateCalenderRequest request
    ){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(calenderService.save(request, sessionProfile.getId()));
    }

    @GetMapping("/{calenderId}") //일정 단건 조회
    public ResponseEntity<GetCalenderResponse> getCalender(@PathVariable Long calenderId){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(calenderService.findOne(calenderId));
    }

    @GetMapping //일정 전체 조회
    public ResponseEntity<List<GetCalenderResponse>> getCalenders(@RequestParam(required = false) Long profileId){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(calenderService.findAll(profileId));
    }

    @PutMapping("/{calenderId}") // 일정 단건 수정
    public  ResponseEntity<UpdateCalenderResponse> updateCalender(
            @PathVariable Long calenderId,
            @SessionAttribute(name = "loginProfile", required = false) SessionProfile sessionProfile,
            @RequestBody UpdateCalenderRequest request
    ){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(calenderService.updateCalender(calenderId, sessionProfile.getId(), request));
    }

    @DeleteMapping("/{calenderId}") //일정 단건 삭제
    public ResponseEntity<Void> deleteCalender(
            @PathVariable Long calenderId,
            @SessionAttribute(name = "loginProfile", required = false) SessionProfile sessionProfile
    ){
        calenderService.delete(calenderId, sessionProfile.getId());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
