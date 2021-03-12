package com.slz.redis.starter;

import cn.hutool.core.convert.Convert;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import redis.clients.jedis.*;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class RedisOperator {

    // 循环限制次数
    public static final int LOCK_LOOP_LIMIT = 1_000_000;

    @Resource
    protected RedisTemplate<String, Object> redisTemplate;

    /**
     * 释放分布式锁的脚本
     */
    private static final String UNLOCK_LUA = "if redis.call(\"get\",KEYS[1]) == ARGV[1] then return redis.call(\"del\",KEYS[1]) else return 0 end";

    /**
     * 执行lua脚本
     */
    public void execute(String luaScript, List<String> keys) {
        redisTemplate.execute(RedisScript.of(luaScript), keys);
    }

    /**
     * 指定缓存失效时间
     *
     * @param key  键
     * @param time 时间(秒)
     * @return
     */
    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            log.error("RedisOperator expire error:",e);
            return false;
        }
    }

    /**
     * 根据key 获取过期时间
     *
     * @param key 键 不能为null
     * @return 时间(秒) 返回0代表为永久有效
     */
    public long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 判断key是否存在
     *
     * @param key 键
     * @return true 存在 false不存在
     */
    @SuppressWarnings("unchecked")
    public boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            log.error("RedisOperator hasKey... error:",e);
        }
        return false;
    }

    /**
     * 查找匹配的key
     * @return
     */
    public Set<String> keys(String pattern) {
        if (StringUtils.isBlank(pattern) || pattern.trim().equals("*")) {
            throw new IllegalArgumentException("危险操作，不允许调用keys *");
        }
        return redisTemplate.keys(pattern);
    }

    /**
     * 删除缓存
     *
     * @param key 可以传一个值 或多个
     */
    @SuppressWarnings("unchecked")
    public void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

    /**
     * 普通缓存获取
     *
     * @param key 键
     * @return 值
     */
    public String get(String key) {
        return key == null ? null : (String) redisTemplate.opsForValue().get(key);
    }

    /**
     * 普通缓存放入
     *
     * @param key   键
     * @param value 值
     * @return true成功 false失败
     */
    public boolean set(String key, String value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            log.error("RedisOperator set... error:",e);
            return false;
        }
    }

    /**
     * 普通缓存放入并设置时间
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return true成功 false 失败
     */
    public boolean set(String key, String value, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            log.error("RedisOperator set... error:",e);
            return false;
        }
    }

    /**
     * 递增
     *
     * @param key   键
     * @param delta 要增加几(大于0)
     * @return
     */
    public long incr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        Long increment = redisTemplate.opsForValue().increment(key, delta);
        if (increment != null) {
            return increment;
        }else {
            throw new RuntimeException("incr key is not exist");
        }
    }

    /**
     * 递减
     *
     * @param key   键
     * @param delta 要减少几(小于0)
     * @return
     */
    public long decr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递减因子必须大于0");
        }
        Long increment = redisTemplate.opsForValue().increment(key, -delta);
        if (increment != null) {
            return increment;
        }else {
            throw new RuntimeException("decr key is not exist");
        }
    }


    //===============================list=================================

    /**
     * 获取list缓存的内容
     *
     * @param key   键
     * @param start 开始
     * @param end   结束  0 到 -1代表所有值
     * @return
     */
    public List<Object> lGet(String key, long start, long end) {
        try {
            return redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            log.error("RedisOperator lGet... error:",e);
            return Collections.emptyList();
        }
    }


    /**
     * 获取list缓存的长度
     *
     * @param key 键
     * @return
     */
    public long lGetListSize(String key) {
        try {
            return redisTemplate.opsForList().size(key);
        } catch (Exception e) {
            log.error("RedisOperator lGetListSize... error:",e);
            return 0;
        }
    }


    /**
     * 通过索引 获取list中的值
     *
     * @param key   键
     * @param index 索引  index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
     * @return
     */
    public Object lGetIndex(String key, long index) {
        try {
            return redisTemplate.opsForList().index(key, index);
        } catch (Exception e) {
            log.error("RedisOperator lGetIndex... error:",e);
            return null;
        }
    }


    /**
     * 根据索引修改list中的某条数据
     *
     * @param key   键
     * @param index 索引
     * @param value 值
     * @return
     */
    public boolean lUpdateIndex(String key, long index, Object value) {
        try {
            redisTemplate.opsForList().set(key, index, value);
            return true;
        } catch (Exception e) {
            log.error("RedisOperator lUpdateIndex... error:",e);
            return false;
        }
    }

    /**
     * 移除N个值为value
     *
     * @param key   键
     * @param count 移除多少个
     * @param value 值
     * @return 移除的个数
     */
    public long lRemove(String key, long count, Object value) {
        try {
            return redisTemplate.opsForList().remove(key, count, value);
        } catch (Exception e) {
            log.error("RedisOperator lRemove... error:",e);
            return 0;
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @return
     */
    public boolean lSet(String key, Object value) {
        try {
            redisTemplate.opsForList().leftPush(key, value);
            return true;
        } catch (Exception e) {
            log.error("RedisOperator lSet... error:",e);
            return false;
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return
     */
    public boolean lSet(String key, Object value, long time) {
        try {
            redisTemplate.opsForList().leftPush(key, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            log.error("RedisOperator lSet... error:",e);
            return false;
        }
    }

    /**
     * 移除list最右侧一个元素
     *
     * @param key 键
     * @return
     */
    public boolean rPop(String key) {
        try {
            redisTemplate.opsForList().rightPop(key);
            return true;
        } catch (Exception e) {
            log.error("RedisOperator rPop... error:",e);
            return false;
        }
    }

    // ============================set=============================

    /**
     * 根据key获取Set中的所有值
     *
     * @param key 键
     * @return
     */
    public Set<Object> sGet(String key) {
        try {
            return redisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            log.error("RedisOperator sGet... error:",e);
            return Collections.emptySet();
        }
    }

    /**
     * 根据value从一个set中查询,是否存在
     *
     * @param key   键
     * @param value 值
     * @return true 存在 false不存在
     */
    public boolean sHasKey(String key, Object value) {
        try {
//            return redisTemplate.opsForSet().isMember(key, value);
            return redisTemplate.opsForSet().members(key).contains(value);
        } catch (Exception e) {
            log.error("RedisOperator sHasKey... error:",e);
            return false;
        }
    }

    /**
     * 将数据放入set缓存
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public long sSet(String key, Object... values) {
        try {
            return redisTemplate.opsForSet().add(key, values);
        } catch (Exception e) {
            log.error("RedisOperator sSet... error:",e);
            return 0;
        }
    }

    /**
     * 将set数据放入缓存
     *
     * @param key    键
     * @param time   时间(秒)
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public long sSetAndTime(String key, long time, Object... values) {
        try {
            Long count = redisTemplate.opsForSet().add(key, values);
            if (time > 0) {
                expire(key, time);
            }
            return count;
        } catch (Exception e) {
            log.error("RedisOperator sSetAndTime... error:",e);
            return 0;
        }
    }

    /**
     * 获取set缓存的长度
     *
     * @param key 键
     * @return
     */
    public long sGetSetSize(String key) {
        try {
            return redisTemplate.opsForSet().size(key);
        } catch (Exception e) {
            log.error("RedisOperator sGetSetSize... error:",e);
            return 0;
        }
    }

    /**
     * 移除值为value的
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 移除的个数
     */
    public long setRemove(String key, Object... values) {
        try {
            return redisTemplate.opsForSet().remove(key, values);
        } catch (Exception e) {
            log.error("RedisOperator setRemove... error:",e);
            return 0;
        }
    }

    //================================Map=================================

    /**
     * HashGet
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return 值
     */
    public Object hget(String key, String item) {
        return redisTemplate.opsForHash().get(key, item);
    }

    /**
     * 获取hashKey对应的所有键值
     *
     * @param key 键
     * @return 对应的多个键值
     */
    public Map<Object, Object> hmget(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 获取hashKey对应的所有键
     *
     * @param key 键
     * @return 对应的多个键
     */
    public Set<Object> hkeys(String key) {
        return redisTemplate.opsForHash().keys(key);
    }

    /**
     * HashSet
     *
     * @param key 键
     * @param map 对应多个键值
     * @return true 成功 false 失败
     */
    public boolean hmset(String key, Map<String, Object> map) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            log.error("RedisOperator hmset... error:",e);
            return false;
        }
    }

    /**
     * HashSet 并设置时间
     *
     * @param key  键
     * @param map  对应多个键值
     * @param time 时间(秒)
     * @return true成功 false失败
     */
    public boolean hmset(String key, Map<String, Object> map, long time) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            log.error("RedisOperator hmset... error:",e);
            return false;
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @return true 成功 false失败
     */
    public boolean hset(String key, String item, Object value) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            return true;
        } catch (Exception e) {
            log.error("RedisOperator hset... error:",e);
            return false;
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @param time  时间(秒)  注意:如果已存在的hash表有时间,这里将会替换原有的时间
     * @return true 成功 false失败
     */
    public boolean hset(String key, String item, Object value, long time) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            log.error("RedisOperator hset... error:",e);
            return false;
        }
    }

    /**
     * 删除hash表中的值
     *
     * @param key  键 不能为null
     * @param item 项 可以使多个 不能为null
     */
    public void hdel(String key, Object... item) {
        redisTemplate.opsForHash().delete(key, item);
    }

    /**
     * 判断hash表中是否有该项的值
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return true 存在 false不存在
     */
    public boolean hHasKey(String key, String item) {
        return redisTemplate.opsForHash().hasKey(key, item);
    }


    public Boolean setNX(String key, String value, Long expireTime) {
        return redisTemplate.opsForValue().setIfAbsent(key,value,expireTime,TimeUnit.MILLISECONDS);
    }

    public void publishMsg(String channel, String message) {
        if (channel != null && message != null && channel.length() > 0 && message.length() > 0) {
            redisTemplate.convertAndSend(channel, message);
        }
    }

    /**
     * 尝试加锁
     *
     * @param key    前缀
     * @param value  唯一值
     * @param expire 单位毫秒
     * @return
     */
    public boolean tryLock(String key, String value, long expire, TimeUnit unit) {
        try {
            return redisTemplate.opsForValue().setIfAbsent(key, value, expire, unit);
        } catch (Exception e) {
            log.error("set redis Lock occured an exception,redisTemplate={},key={},value={}",redisTemplate,key,value,e);
        }
        return false;
    }

    /**
     * 尝试获取锁 直到等待时间 , 默认间隔 10ms
     * @param key
     * @return
     */
    public boolean tryLock(String key) {
        return this.tryLock(key,Thread.currentThread().getName(), 300000,0);
    }

    /**
     * 尝试获取锁 直到等待时间 , 默认间隔 10ms
     * @param key
     * @param value
     * @param lockTime
     * @return
     */
    public boolean tryLock(String key, String value, long lockTime) {
        return this.tryLock(key,value,lockTime,0);
    }

    /**
     * 尝试获取锁 直到等待时间
     *
     * @param key       前缀
     * @param value     唯一值
     * @param lockTime  占用锁的时间（单位毫秒）
     * @param sleepTime 抢占锁的间隔时间
     * @return
     */
    public boolean tryLock(String key, String value, long lockTime, long sleepTime) {
        // 至少间隔10ms
        if(sleepTime < 10){
            sleepTime = 10L;
        }
        // 循环获取锁
        try {
            int loopCount = 1;
            while(loopCount < LOCK_LOOP_LIMIT) {
                if (redisTemplate.opsForValue().setIfAbsent(key, value, lockTime, TimeUnit.MILLISECONDS)) {
                    return true;
                }
                loopCount ++;
                Thread.sleep(sleepTime);
            }
            log.warn("tryLock fail >>>  loopCount = {}", loopCount);
        } catch (Exception e) {
            log.error("set redis Lock occured an exception,key={},value={}",key,value,e);
        }
        return false;
    }

    /**
     * 释放锁
     */
    public boolean unLock(String key) {
        return eval(UNLOCK_LUA, key, Thread.currentThread().getName())>0;
    }

    public Long eval(String script, String key, Object value){
        List<String> keys = new ArrayList<>();
        List<String> args = new ArrayList<>();
        keys.add(key);
        args.add(Convert.toStr(value));
        RedisCallback<Long> callback = connection -> {
            Object nativeConnection = connection.getNativeConnection();
            if (nativeConnection instanceof JedisCluster) {
                // 集群模式
                return (Long) ((JedisCluster) nativeConnection).eval(script, keys, args);
            } else if (nativeConnection instanceof Jedis) {
                // 单机模式
                return (Long) ((Jedis) nativeConnection).eval(script, keys, args);
            } else {
                log.error("redis lock model match error ");
            }
            return -1L;
        };
        return redisTemplate.execute(callback);
    }

    public Set<String> scan(String key) {
        return redisTemplate.execute((RedisCallback<Set<String>>) connection -> {
            Set<String> keys = Sets.newHashSet();
            JedisCommands commands = (JedisCommands) connection.getNativeConnection();
            MultiKeyCommands multiKeyCommands = (MultiKeyCommands) commands;
            ScanParams scanParams = new ScanParams();
            scanParams.match("*" + key + "*");
            scanParams.count(1000);
            ScanResult<String> scan = multiKeyCommands.scan("0", scanParams);
            while (null != scan.getStringCursor()) {
                keys.addAll(scan.getResult());
                if (!StringUtils.equals("0", scan.getStringCursor())) {
                    scan = multiKeyCommands.scan(scan.getStringCursor(), scanParams);
                } else {
                	break;
                }
            }
            return keys;
        });
    }



}
