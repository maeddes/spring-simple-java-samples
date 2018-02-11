package de.maeddes.SpringTodoListSimpleCQRS;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

//@RepositoryRestResource(collectionResourceRel = "todoitems")
interface ToDoItemRepository extends PagingAndSortingRepository<ToDoItem,Integer> {

    List<ToDoItem> findAll();

}
