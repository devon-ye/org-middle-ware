package org.mw.iam.server.service;

import org.mw.iam.server.entity.GroupInfo;
import java.util.List;

/**
 * (GroupInfo)表服务接口
 *
 * @author makejava
 * @since 2020-02-09 20:18:50
 */
public interface GroupInfoService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    GroupInfo queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<GroupInfo> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param groupInfo 实例对象
     * @return 实例对象
     */
    GroupInfo insert(GroupInfo groupInfo);

    /**
     * 修改数据
     *
     * @param groupInfo 实例对象
     * @return 实例对象
     */
    GroupInfo update(GroupInfo groupInfo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}