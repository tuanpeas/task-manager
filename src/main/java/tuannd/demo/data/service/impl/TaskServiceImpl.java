package tuannd.demo.data.service.impl;

import tuannd.demo.data.model.PaginableItemList;
import tuannd.demo.data.model.Task;
import tuannd.demo.data.repository.TaskRepository;
import tuannd.demo.data.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {
    private static final Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);
    @Autowired
    private TaskRepository taskRepository;

    public boolean insertTask(Task task) {
        try {
            taskRepository.save(task);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("insertTask : " + e.getMessage());
            return false;
        }
    }

    public PaginableItemList<Task> findByOrderByIdDesc(Integer pageNo, Integer pageSize, String sortBy) {
        try {

            PaginableItemList<Task> paginableItemList = new PaginableItemList<>();
            paginableItemList.setPageSize(pageSize);
            paginableItemList.setPageNumber(pageNo);
            paginableItemList.setPageSizeNotComplete(pageSize);
            paginableItemList.setPageNumberNotComplete(pageNo);
            PageRequest paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
            PageRequest pagingNotComplete = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
            Page<Task> pageComplete = taskRepository.findAll(1, paging);
            Page<Task> pageNotComplete = taskRepository.findAll(0, pagingNotComplete);
            paginableItemList.setTotalData(pageComplete.getTotalElements());
            paginableItemList.setListData(pageComplete.getContent());
            paginableItemList.setTotalDataNotComplete(pageNotComplete.getTotalElements());
            paginableItemList.setListNotComplete(pageNotComplete.getContent());
            return paginableItemList;

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("findByOrderByIdDesc : " + e.getMessage());
            return new PaginableItemList<Task>();
        }
    }

    public Task findById(Integer id) {
        try {

            Task task = taskRepository.findById(id);
            return task;

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("findById : " + e.getMessage());
            return new Task();
        }
    }

}
