package de.maeddes.SpringTodoListSimpleCQRS;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

//@RepositoryRestResource(collectionResourceRel = "todoitems")
interface ToDoItemRepository extends PagingAndSortingRepository<ToDoItem,Integer> {

    List<ToDoItem> findAll();

}
