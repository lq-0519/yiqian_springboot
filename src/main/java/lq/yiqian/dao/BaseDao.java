package lq.yiqian.dao;

import java.util.List;

/**
 * dao层基类
 *
 * @author liqian477
 * @date 2021/10/24 13:49
 */
public interface BaseDao<T, K> {
    /**
     * 查询 数量
     */
    int queryForCount(K bean);

    /**
     * 根据主键删除
     */
    int delete(Long id);

    /**
     * 插入一条数据
     */
    int insert(T bean);

    /**
     * 查询列表
     */
    List<T> queryForList(K bean);

    /**
     * 根据主键id查询对象
     */
    T queryForObject(Long id);

    /**
     * 更新记录
     */
    int update(T bean);
}
