package kr.com.younsiktech.springboot.base.web.dto;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class WebResponseDtoTest {

    @Test
    public void webResponseDtoTest() {
        String name = "test";
        int amount = 10;

        WebResponseDto webResponseDto = new WebResponseDto(name, amount);


        assertThat(webResponseDto.getName()).isEqualTo(name);
        assertThat(webResponseDto.getAmount()).isEqualTo(amount);

    }
}