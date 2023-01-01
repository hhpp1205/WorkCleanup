package cleanup.work.workcleanup.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * TODO
 * 1. 페이징, 정렬
 * 2. 보험사 별로 테이블 구분
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class HomeController {


    @GetMapping
    public String list() {
        return "page/index";
    }


}
