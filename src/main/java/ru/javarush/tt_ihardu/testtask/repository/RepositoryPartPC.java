package ru.javarush.tt_ihardu.testtask.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.javarush.tt_ihardu.testtask.entity.PartPC;

import java.util.List;

public interface  RepositoryPartPC extends PagingAndSortingRepository<PartPC, Long> {
    List<PartPC> findByNeedsTrue();
    List<PartPC> findAll();
    Page<PartPC> findAll(Pageable pageable);
    Page<PartPC> findByNameContaining(String name, Pageable pageable);
    Page<PartPC> findByNeeds(boolean needs, Pageable pageable);
    Page<PartPC> findByNameContainingAndNeeds(String name, boolean needs, Pageable pageable);
}

