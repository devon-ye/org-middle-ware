package org.mw.iam.server.dao;

import org.mw.iam.server.entity.Policy;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Policy)表数据库访问层
 *
 * @author makejava
 * @since 2020-02-09 20:17:59
 */
public interface PolicyDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Policy queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Policy> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param policy 实例对象
     * @return 对象列表
     */
    List<Policy> queryAll(Policy policy);

    /**
     * 新增数据
     *
     * @param policy 实例对象
     * @return 影响行数
     */
    int insert(Policy policy);

    /**
     * 修改数据
     *
     * @param policy 实例对象
     * @return 影响行数
     */
    int update(Policy policy);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}