package cn.taroco.common.ribbon;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Feign统一配置
 *
 * @author liuht
 * @date 2018/9/18 14:04
 */
@Configuration
public class TarocoFeignAutoConfigure {

    /**
     * Feign 日志级别
     */
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public XlabelFeignHeaderInterceptor xlabelFeignHeaderInterceptor() {
        return new XlabelFeignHeaderInterceptor();
    }
}
