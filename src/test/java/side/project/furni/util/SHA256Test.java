package side.project.furni.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SHA256Test {

    @Test
    @DisplayName("sha256으로 비밀번호를 암호화한다")
    void encrypt() {
        // given
        String password = "test";

        // when
        String encrypt = SHA256.encrypt(password);

        // then
        assertThat("9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08").isEqualTo(encrypt);
    }

}