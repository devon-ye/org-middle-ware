package org.mw.iam.server.dao;

import org.mw.iam.server.entity.Resource;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Resource)表数据库访问层
 *
 * @author makejava
 * @since 2020-02-09 20:14:23
 */
public interface ResourceDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Resource queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Resource> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param resource 实例对象
     * @return 对象列表
     */
    List<Resource> queryAll(Resource resource);

    /**
     * 新增数据
     *
     * @param resource 实例对象
     * @return 影响行数
     */
    int insert(Resource resource);

    /**
     * 修改数据
     *
     * @param resource 实例对象
     * @return 影响行数
     */
    int update(Resource resource);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}