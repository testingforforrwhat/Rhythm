package com.test.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.test.bean.bo.MusicCategoriesAddBo;
import com.test.bean.bo.MusicCategoriesUpdateBo;
import com.test.bean.po.MusicCategories;
import com.test.service.MusicCategoriesService;
import com.test.mapper.MusicCategoriesMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
* @author 23194
* @description 针对表【music_categories】的数据库操作Service实现
* @createDate 2024-04-03 12:32:12
*/
@Service
public class MusicCategoriesServiceImpl extends ServiceImpl<MusicCategoriesMapper, MusicCategories>
    implements MusicCategoriesService{


    // 依赖项
    @Resource
    private MusicCategoriesMapper musicCategoriesMapper;


    /**
     * 根据分类ID，从数据库查询该分类数据
     *
     * @param categoryId
     * @return
     */
    @Override
    public MusicCategories selectByCategoryId(Integer categoryId) {
        return musicCategoriesMapper.selectById( categoryId );
    }

    /**
     * 查询当前所有音乐分类
     *
     * @return
     */
    @Override
    public List<MusicCategories> listAll() {
        return musicCategoriesMapper.selectList( null );
    }

    /**
     * 添加音乐分类
     *
     * @param musicCategoriesAddBo
     * @return
     */
    @Override
    public boolean add(MusicCategoriesAddBo musicCategoriesAddBo) {
        // 创建用于添加的MusicCategories对象
        MusicCategories musicCategories = new MusicCategories();
//        // 借组属性拷贝工具将dto中的同名的属性值 赋给 musicCategories
//        BeanUtil.copyProperties(musicCategoriesAddBo,musicCategories);
//        System.out.println( musicCategoriesAddBo );
//        System.out.println( musicCategories );
        musicCategoriesAddBo.setCategoryId( musicCategoriesAddBo.getCategoryId() );
        musicCategoriesAddBo.setCategoryName( musicCategoriesAddBo.getCategoryName() );
        System.out.println( musicCategoriesAddBo.getCreatedAt() );

        Date now = new Date();
        musicCategoriesAddBo.setCreatedAt( now );
        musicCategoriesAddBo.setUpdatedAt( now );
        BeanUtil.copyProperties(musicCategoriesAddBo,musicCategories);
        System.out.println( musicCategories );
        return musicCategoriesMapper.insert( musicCategories ) > 0 ? true : false;
    }

    /**
     * 编辑音乐分类
     *
     * @param musicCategoriesUpdateBo
     * @return
     */
    @Override
    public boolean updateMusicCategories(MusicCategoriesUpdateBo musicCategoriesUpdateBo) {

        System.out.println( musicCategoriesUpdateBo );
        // 创建用于更新的UpdateWapper
        UpdateWrapper<MusicCategories> updateWrapper = new UpdateWrapper<>();
        // 设置需要更新的字段 和更新条件
        Date now = new Date();
        updateWrapper.set("category_name",musicCategoriesUpdateBo.getCategoryName())
                .set("updated_at",now)
                .eq("category_id",musicCategoriesUpdateBo.getCategoryId());
        System.out.println( "updateWrapper:" + updateWrapper );
        int update = musicCategoriesMapper.update(new MusicCategories(), updateWrapper);
        System.out.println( "musicCategoriesUpdateBo:" + musicCategoriesUpdateBo );
        // 因为这个方法需要将更新过后的数据返回 所以可以再次查询数据库
        MusicCategories musicCategories = musicCategoriesMapper.selectById(musicCategoriesUpdateBo.getCategoryId());
        return update > 0 ? true : false;

    }

    /**
     * 删除音乐分类
     *
     * @param categoryId
     * @return
     */
    @Override
    public boolean deleteMusicCategory(Integer categoryId) {
        return musicCategoriesMapper.deleteById( categoryId ) > 0 ? true : false;
    }
}




