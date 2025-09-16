package org.example.short_pj_25_09;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ShortUrlController {

    private List<ShortUrl> shortUrls = new ArrayList<>();
    private long shortId = 0;

    @GetMapping("/add")
    @ResponseBody
    public ShortUrl add(String body, String url){

        ShortUrl shortUrl = ShortUrl.builder()
                .id(++shortId)
                .body(body)
                .url(url)
                .build();

        shortUrls.add(shortUrl);

        return shortUrl;
    }
}
