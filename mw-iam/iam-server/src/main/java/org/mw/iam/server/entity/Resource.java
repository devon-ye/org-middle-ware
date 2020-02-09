package org.mw.iam.server.entity;

import java.io.Serializable;

/**
 * (Resource)实体类
 *
 * @author makejava
 * @since 2020-02-09 20:14:23
 */
public class Resource implements Serializable {
    private static final long serialVersionUID = 224178756058547623L;
    
    private Integer id;
    
    private String name;
    
    private Integer type;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

}