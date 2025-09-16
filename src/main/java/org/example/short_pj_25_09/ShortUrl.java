package org.example.short_pj_25_09;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ShortUrl {

    private long id;
    @Builder.Default // 기본값
    private LocalDateTime createDate = LocalDateTime.now();
    @Builder.Default // 기본값
    private LocalDateTime updateDate = LocalDateTime.now();
    private String body;
    private String url;
}
