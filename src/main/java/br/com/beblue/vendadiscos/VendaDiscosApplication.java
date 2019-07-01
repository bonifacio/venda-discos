package br.com.beblue.vendadiscos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@SpringBootApplication
@EnableAsync
public class VendaDiscosApplication {

    public static void main(String[] args) {
        SpringApplication.run(VendaDiscosApplication.class, args);
    }

    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(4);
        executor.setMaxPoolSize(4);
        executor.setQueueCapacity(100);
        executor.initialize();
        return executor;
    }

    @Bean
    public MessageSource messageResource() {
        ResourceBundleMessageSource messageBundleResrc = new ResourceBundleMessageSource();
        messageBundleResrc.setBasename("classpath:messages");
        messageBundleResrc.setDefaultEncoding("UTF-8");
        return messageBundleResrc;
    }

    @Bean
    public LocalValidatorFactoryBean getValidator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageResource());
        return bean;
    }
}
