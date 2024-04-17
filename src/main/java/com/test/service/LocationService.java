package com.test.service;

import java.io.IOException;

/**
 * 定位业务逻辑层接口
 * */
public interface LocationService {

    /**
     * ip地址归属丁定位
     * @return 定位的地区编号
     * */
    Integer ipLocation() throws IOException;

}
