package kr.spartaclub.calender.service;

import kr.spartaclub.calender.dtocomment.*;
import kr.spartaclub.calender.entity.Calender;
import kr.spartaclub.calender.entity.Comment;
import kr.spartaclub.calender.entity.Profile;
import kr.spartaclub.calender.exception.ErrorCode;
import kr.spartaclub.calender.exception.ProfileException;
import kr.spartaclub.calender.repository.CalenderRepository;
import kr.spartaclub.calender.repository.CommentRepository;
import kr.spartaclub.calender.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final CalenderRepository calenderRepository;
    private final ProfileRepository profileRepository;

    @Transactional // 댓글 생성
    public CreateCommentResponse save(
            Long calenderId, Long profileId, CreateCommentRequest request
    ) {
        Profile profile = profileRepository.findById(profileId).orElseThrow(
                () -> new ProfileException(ErrorCode.PROFILE_NOT_FOUND)
        ); //세션 로그인해서 받아온건데 예외가 있나..?
        Calender calender = calenderRepository.findById(calenderId).orElseThrow(
                () -> new ProfileException(ErrorCode.CALENDER_NOT_FOUND)
        );
        Comment comment = new Comment(
                request.getContent(),
                calender,
                profile
        );
        return new CreateCommentResponse(commentRepository.save(comment));
    }

    @Transactional(readOnly = true) // 댓글 전체 조회
    public List<GetCommentResponse> findAll(Long profileId) {
        List<Comment> comments = commentRepository.findAll();
        List<GetCommentResponse> dtos = new ArrayList<>();

        if (profileId == null) {
            for (Comment comment : comments) {
                GetCommentResponse dto = new GetCommentResponse(comment);
                dtos.add(dto);
            }
        } else {
            for (Comment comment : comments) {
                if (comment.getProfile().getUserId().equals(profileId)) {
                    GetCommentResponse dto = new GetCommentResponse(comment);
                    dtos.add(dto);
                }
            }
        }
//        dtos.sort(Comparator.comparing(GetCommentResponse::getCreatedAt));
        return dtos;
    }

    @Transactional // 댓글 수정
    public UpdateCommentResponse update(Long profileId, Long commentId, UpdateCommentRequest request) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ProfileException(ErrorCode.CALENDER_NOT_FOUND)
        );

        if (!comment.getProfile().getUserId().equals(profileId)) {
            throw new ProfileException(ErrorCode.INVALID_PROFILE);
        }
        comment.updateComment(request.getContent());
        return new UpdateCommentResponse(comment);
    }

    @Transactional // 댓글 삭제
    public void delete(Long commentId, Long profileId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ProfileException(ErrorCode.CALENDER_NOT_FOUND)
        );
        if (!comment.getProfile().getUserId().equals(profileId)) {
            throw new ProfileException(ErrorCode.INVALID_PROFILE);
        }
        commentRepository.deleteById(commentId);

    }
}
