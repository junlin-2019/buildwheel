package com.jlwj;

import redis.clients.jedis.Jedis;

/**
 * @author hehang on 2019-06-25
 * @descriptionjedis
 */
public class JedisClient {
    public static void main(String[] args) {

        Jedis jedis = new Jedis("127.0.0.1",6380);
        jedis.set("qwe","asdf");
    }
}
