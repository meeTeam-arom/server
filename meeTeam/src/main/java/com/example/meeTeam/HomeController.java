package com.example.meeTeam;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/testPage")
    public String home() {
        return "배포 확인용 페이지";
    }
}
