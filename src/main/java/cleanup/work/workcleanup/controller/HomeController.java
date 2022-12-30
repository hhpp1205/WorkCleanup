package cleanup.work.workcleanup.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * TODO
 * 1. 차량 추가 시 보험 선택하면 보험명세 칸에 최소정보로 데이터 생성(보험명, 청구일자, 차종, 차량번호)
 * 2. 보험사 별로 테이블 구분
 * 3. 차량 관리 에 청구하기 버튼 추가, 버튼 클릭 시 보험 수리비 명세표 추가 페이지 이동
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
