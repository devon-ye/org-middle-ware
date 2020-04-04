package org.mw.iam.server.controller;

import org.mw.iam.server.entity.Policy;
import org.mw.iam.server.service.PolicyService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Policy)表控制层
 *
 * @author makejava
 * @since 2020-02-09 20:17:59
 */
@RestController
@RequestMapping("policy")
public class PolicyController {
    /**
     * 服务对象
     */
    @Resource
    private PolicyService policyService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Policy selectOne(Integer id) {
        return this.policyService.queryById(id);
    }

}