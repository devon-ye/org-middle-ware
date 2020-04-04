package org.mw.iam.server.controller;

import org.mw.iam.server.entity.Resource;
import org.mw.iam.server.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * (Resource)表控制层
 *
 * @author makejava
 * @since 2020-02-09 20:14:23
 */
@RestController
@RequestMapping("resource")
public class ResourceController {
    /**
     * 服务对象
     */
    @Autowired
    private ResourceService resourceService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Resource selectOne(Integer id) {
        return this.resourceService.queryById(id);
    }

}