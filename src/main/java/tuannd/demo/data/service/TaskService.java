package tuannd.demo.data.service;

import tuannd.demo.data.model.PaginableItemList;
import tuannd.demo.data.model.Task;

public interface TaskService {
    boolean insertTask(Task task);

    PaginableItemList<Task> findByOrderByIdDesc(Integer pageNo, Integer pageSize, String sortBy);
    Task findById(Integer id);
}
