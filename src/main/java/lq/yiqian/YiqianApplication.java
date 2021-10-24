package lq.yiqian;

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
@ServletComponentScan
@EnableEncryptableProperties
@EnableScheduling
public class YiqianApplication {

    public static void main(String[] args) {
        SpringApplication.run(YiqianApplication.class, args);
    }

}
