package org.github.ops

import org.github.spring.redis.ListRedisScript
import org.github.spring.redis.LongRedisScript
import org.github.spring.redis.StringRedisOps
import org.github.spring.redis.StringRedisScript
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.util.Collections.*
import javax.annotation.Resource

@ExtendWith(SpringExtension::class)
@SpringBootTest
class RedisTests {
  /** log. */
  private val log = RedisTests::class.log

  @Autowired
  private lateinit var redisOps: StringRedisOps
  @Resource
  private lateinit var boolScript: StringRedisScript
  @Resource
  private lateinit var voidScript: StringRedisScript
  @Resource
  private lateinit var longScript: LongRedisScript
  @Resource
  private lateinit var listScript: ListRedisScript

  @Test
  fun testBool() {
    val key = "redis:bool"
    val flag = requireNotNull(redisOps.execute(boolScript, singletonList(key), 60)).toBoolean()
    log.info { "testBool: $flag" }
  }

  @Test
  fun testLong() {
    val key = "redis:long"
    val long = requireNotNull(redisOps.execute(longScript, singletonList(key), 60))
    log.info { "testLong: $long" }
  }

  @Test
  fun testVoid() {
    val key = "redis:void"
    val void = redisOps.execute(voidScript, singletonList(key), 60)
    log.info { "testVoid: $void" }
  }

  @Test
  fun testList() {
    val key = "redis:list"
    val list: List<String?> = requireNotNull(redisOps.execute(listScript, singletonList(key), 60))
    log.info { "testList: $list" }
  }
}
