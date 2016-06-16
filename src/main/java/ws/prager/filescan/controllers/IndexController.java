package ws.prager.filescan.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    final static Logger logger = LoggerFactory.getLogger(IndexController.class);

    @CrossOrigin
    @RequestMapping("/")
    String index() {
        return "index";
    }
}
