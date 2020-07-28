package kr.com.younsiktech.springboot.base.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor // final 필드 포함된 생성자 만듬, final 없으면 생성 안함.
public class WebResponseDto {
    private final String name;
    private final int amount;
}
