package ru.javarush.tt_ihardu.testtask.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import ru.javarush.tt_ihardu.testtask.entity.PartPC;

public interface  RepositoryPartPC extends CrudRepository<PartPC, Long> {
    Page<PartPC> findAll(Pageable pageable);
    Page<PartPC> findByNameContaining(String name, Pageable pageable);
    Page<PartPC> findByNeeds(boolean needs, Pageable pageable);
    Page<PartPC> findByNameContainingAndNeeds(String name, boolean needs, Pageable pageable);
}

