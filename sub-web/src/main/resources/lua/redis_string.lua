--
-- Created by IntelliJ IDEA.
-- User: JYD_XL
-- Date: 2019-04-28
-- Time: 00:27
-- To change this template use File | Settings | File Templates.
--

local key = KEYS[1] --锁对应的key
local seconds = ARGV[1] --超时时间 秒
--尝试获取锁
local flag = redis.call('setnx', key, '') == 1
--获取到锁了
if flag then redis.call('expire', key, seconds) end
return tostring(flag);
