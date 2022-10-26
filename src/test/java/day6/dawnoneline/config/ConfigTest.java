package day6.dawnoneline.config;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
@Slf4j
public class ConfigTest {

    @Autowired
    private SecurityConfig securityConfig;

    @Test
    @DisplayName("비밀번호 암호화 테스트")
    void passwordEncodeTest() {
        BCryptPasswordEncoder encoder = securityConfig.passwordEncoder();

        String rawPassword = "myPassword";
        String encodePassword = encoder.encode(rawPassword);
        log.debug("give before : {}", rawPassword);
        log.debug("give after : {}", encodePassword);

        assertTrue(encoder.matches(rawPassword, encodePassword));
    }

}
