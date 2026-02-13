package kr.spartaclub.calender.controller;

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
    public ResponseEntity<CreateCommentResponse> createComment(
            @PathVariable Long calenderId,
            @SessionAttribute(name = "loginProfile", required = false) SessionProfile sessionProfile,
            @RequestBody CreateCommentRequest request
    ){
        if (sessionProfile == null) {
            throw new ProfileException(ErrorCode.STATE_NOT_LOGIN);
        }
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(commentService.save(calenderId,sessionProfile.id(),request));
    }

    @GetMapping("/comments") // 댓글 전체 조회
    public ResponseEntity<List<GetCommentResponse>> getComments(
            @RequestParam(required = false) Long profileId
    ){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commentService.findAll(profileId));
    }

    @PutMapping("/comments/{commentId}") //댓글 수정
    public ResponseEntity<UpdateCommentResponse> updateComment(
            @SessionAttribute(name = "loginProfile") SessionProfile sessionProfile,
            @PathVariable Long commentId,
            @RequestBody UpdateCommentRequest request
    ){
        if (sessionProfile == null) {
            throw new ProfileException(ErrorCode.STATE_NOT_LOGIN);
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commentService
                        .update(sessionProfile.id(),commentId,request)
                );
    }

    @DeleteMapping("/comments/{commentId}") //댓글 삭제
    public ResponseEntity<Void> deleteComment(
            @SessionAttribute(name = "loginProfile") SessionProfile sessionProfile,
            @PathVariable Long commentId
    ){
        if (sessionProfile == null) {
            throw new ProfileException(ErrorCode.STATE_NOT_LOGIN);
        }
        commentService.delete(commentId,sessionProfile.id());
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
