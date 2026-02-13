package kr.spartaclub.calender.controller;

import kr.spartaclub.calender.common.ApiResponse;
import kr.spartaclub.calender.dtocomment.*;
import kr.spartaclub.calender.dtoprofile.SessionProfile;
import kr.spartaclub.calender.exception.ErrorCode;
import kr.spartaclub.calender.exception.ProfileException;
import kr.spartaclub.calender.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/calenders/{calenderId}/comments") // 댓글생성
    public ResponseEntity<ApiResponse<CreateCommentResponse>> createComment(
            @PathVariable Long calenderId,
            @SessionAttribute(name = "loginProfile", required = false) SessionProfile sessionProfile,
            @RequestBody CreateCommentRequest request
    ){
        if (sessionProfile == null) {
            throw new ProfileException(ErrorCode.STATE_NOT_LOGIN);
        }
        commentService.save(calenderId,sessionProfile.id(),request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(CreateCommentResponse.of(request.getContent(),"댓글 생성 성공")));
    }

    @GetMapping("/comments") // 댓글 전체 조회
    public ResponseEntity<ApiResponse<List<GetCommentResponse>>> getComments(
            @RequestParam(required = false) Long profileId
    ){
        List<GetCommentResponse> dtoes = commentService.findAll(profileId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.successPrint(dtoes));
    }

    @PutMapping("/comments/{commentId}") //댓글 수정
    public ResponseEntity<ApiResponse<UpdateCommentResponse>> updateComment(
            @SessionAttribute(name = "loginProfile") SessionProfile sessionProfile,
            @PathVariable Long commentId,
            @RequestBody UpdateCommentRequest request
    ){
        if (sessionProfile == null) {
            throw new ProfileException(ErrorCode.STATE_NOT_LOGIN);
        }
        commentService.update(sessionProfile.id(),commentId,request);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(UpdateCommentResponse.of(commentId,"댓글 수정 완료")));
    }

    @DeleteMapping("/comments/{commentId}") //댓글 삭제
    public ResponseEntity<ApiResponse<DeleteCommentResponse>> deleteComment(
            @SessionAttribute(name = "loginProfile") SessionProfile sessionProfile,
            @PathVariable Long commentId
    ){
        if (sessionProfile == null) {
            throw new ProfileException(ErrorCode.STATE_NOT_LOGIN);
        }
        commentService.delete(commentId,sessionProfile.id());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(DeleteCommentResponse.of("댓글 삭제 성공")));
    }
}
