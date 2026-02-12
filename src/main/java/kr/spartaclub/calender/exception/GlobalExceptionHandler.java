package kr.spartaclub.calender.exception;

import jakarta.servlet.http.HttpServletRequest;
import kr.spartaclub.calender.common.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice // 👈 모든 컨트롤러의 예외를 감시하는 어노테이션
public class GlobalExceptionHandler {

    /**
     * 1. 비즈니스 로직 예외 처리 (우리가 예상한 에러)
     * MemberService 등에서 던진 MemberException을 잡습니다.
     */
    @ExceptionHandler(ProfileException.class)
    public ResponseEntity<ApiResponse<Void>> handleMemberException(ProfileException e, HttpServletRequest request) {
        log.warn("ProfileException : {}", e.getMessage());
        ErrorCode errorCode = e.getErrorCode();
        return ResponseEntity
                .status(errorCode.getStatus())
                .body(ApiResponse.fail(buildErrorResponse(
                        errorCode,
                        e.getMessage(),
                        request.getRequestURI()))
                );
    }

    /**
     * 2. 유효성 검사 실패 처리 (@Valid)
     * DTO의 @NotBlank, @Pattern 조건 등을 만족하지 못했을 때 발생합니다.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidationException(MethodArgumentNotValidException e, HttpServletRequest request) {
        log.warn("ValidationException : {}", e.getMessage());

        // 여러 에러 중 첫 번째 에러 메시지만 가져와서 보여줍니다.
        String errorMessage = e.getBindingResult().getFieldError().getDefaultMessage();

        return ResponseEntity
                .badRequest()
                .body(ApiResponse.fail(buildErrorResponse(
                        ErrorCode.INVALID_INPUT_VALUE,
                        errorMessage,
                        request.getRequestURI())
                ));
    }

    /**
     * 3. 예상치 못한 시스템 에러 (최후의 보루)
     * NullPointerException 등 우리가 예상하지 못한 에러가 발생했을 때 500 응답을 줍니다.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleException(Exception e, HttpServletRequest request) {
        log.error("Exception : {}", e.getMessage()); // 개발자는 로그로 원인을 확인하고

        // 클라이언트에게는 "서버 오류"라고만 알려줍니다 (보안상 상세 내용 숨김)
        return ResponseEntity
                .internalServerError()
                .body(ApiResponse.fail(buildErrorResponse(
                        ErrorCode.INTERNAL_SERVER_ERROR,
                        "알 수 없는 에러가 발생했습니다.",
                        request.getRequestURI()))
                );
    }

    // ErrorResponse 생성 편의 메서드
    private ErrorResponse buildErrorResponse(ErrorCode errorCode, String message, String path) {
        return ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(errorCode.getStatus().value())
                .error(errorCode.getStatus().name())
                .code(errorCode.getCode())
                .message(message)
                .path(path)
                .build();
    }
}
