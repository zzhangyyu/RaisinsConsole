package com.yoler.raisinsconsole.dao.mapper;

import java.io.Serializable;

/**
 * zhangyu
 *
 * @param <E>  数据库实体entity
 * @param <PK> 主键
 */
public interface BaseMapper<E, PK extends Serializable> {

    public int insert(E entity);

    public int update(E entity);

    public int delete(PK pk);

    public E get(PK pk);

}
