package org.example.short_pj_25_09;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
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

    @GetMapping("/s/{body}/**")
    @ResponseBody
    public ShortUrl add(@PathVariable String body, HttpServletRequest req) {
        String url = req.getRequestURI();

        if (req.getQueryString() != null) {
            url += "?" + req.getRequestURI();
        }

        String[] urlBits = url.split("/",4);

        System.out.println("Arrays.toString(urlBits): " + Arrays.toString(urlBits));

        url = urlBits[3];

        ShortUrl shortUrl = ShortUrl.builder()
                .id(++shortId)
                .body(body)
                .url(url)
                .build();

        shortUrls.add(shortUrl);

        return shortUrl;
    }

    @GetMapping("/g/{id}")
    public String go(@PathVariable long id) {

        ShortUrl shortUrl = shortUrls.stream()
                .filter(_shortUrl -> _shortUrl.getId() == id)
                .findFirst()
                .orElse(null);

        if (shortUrl == null) throw new RuntimeException("%d번 데이터 없음".formatted(id));

        shortUrl.increaseCount();

        return "redirect:" + shortUrl.getUrl();
    }

    @GetMapping("/all")
    @ResponseBody
    public List<ShortUrl> all() {
        return shortUrls;
    }
}
