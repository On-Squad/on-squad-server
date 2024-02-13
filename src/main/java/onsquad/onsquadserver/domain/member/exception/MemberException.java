package onsquad.onsquadserver.domain.member.exception;


public class MemberException extends RuntimeException {

    public MemberException(String message) {
        super(message);
    }

    public static class InvalidEmailFormat extends MemberException {
        public InvalidEmailFormat() {
            super("이메일 형식과 일치하지 않습니다.");
        }
    }

    public static class NotFound extends MemberException {
        public NotFound() {
            super("회원을 찾을 수 없습니다.");
        }
    }
}
