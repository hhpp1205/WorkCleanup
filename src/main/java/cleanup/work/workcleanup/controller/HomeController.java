package cleanup.work.workcleanup.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * CarInsuranceList 불러오기 구현
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
