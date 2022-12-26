package cleanup.work.workcleanup.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * TODO
 * 1. 견인 Y/N 말고 얼마인지 표시(없으면 공란)
 * 2. 사진이랑 똑같이 만들기
 * 3. 차량 추가 시 보험 선택하면 보험명세 칸에 최소정보로 데이터 생성(보험명, 청구일자, 차종, 차량번호)
 * 4. 보험사 별로 테이블 구분
 * 5. 차량 관리 에 청구하기 버튼 추가, 버튼 클릭 시 보험 수리비 명세표 추가 페이지 이동
 *  ex) 보험 2개면 입력칸도 2개(한 페이지에서 모두 이루어 질 수 있도록)
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
