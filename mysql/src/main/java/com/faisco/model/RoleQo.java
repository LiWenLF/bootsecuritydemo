package com.faisco.model;

/**
 * Created by admin on 2018/3/27.
 */
public class RoleQo extends PageQo{
    private Long id;
    private String name;

    public RoleQo() {
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

}