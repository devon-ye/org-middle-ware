package org.mw.iam.server.service;

import org.mw.iam.server.entity.Policy;
import java.util.List;

/**
 * (Policy)表服务接口
 *
 * @author makejava
 * @since 2020-02-09 20:17:59
 */
public interface PolicyService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Policy queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Policy> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param policy 实例对象
     * @return 实例对象
     */
    Policy insert(Policy policy);

    /**
     * 修改数据
     *
     * @param policy 实例对象
     * @return 实例对象
     */
    Policy update(Policy policy);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}