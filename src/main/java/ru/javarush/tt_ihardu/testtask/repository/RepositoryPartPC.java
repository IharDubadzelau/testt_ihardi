package ru.javarush.tt_ihardu.testtask.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.javarush.tt_ihardu.testtask.entity.PartPC;

public interface  RepositoryPartPC extends CrudRepository<PartPC, Long> {

    Page<PartPC> findAll(Pageable pageable);

    @Query("select u from PartPC u where u.name like %?1")
    Page<PartPC> findByNameLike(String name, Pageable pageable);
}

