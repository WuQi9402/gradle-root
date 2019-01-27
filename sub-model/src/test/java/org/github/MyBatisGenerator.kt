package org.github

import com.baomidou.mybatisplus.annotation.IdType.UUID
import com.baomidou.mybatisplus.generator.AutoGenerator
import com.baomidou.mybatisplus.generator.config.DataSourceConfig
import com.baomidou.mybatisplus.generator.config.GlobalConfig
import com.baomidou.mybatisplus.generator.config.PackageConfig
import com.baomidou.mybatisplus.generator.config.StrategyConfig
import com.baomidou.mybatisplus.generator.config.TemplateConfig
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy.underline_to_camel
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine
import java.lang.System.getProperty

fun main() {
  val generator = AutoGenerator().apply { templateEngine = FreemarkerTemplateEngine() }

  GlobalConfig().apply {
    val path = getProperty("user.dir")!!
    outputDir = "$path/sub-model/src/main/java"
    isFileOverride = true
    isOpen = false
    author = "JYD_XL"
    idType = UUID
    entityName = "%sEntity"
    mapperName = "I%sMapper"
    serviceName = "I%sService"
    serviceImplName = "%sServiceImpl"
    generator.globalConfig = this
  }

  DataSourceConfig().apply {
    url = "jdbc:mysql://localhost:3357/security?useSSL=false"
    driverName = "com.mysql.jdbc.Driver"
    username = "root"
    password = "l"
    generator.dataSource = this
  }

  PackageConfig().apply {
    parent = "org.github"
    moduleName = "base"
    generator.packageInfo = this
  }

  StrategyConfig().apply {
    naming = underline_to_camel
    isEntityLombokModel = true
    isEntityTableFieldAnnotationEnable = true
    versionFieldName = "version"
    logicDeleteFieldName = "deleted"
    setInclude(".+")
    generator.strategy = this
  }

  TemplateConfig().apply {
    controller = null
    xml = null
    service = "service.java"
    serviceImpl = "serviceImpl.java"
    mapper = "mapper.java"
    setEntity("entity.java")
    generator.template = this
  }

  generator.execute()
}
