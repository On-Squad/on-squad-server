package onsquad.onsquadserver.infra.mail.presentation;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import onsquad.onsquadserver.infra.mail.application.AuthMailService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RequiredArgsConstructor
@RequestMapping("/api/mail")
@RestController
public class AuthMailController {

    private final AuthMailService authMailService;

    @GetMapping(value = "/auth", params = {"email"})
    public void sendAuthCode(
            @RequestParam @Email String email
    ) {
        authMailService.sendAuthCodeToEmail(email);
    }

    @GetMapping(value = "/auth", params = {"email", "confirm"})
    public void verifyAuthCode(
            @RequestParam @Email String email,
            @RequestParam @NotEmpty String confirm
    ) {
        authMailService.verifyAuthCode(email, confirm);
    }
}
