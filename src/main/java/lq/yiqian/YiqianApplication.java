package lq.yiqian;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author yiqian
 * <p>
 * 开启jasypt加密
 */
@SpringBootApplication
@MapperScan("lq.yiqian.mapper")
// jsp
@ServletComponentScan
// jasypt加密
@EnableEncryptableProperties
// Spring定时任务
@EnableScheduling
// jetcache
@EnableMethodCache(basePackages = "lq.yiqian")
@EnableCreateCacheAnnotation
public class YiqianApplication {

    public static void main(String[] args) {
        SpringApplication.run(YiqianApplication.class, args);
    }

}
