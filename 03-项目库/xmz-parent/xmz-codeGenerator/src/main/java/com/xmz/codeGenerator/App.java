package com.xmz.codeGenerator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.io.IOException;
import java.util.Scanner;

/**
 * 代码生成器
 * 直接运行！请勿动！！！
 */
public class App {

    protected static String author = "";
    protected static String dir = "";
    protected static String tables = "";
    protected static String packages = "";
    protected static String username = "";
    protected static String password = "";

    /**
     * @param args args
     * @throws IOException IO异常
     */
    public static void main(String[] args) throws IOException {
        System.out.println("============[代码生成器]===========");
        do {
            System.out.println("-------------[参数配置]------------");
            System.out.print("*输出目录[dir]（src/main/java）：");
            dir = new Scanner(System.in).next();
            System.out.print("*模块名[packages]（src/main/java）：com.xmz.");
            packages = "com.xmz."+new Scanner(System.in).next();
            System.out.print("*表名[tables]（多个表以','分隔）：");
            tables = new Scanner(System.in).next();
            System.out.print("创建者[author]：（CodeGenerator）");
            author = new Scanner(System.in).next();
            System.out.print("用户名[username]：");
            username = new Scanner(System.in).next();
            System.out.print("密码[password]：无密码（$NULL$）");
            password = new Scanner(System.in).next();
            password = password.equals("$NULL$")?"":password;
            System.out.println("---------------------------------");
            System.out.printf("你所设置的参数为：\n" +
                    "[dir]: %s,\n" +
                    "[packages]: %s,\n" +
                    "[tables]: %s,\n" +
                    "[author]: %s,\n" +
                    "[username]: %s,\n" +
                    "[password]: %s,\n" +
                    "", dir, packages, tables, author,username,password);
            System.out.print("OK ? (Y)");
        }while (!new Scanner(System.in).next().equals("Y"));
        System.out.println("=============[开始生成]===========-");
        run();
        System.out.println("=============[生成完毕]===========-");
    }

    protected static void run(){
        // 创建构造器
        AutoGenerator autoGenerator = new AutoGenerator();

        // 全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir(dir); // 输出位置
        globalConfig.setAuthor(author); // 使用者
        globalConfig.setOpen(false); // 是否打开
        globalConfig.setFileOverride(false); // 是否覆盖
        // ---- 分割线 ----
        globalConfig.setServiceName("I%sService"); // Service类命名
        globalConfig.setServiceImplName("%sServiceImpl"); // Service实现类命名
        globalConfig.setIdType(IdType.ASSIGN_UUID); // 主键策略
        globalConfig.setDateType(DateType.ONLY_DATE); // 日期策略
        globalConfig.setSwagger2(true); // 开启Swagger2模式
        autoGenerator.setGlobalConfig(globalConfig); // 将全局配置填充到构造器

        // 数据库配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver"); // 启动类
        dataSourceConfig. // 配置URL
                setUrl("jdbc:mysql://localhost:3306/xmz_student?useSSL=false&serverTimezone=Asia/Shanghai");
        dataSourceConfig.setUsername(username); // 连接用户
        dataSourceConfig.setPassword(password); // 密码
        dataSourceConfig.setDbType(DbType.MYSQL); // 数据库类型、
        autoGenerator.setDataSource(dataSourceConfig);

        // 包配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent(packages);
        packageConfig.setMapper("mapper");
        packageConfig.setEntity("entity.pojo");
        packageConfig.setXml("mapper");
        autoGenerator.setPackageInfo(packageConfig);

        // 策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setInclude(tables.split(",")); // 表导入
        strategyConfig.setNaming(NamingStrategy.underline_to_camel); // 数据表映射实体命名策略
        strategyConfig.setTablePrefix(packageConfig.getModuleName()+"_"); // 表前缀策略
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel); // 表字段映射属性命名策略
        strategyConfig.setEntityLombokModel(true); // 启用Lombok
        strategyConfig.setRestControllerStyle(true); // 启用Restful风格
        strategyConfig.setControllerMappingHyphenStyle(true); // URL驼峰转连字符

        autoGenerator.setStrategy(strategyConfig);

        autoGenerator.execute();

    }
}
