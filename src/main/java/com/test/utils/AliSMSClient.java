package com.test.utils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.teaopenapi.models.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@Configuration
public class AliSMSClient {

    /**
     * 使用AK&SK初始化账号Client
     * @return Client
     * @throws Exception
     */
    @Bean
    public Client createClient() throws Exception {

        ExcelReader reader = ExcelUtil.getReader("E:\\Downloads\\sms.xls");
        List<Map<String, Object>> readAll = reader.readAll();
        System.out.println(readAll);
        System.out.println(ExcelUtil.getReader(FileUtil.file("E:\\Downloads\\sms.xls"), 0).readColumn(2,1).toString());
        String AccessKeyId = ExcelUtil.getReader(FileUtil.file("E:\\Downloads\\sms.xls"), 0).readColumn(2,1).iterator().next().toString();

        for (Map<String, Object> readMap:readAll
             ) {

            for (Map.Entry<String, Object> read:readMap.entrySet()
                 ) {
                System.out.println(read.getKey());
            }
        }
        // 获取第4个键值对
        Map.Entry<String, Object> FourthEntry = null;
        int count = 0;
        for (Map.Entry<String, Object> entry : readAll.iterator().next().entrySet()) {
            if (count == 3) {
                FourthEntry = entry;
                break;
            }
            count++;
        }
        System.out.println(FourthEntry.getValue());

        Map.Entry<String, Object> readTheKey = readAll.iterator().next().entrySet().iterator().next();
        System.out.println(readTheKey.getValue());
        String AccessKeySecret = FourthEntry.getValue().toString();

        System.out.println("AccessKeyId: " + AccessKeyId);
        System.out.println("AccessKeySecret: " + AccessKeySecret);

        Config config = new Config()
                // 您的 AccessKey ID
                .setAccessKeyId(AccessKeyId)
                // 您的 AccessKey Secret
                .setAccessKeySecret(AccessKeySecret);
        // 访问的域名
        config.endpoint = "dysmsapi.aliyuncs.com";
        Client client = new Client(config);
        return client;
    }

}
