package onsquad.onsquadserver.domain.auth.exception;

public class AuthException extends RuntimeException {

    public AuthException(String message) {
        super(message);
    }

    public static class NonMailAuthenticatedUser extends AuthException {
        public NonMailAuthenticatedUser() {
            super("메일인증이 완료되지 않았습니다.");
        }
    }

    public static class AlreadyExistsUser extends AuthException {
        public AlreadyExistsUser() {
            super("이미 존재하는 사용자입니다.");
        }
    }
}
