package ${package.Service}

import ${superServiceClassPackage}
import ${package.Entity}.${entity}

/**
 * <p>
 * ${table.comment!} 服务类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if kotlin>
interface ${table.serviceName}: ${superServiceClass}<${entity}>
</#if>
