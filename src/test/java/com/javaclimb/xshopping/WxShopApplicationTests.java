package com.javaclimb.xshopping;

import cn.hutool.crypto.SecureUtil;
import org.junit.jupiter.api.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class WxShopApplicationTests {

    @Test
    void contextLoads() throws Exception{
        List<String> warning = new ArrayList<>();
        boolean overwrite = true;
        File configFile = new File("src/main/resources/generatorConfig.xml");
        ConfigurationParser cp = new ConfigurationParser(warning);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config,callback,warning);
        myBatisGenerator.generate(null);
    }

    @Test
    void md5(){
        String password = SecureUtil.md5("0000");
        System.out.println(password);
    }

}
