package onsquad.onsquadserver.infra.mail.application;

import java.util.Random;

public interface RandomCodeGenerator {

    String CODE_BOOK = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    default String generateAuthCode() {
        StringBuilder stringBuilder = new StringBuilder();
        mapCodeBookToIndex(stringBuilder);
        return stringBuilder.toString();
    }

    private void mapCodeBookToIndex(StringBuilder stringBuilder) {
        Random random = new Random();
        random.ints(0, CODE_BOOK.length())
                .limit(8)
                .forEach(integer -> stringBuilder.append(CODE_BOOK.charAt(integer)));
    }
}
