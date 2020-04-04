package org.mw.iam.server.controller;

import org.mw.iam.server.entity.Action;
import org.mw.iam.server.service.ActionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Action)表控制层
 *
 * @author makejava
 * @since 2020-02-09 20:19:59
 */
@RestController
@RequestMapping("action")
public class ActionController {
    /**
     * 服务对象
     */
    @Resource
    private ActionService actionService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Action selectOne(Integer id) {
        return this.actionService.queryById(id);
    }

}