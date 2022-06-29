import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.fill.Column;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;

/**
 * @author xwl
 * @version 1.0
 * @since 2022/6/9 14:11
 */
public class GenerateCode {
    @Test
    public void testGenerateSystem() {
        basicInfo("fc", "system");
    }


    public void basicInfo(String prefixName, String name) {
        FastAutoGenerator.create("jdbc:mysql://10.10.11.17:3307/" + prefixName + "_" + name + "?useSSL=false&rewriteBatchedStatements=true&characterEncoding=utf-8",
                        "root",
                        "root")
                .globalConfig(builder -> {
                    builder.author("fusw") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .commentDate("yyyy-MM-dd")// 覆盖已生成文件
                            .outputDir(getHandlePath(prefixName, name)); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.fc") // 设置父包名
                            .moduleName(name + ".auto"); // 设置父包模块名

                })
                .strategyConfig(conf -> conf
                        .serviceBuilder().formatServiceFileName("%sAutoService").formatServiceImplFileName("%sAutoServiceImpl").build()
                        .mapperBuilder().formatMapperFileName("%sAutoMapper").formatXmlFileName("%sAutoMapper")
                        .entityBuilder()
                        .enableLombok()
                        .logicDeleteColumnName("is_deleted")
                        .idType(IdType.ASSIGN_ID)
                        .addTableFills(Arrays.asList(new Column("org_name", FieldFill.INSERT), new Column("org_code", FieldFill.INSERT)))
                        .versionColumnName("optimistic_version"))
                .templateConfig(p -> p.disable(TemplateType.CONTROLLER, TemplateType.XML))
                .execute();
    }

    public String getHandlePath(String prefixName, String name) {
        String separator = File.separator;
        String root = System.getProperty("user.dir").substring(0, System.getProperty("user.dir").lastIndexOf(separator));
        String projectPath = "src.main.java".replace(".", separator);
        String projectName = prefixName + "-" + name;
        return root + separator + projectName + separator + projectPath;
    }
}
