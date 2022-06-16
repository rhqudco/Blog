package bear.blog.controller;

import bear.blog.domain.TestDomain;
import bear.blog.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
    @Autowired
    TestService testService;

    @RequestMapping("/")
    public String Index() {
        return "index";
    }

    @RequestMapping("join")
    public String join(TestDomain domain) {
        Long savedId = testService.join(domain);
        return "index";
    }
}