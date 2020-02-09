package org.mw.iam.server.dao;

import org.mw.iam.server.entity.Action;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Action)表数据库访问层
 *
 * @author makejava
 * @since 2020-02-09 20:19:59
 */
public interface ActionDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Action queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Action> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param action 实例对象
     * @return 对象列表
     */
    List<Action> queryAll(Action action);

    /**
     * 新增数据
     *
     * @param action 实例对象
     * @return 影响行数
     */
    int insert(Action action);

    /**
     * 修改数据
     *
     * @param action 实例对象
     * @return 影响行数
     */
    int update(Action action);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}