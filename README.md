# LsDriver
打印SQL语句 参考 https://github.com/arthurblake/log4jdbc

只支持PreparedStatement

log-mysql5/log-mysql8

spring.datasource.driver-class-name=com.github.yiyan1992.driver.MyDriver

spring.datasource.url=log-mysql8:jdbc:mysql://localhost:3306/demo

    <!-- https://mvnrepository.com/artifact/com.github.yiyan1992/LsDriver -->
    <dependency>
        <groupId>com.github.yiyan1992</groupId>
        <artifactId>LsDriver</artifactId>
        <version>1.1</version>
    </dependency>
