package org.github.config

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor
import org.mybatis.spring.annotation.MapperScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@MapperScan("org.github.**.mapper")
@Configuration
class MyBatisPlusConfig {
  @Bean
  fun paginationInterceptor() = PaginationInterceptor()
}
