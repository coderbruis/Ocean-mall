package tk.mybatis.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author LuoHaiYang
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
