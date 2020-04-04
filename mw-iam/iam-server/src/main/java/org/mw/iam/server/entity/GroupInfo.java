package org.mw.iam.server.entity;

import java.io.Serializable;

/**
 * (GroupInfo)实体类
 *
 * @author makejava
 * @since 2020-02-09 20:18:50
 */
public class GroupInfo implements Serializable {
    private static final long serialVersionUID = 876803338625979231L;
    
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