package tuannd.demo.controller;

import tuannd.demo.data.model.Task;
import tuannd.demo.data.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

    @Autowired
    private TaskService taskService;


}
