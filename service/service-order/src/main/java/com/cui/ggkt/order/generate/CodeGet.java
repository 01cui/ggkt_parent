package com.cui.ggkt.order.generate;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.sql.SQLException;
import java.util.Collections;

public class CodeGet {
    /**
     * 执行 run
     */
    public static void main(String[] args) throws SQLException {
        String property = System.getProperty("user.dir");
        System.out.println("property = " + property);
        FastAutoGenerator.create("jdbc:mysql://192.168.42.129:3306/glkt_order?useSSL=false&characterEncoding=utf-8&serverTimezone=Asia/Shanghai",
                        "root",
                        "root")
                .globalConfig(builder -> {
                    builder.author("崔令雨") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .outputDir("D:/ggkt/ggkt_parent/service/service-order/src/main/java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.cui.ggkt") // 设置父包名
                            .moduleName("order") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "D:/ggkt/ggkt_parent/service/service-order/src/main/resources/mapper")); // 设置mapperXml生成路径
                })

                .strategyConfig(builder -> {
                    builder.addInclude("order_detail",
                                    "order_info",
                                    "payment_info") // 设置需要生成的表名
                            .addTablePrefix("")// 设置过滤表前缀
                            .entityBuilder()
                            .columnNaming(NamingStrategy.underline_to_camel)
                            .enableLombok()
                            .enableChainModel()
                            .enableTableFieldAnnotation()
                            .enableRemoveIsPrefix()
                            .logicDeleteColumnName("is_deleted")
                            .logicDeletePropertyName("Deleted")
                            .idType(IdType.ASSIGN_ID);
                    builder.controllerBuilder()
                            .enableHyphenStyle()
                            .enableRestStyle();
                    builder.serviceBuilder()
                            .formatServiceFileName("%sService")
                            .formatServiceImplFileName("%sServiceImp");
                    builder.mapperBuilder()
                            .enableMapperAnnotation()
                            .enableBaseResultMap();
                })
                .execute();
    }
}