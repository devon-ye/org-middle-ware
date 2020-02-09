package org.mw.iam.server.controller;

import org.mw.iam.server.entity.GroupInfo;
import org.mw.iam.server.service.GroupInfoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (GroupInfo)表控制层
 *
 * @author makejava
 * @since 2020-02-09 20:18:50
 */
@RestController
@RequestMapping("groupInfo")
public class GroupInfoController {
    /**
     * 服务对象
     */
    @Resource
    private GroupInfoService groupInfoService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public GroupInfo selectOne(Integer id) {
        return this.groupInfoService.queryById(id);
    }

}