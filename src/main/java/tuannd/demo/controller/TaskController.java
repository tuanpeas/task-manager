package tuannd.demo.controller;

import tuannd.demo.data.model.PaginableItemList;
import tuannd.demo.data.model.Task;
import tuannd.demo.data.model.User;
import tuannd.demo.data.service.TaskService;
import tuannd.demo.data.service.UserService;
import tuannd.demo.util.Constanst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/task")
public class TaskController {
    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);
    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;
    @GetMapping("/list")
    public String taskList (
            @RequestParam(value = "pageNumber",required=false) Integer pageNumber,
            @RequestParam(value = "pageNumberNotComplete",required=false) Integer pageNumberNotComplete,
            Model model){
        if(pageNumber == null) {
            pageNumber = 0;
        }
        if(pageNumberNotComplete == null) {
            pageNumberNotComplete = 0;
        }
        PaginableItemList<Task> paginableItemList = taskService.findByOrderByIdDesc(pageNumber, Constanst.PAGE_SIZE,"id") ;
        PaginableItemList<Task> paginableItemListNotComplete = taskService.findByOrderByIdDesc(pageNumberNotComplete, Constanst.PAGE_SIZE,"id") ;
        int totalPages = 0;
        int totalPagesNotComplete = 0;

        if(paginableItemList.getTotalData() % Constanst.PAGE_SIZE == 0) {
            totalPages = (int)(paginableItemList.getTotalData() / Constanst.PAGE_SIZE);
        } else {
            totalPages = (int)(paginableItemList.getTotalData() / Constanst.PAGE_SIZE) + 1;
        }
        if(paginableItemListNotComplete.getTotalDataNotComplete() % Constanst.PAGE_SIZE == 0) {
            totalPagesNotComplete = (int)(paginableItemListNotComplete.getTotalDataNotComplete() / Constanst.PAGE_SIZE);
        } else {
            totalPagesNotComplete = (int)(paginableItemListNotComplete.getTotalDataNotComplete() / Constanst.PAGE_SIZE) + 1;
        }
        List<Task> postList = paginableItemList.getListData();
        List<Task> postListNotComplete = paginableItemList.getListNotComplete();
        model.addAttribute("taskList",postList);
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("page",paginableItemList);
        model.addAttribute("taskListNotComplete",postListNotComplete);
        model.addAttribute("totalPagesNotComplete",totalPagesNotComplete);
        model.addAttribute("pageNotComplete",paginableItemListNotComplete);
        return "task/task";
    }

    @GetMapping("/add")
    public String taskAdd(Model model){
        List<User> listUser = userService.findAll();
        model.addAttribute("task",new Task());
        model.addAttribute("listUser", listUser);
        return "task/add-task";
    }

    @PostMapping(value = "/add")
    public String taskAddPostmapping(@ModelAttribute("task") Task task, Model model){

        boolean insert =  taskService.insertTask(task);
        return "redirect:/task/list";
    }

    @GetMapping("/detail/{id}")
    public String taskDetail(@PathVariable("id") int id, Model model){
        Task task = taskService.findById(id);

        model.addAttribute("task",task);

        return "task/detail-task";
    }

    @GetMapping("/edit/{id}")
    public String taskEdit(@PathVariable("id") int id, Model model){
        Task task = taskService.findById(id);

        model.addAttribute("task",task);

        return "task/edit-task";
    }

    @PostMapping(value = "/edit")
    public String postEditpostmapping(@ModelAttribute("task") Task task, Model model) {

        if (task != null) {
            Task oldTask = taskService.findById(task.getId());
            boolean update = taskService.insertTask(task);
        }

        return "redirect:/task/list";
    }

    @RequestMapping(value = "/destroy/{id}")
    public String postDeletepostmapping(@ModelAttribute("task") Task task, Model model) {

        if (task != null) {
            Task oldTask = taskService.findById(task.getId());
            oldTask.setDelete_flag(1);
            boolean update = taskService.insertTask(oldTask);
        }

        return "redirect:/task/list";
    }

    @RequestMapping(value = "/complete/{id}")
    public String postCompletepostmapping(@ModelAttribute("task") Task task, Model model) {

        if (task != null) {
            Task oldTask = taskService.findById(task.getId());
            oldTask.setComplete_flag(1);
            boolean update = taskService.insertTask(oldTask);
        }

        return "redirect:/task/list";
    }

    @RequestMapping(value = "/not-complete/{id}")
    public String postNotComplete(@ModelAttribute("task") Task task, Model model) {

        if (task != null) {
            Task oldTask = taskService.findById(task.getId());
            oldTask.setComplete_flag(0);
            boolean update = taskService.insertTask(oldTask);
        }

        return "redirect:/task/list";
    }
}
