package kr.spartaclub.calender.controller;

import kr.spartaclub.calender.dtocomment.*;
import kr.spartaclub.calender.dtoprofile.SessionProfile;
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
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(commentService.save(calenderId,sessionProfile.getId(),request));
    }

//    @GetMapping("/profile/{profileId}/comments") //내 댓글 조회
//    public ResponseEntity<List<GetCommentResponse>> getMyComment(
//            @SessionAttribute(name = "loginProfile", required = false) SessionProfile sessionProfile
//    ){
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(commentService.findMine(sessionProfile.getId()));
//    } // 전체조회랑 중복

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
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commentService
                        .update(sessionProfile.getId(),commentId,request)
                );
    }

    @DeleteMapping("/comments/{commentId}") //댓글 삭제
    public ResponseEntity<Void> deleteComment(
            @SessionAttribute(name = "loginProfile") SessionProfile sessionProfile,
            @PathVariable Long commentId
    ){
        commentService.delete(commentId,sessionProfile.getId());
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
