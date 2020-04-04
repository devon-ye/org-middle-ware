package org.mw.iam.server.entity;

import java.io.Serializable;

/**
 * (Policy)实体类
 *
 * @author makejava
 * @since 2020-02-09 20:17:59
 */
public class Policy implements Serializable {
    private static final long serialVersionUID = 813856613725567526L;
    
    private Integer id;
    
    private String name;


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

}