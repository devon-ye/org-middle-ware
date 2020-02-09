package org.mw.iam.server.service;

import org.mw.iam.server.entity.Resource;
import java.util.List;

/**
 * (Resource)表服务接口
 *
 * @author makejava
 * @since 2020-02-09 20:14:23
 */
public interface ResourceService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Resource queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Resource> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param resource 实例对象
     * @return 实例对象
     */
    Resource insert(Resource resource);

    /**
     * 修改数据
     *
     * @param resource 实例对象
     * @return 实例对象
     */
    Resource update(Resource resource);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}