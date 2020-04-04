package org.mw.iam.server.service.impl;

import org.mw.iam.server.entity.Policy;
import org.mw.iam.server.dao.PolicyDao;
import org.mw.iam.server.service.PolicyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Policy)表服务实现类
 *
 * @author makejava
 * @since 2020-02-09 20:17:59
 */
@Service("policyService")
public class PolicyServiceImpl implements PolicyService {
    @Resource
    private PolicyDao policyDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Policy queryById(Integer id) {
        return this.policyDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Policy> queryAllByLimit(int offset, int limit) {
        return this.policyDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param policy 实例对象
     * @return 实例对象
     */
    @Override
    public Policy insert(Policy policy) {
        this.policyDao.insert(policy);
        return policy;
    }

    /**
     * 修改数据
     *
     * @param policy 实例对象
     * @return 实例对象
     */
    @Override
    public Policy update(Policy policy) {
        this.policyDao.update(policy);
        return this.queryById(policy.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.policyDao.deleteById(id) > 0;
    }
}