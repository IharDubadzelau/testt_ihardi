package ru.javarush.tt_ihardu.testtask.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.javarush.tt_ihardu.testtask.entity.PartPC;

import java.util.List;

public interface  RepositoryPartPC extends CrudRepository<PartPC, Long> {

    @Query("select u from PartPC u where u.name like %?1")
    List<PartPC> findByNameLike(String name);
}

