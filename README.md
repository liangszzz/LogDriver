# LsDriver
打印SQL语句 参考 https://github.com/arthurblake/log4jdbc

支持 mysql5/mysql8

将 select * from table where a=? 转换为真实执行SQL

私钥丢失  无法更新  自行下载jar 或编译
 
1.git clone https://github.com/yiyan1992/LsDriver.git

2.mvn install -Pdev -Dgpg.skip

3.pom 引入		

    <dependency>
        <groupId>com.github.yiyan1992</groupId>
        <artifactId>LsDriver</artifactId>
        <version>1.2-SNAPSHOT</version>
    </dependency>
    
 4.spring.datasource.driver-class-name=<b>com.github.yiyan1992.driver.LogDriver</b>
   
 5.spring.datasource.url=<b>logSQL:</b>jdbc:mysql://localhost:3306/demo
   
 6.logging.level.com.github.yiyan1992=debug
   
   
   
   