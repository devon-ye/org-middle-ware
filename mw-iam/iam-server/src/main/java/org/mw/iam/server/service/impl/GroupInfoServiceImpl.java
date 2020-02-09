package org.mw.iam.server.service.impl;

import org.mw.iam.server.entity.GroupInfo;
import org.mw.iam.server.dao.GroupInfoDao;
import org.mw.iam.server.service.GroupInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (GroupInfo)表服务实现类
 *
 * @author makejava
 * @since 2020-02-09 20:18:50
 */
@Service("groupInfoService")
public class GroupInfoServiceImpl implements GroupInfoService {
    @Resource
    private GroupInfoDao groupInfoDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public GroupInfo queryById(Integer id) {
        return this.groupInfoDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<GroupInfo> queryAllByLimit(int offset, int limit) {
        return this.groupInfoDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param groupInfo 实例对象
     * @return 实例对象
     */
    @Override
    public GroupInfo insert(GroupInfo groupInfo) {
        this.groupInfoDao.insert(groupInfo);
        return groupInfo;
    }

    /**
     * 修改数据
     *
     * @param groupInfo 实例对象
     * @return 实例对象
     */
    @Override
    public GroupInfo update(GroupInfo groupInfo) {
        this.groupInfoDao.update(groupInfo);
        return this.queryById(groupInfo.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.groupInfoDao.deleteById(id) > 0;
    }
}