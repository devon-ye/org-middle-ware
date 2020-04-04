package org.mw.iam.server.service.impl;

import org.mw.iam.server.entity.Action;
import org.mw.iam.server.dao.ActionDao;
import org.mw.iam.server.service.ActionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Action)表服务实现类
 *
 * @author makejava
 * @since 2020-02-09 20:19:59
 */
@Service("actionService")
public class ActionServiceImpl implements ActionService {
    @Resource
    private ActionDao actionDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Action queryById(Integer id) {
        return this.actionDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Action> queryAllByLimit(int offset, int limit) {
        return this.actionDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param action 实例对象
     * @return 实例对象
     */
    @Override
    public Action insert(Action action) {
        this.actionDao.insert(action);
        return action;
    }

    /**
     * 修改数据
     *
     * @param action 实例对象
     * @return 实例对象
     */
    @Override
    public Action update(Action action) {
        this.actionDao.update(action);
        return this.queryById(action.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.actionDao.deleteById(id) > 0;
    }
}