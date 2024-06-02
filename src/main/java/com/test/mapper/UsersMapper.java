package com.test.mapper;

import com.test.bean.po.Users;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
* @author 23194
* @description 针对表【users】的数据库操作Mapper
* @createDate 2024-04-09 23:38:47
* @Entity com.test.bean.po.Users
*/

@Mapper
@Repository
public interface UsersMapper extends BaseMapper<Users> {

}




