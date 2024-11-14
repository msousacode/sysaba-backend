package br.com.sysaba;

import br.com.sysaba.core.security.config.RsaKeyConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(RsaKeyConfigProperties.class)
@SpringBootApplication
public class SysabaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SysabaApplication.class, args);
    }

}
