package cleanup.work.workcleanup.controller;

import cleanup.work.workcleanup.component.Page;
import cleanup.work.workcleanup.controller.form.CarSearchCond;
import cleanup.work.workcleanup.repository.CarRepository;
import cleanup.work.workcleanup.repository.dto.CarDto;
import cleanup.work.workcleanup.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


/**
 * TODO
 * 1. 페이징, 정렬
 * 2. 보험사 별로 테이블 구분
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class HomeController {


    private final CarService carService;
    private final CarRepository carRepository;


    @GetMapping("/")
    public String list(CarSearchCond cond, Model model, Pageable pageable) {
        List<CarDto> cars = carService.getCarList(cond, pageable);
        Long totalCount = carRepository.getCount(cond);
        Page page = new Page(totalCount, pageable);


        model.addAttribute("cars", cars);
        model.addAttribute("cond", cond);
        model.addAttribute("page", page);
        return "page/car/car-list";
    }


}
