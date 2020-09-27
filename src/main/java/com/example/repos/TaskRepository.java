package com.example.repos;
import org.springframework.data.repository.CrudRepository;
import com.example.domain.Task;

public interface TaskRepository extends CrudRepository<Task, Long>
{

}
