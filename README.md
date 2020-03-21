# LsDriver
打印SQL语句 参考 https://github.com/arthurblake/log4jdbc

只支持MYSQL   
只支持PreparedStatement形式

mysql5/mysql8

spring.datasource.driver-class-name=<b>com.github.yiyan1992.driver.LogDriver</b>

spring.datasource.url=<b>logSQL:</b>jdbc:mysql://localhost:3306/demo

<b>logging.level.com.github.yiyan1992=debug</b>

    <!-- https://mvnrepository.com/artifact/com.github.yiyan1992/LsDriver -->
    <dependency>
        <groupId>com.github.yiyan1992</groupId>
        <artifactId>LsDriver</artifactId>
        <version>1.2</version>
    </dependency>
 
 项目只用于开发环境使用
