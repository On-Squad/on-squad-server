package onsquad.onsquadserver.domain.auth.presentation;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import onsquad.onsquadserver.domain.auth.application.AuthService;
import onsquad.onsquadserver.domain.auth.presentation.dto.DuplicateMemberResponse;
import onsquad.onsquadserver.domain.auth.presentation.dto.MemberJoinRequest;
import onsquad.onsquadserver.global.common.response.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@Validated
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@RestController
public class AuthController {

    private final AuthService authService;

    @GetMapping("/check")
    public ResponseEntity<Response<DuplicateMemberResponse>> checkDuplicateNickname(
            @RequestParam @NotBlank String nickname
    ) {
        if (authService.checkNicknameDuplicate(nickname)) {
            return ResponseEntity.ok(Response.success(new DuplicateMemberResponse(true)));
        }
        return ResponseEntity.ok(Response.success(new DuplicateMemberResponse(false)));
    }

    @PostMapping("/join")
    public ResponseEntity<Void> joinMember(
            @RequestBody @Valid MemberJoinRequest memberJoinRequest
    ) {
        authService.joinMember(memberJoinRequest.toDto());
        return ResponseEntity.status(CREATED).build();
    }
}
