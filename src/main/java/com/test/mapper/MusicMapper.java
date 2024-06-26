package com.test.mapper;

import com.test.bean.bo.MusicSearchBo;
import com.test.bean.po.Music;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author 23194
* @description 针对表【music】的数据库操作Mapper
* @createDate 2024-04-03 12:32:12
* @Entity com.test.bean.po.Music
*/
@Mapper
public interface MusicMapper extends BaseMapper<Music> {

    List<Music> selectAll();

    List<Music> listByBo(MusicSearchBo musicSearchBo);
}




