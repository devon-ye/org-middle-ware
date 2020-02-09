package org.mw.iam.server.service;

import org.mw.iam.server.entity.Action;
import java.util.List;

/**
 * (Action)表服务接口
 *
 * @author makejava
 * @since 2020-02-09 20:19:59
 */
public interface ActionService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Action queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Action> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param action 实例对象
     * @return 实例对象
     */
    Action insert(Action action);

    /**
     * 修改数据
     *
     * @param action 实例对象
     * @return 实例对象
     */
    Action update(Action action);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}