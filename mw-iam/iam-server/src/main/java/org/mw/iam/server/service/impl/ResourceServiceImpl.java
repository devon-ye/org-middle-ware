package org.mw.iam.server.service.impl;

import org.mw.iam.server.entity.Resource;
import org.mw.iam.server.dao.ResourceDao;
import org.mw.iam.server.service.ResourceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Resource)表服务实现类
 *
 * @author makejava
 * @since 2020-02-09 20:14:23
 */
@Service("resourceService")
public class ResourceServiceImpl implements ResourceService {
    @Resource
    private ResourceDao resourceDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Resource queryById(Integer id) {
        return this.resourceDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Resource> queryAllByLimit(int offset, int limit) {
        return this.resourceDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param resource 实例对象
     * @return 实例对象
     */
    @Override
    public Resource insert(Resource resource) {
        this.resourceDao.insert(resource);
        return resource;
    }

    /**
     * 修改数据
     *
     * @param resource 实例对象
     * @return 实例对象
     */
    @Override
    public Resource update(Resource resource) {
        this.resourceDao.update(resource);
        return this.queryById(resource.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.resourceDao.deleteById(id) > 0;
    }
}