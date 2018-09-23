package ru.javarush.tt_ihardu.testtask.entity;

import javax.persistence.*;

@Entity
@Table(name = "partspc_ihardi")
public class PartPC {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private boolean needs;
    private Long count;

    public PartPC() {
    }

    public PartPC(String name, boolean needs, Long count) {
        this.name = name;
        this.needs = needs;
        this.count = count;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isNeeds() {
        return needs;
    }

    public void setNeeds(boolean needs) {
        this.needs = needs;
    }



    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
