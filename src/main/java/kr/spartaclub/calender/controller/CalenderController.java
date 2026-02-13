package kr.spartaclub.calender.controller;

import kr.spartaclub.calender.common.ApiResponse;
import kr.spartaclub.calender.dtocalender.*;
import kr.spartaclub.calender.dtoprofile.SessionProfile;
import kr.spartaclub.calender.exception.ErrorCode;
import kr.spartaclub.calender.exception.ProfileException;
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
    public ResponseEntity<ApiResponse<CreateCalenderResponse>> createCalender(
            @SessionAttribute(name = "loginProfile", required = false) SessionProfile sessionProfile,
            @RequestBody CreateCalenderRequest request
    ){
        if (sessionProfile == null) {
            throw new ProfileException(ErrorCode.STATE_NOT_LOGIN);
        }
        calenderService.save(request, sessionProfile.id());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(CreateCalenderResponse.of(request.getContent(),"일정 생성이 완료되었습니다.")));
    }

    @GetMapping("/{calenderId}") //일정 단건 조회
    public ResponseEntity<ApiResponse<GetSingleCalenderResponse>> getCalender(@PathVariable Long calenderId){
        GetSingleCalenderResponse response = calenderService.findOne(calenderId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.successPrint(response));
    }

    @GetMapping //일정 전체 조회
    public ResponseEntity<ApiResponse<List<GetCalenderResponse>>> getCalenders(
            @RequestParam(required = false) Long profileId
    ){
        List<GetCalenderResponse> dtos = calenderService.findAll(profileId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.successPrint(dtos));
    }

    @PutMapping("/{calenderId}") // 일정 단건 수정
    public  ResponseEntity<ApiResponse<UpdateCalenderResponse>> updateCalender(
            @PathVariable Long calenderId,
            @SessionAttribute(name = "loginProfile", required = false) SessionProfile sessionProfile,
            @RequestBody UpdateCalenderRequest request
    ){
        if (sessionProfile == null) {
            throw new ProfileException(ErrorCode.STATE_NOT_LOGIN);
        }
        calenderService.updateCalender(calenderId, sessionProfile.id(), request);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(UpdateCalenderResponse.of(calenderId,"일정 수정 성공")));
    }

    @DeleteMapping("/{calenderId}") //일정 단건 삭제
    public ResponseEntity<ApiResponse<DeleteCalenderResponse>> deleteCalender(
            @PathVariable Long calenderId,
            @SessionAttribute(name = "loginProfile", required = false) SessionProfile sessionProfile
    ){
        if (sessionProfile == null) {
            throw new ProfileException(ErrorCode.STATE_NOT_LOGIN);
        }
        calenderService.delete(calenderId, sessionProfile.id());
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(DeleteCalenderResponse.of("삭제 성공")));
    }
}
