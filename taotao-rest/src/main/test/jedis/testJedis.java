package jedis;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by geek on 2017/6/30.
 */
public class testJedis {

    // 单实例连接redis
    @Test
    public void testJedisSingle() {

        Jedis jedis = new Jedis("192.168.56.133", 6379);
        jedis.set("name", "bar");
        String name = jedis.get("name");
        System.out.println(name);
        jedis.close();
    }


    //连接池方式
    @Test
    public void testJedisPool() {

        //1.创建连接池
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        //1.1设置属性
        poolConfig.setMaxIdle(30);//连接数
        poolConfig.setMaxIdle(5);//空闲数

        //2.创建连接
        JedisPool pool = new JedisPool("192.168.56.133", 6379);
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.set("jjj", "bar");
            String name = jedis.get("jjj");
            System.out.println(name);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (jedis != null)
                jedis.close();
            pool.close();
        }
    }

    // 连接redis集群
    @Test
    public void testJedisCluster() {

        JedisPoolConfig config = new JedisPoolConfig();
        // 最大连接数
        config.setMaxTotal(30);
        // 最大连接空闲数
        config.setMaxIdle(2);

        //集群结点
        Set<HostAndPort> jedisClusterNode = new HashSet<HostAndPort>();
        String IP = "192.168.0.106";
        jedisClusterNode.add(new HostAndPort(IP, 7001));
        jedisClusterNode.add(new HostAndPort(IP, 7002));
        jedisClusterNode.add(new HostAndPort(IP, 7003));
        jedisClusterNode.add(new HostAndPort(IP, 7004));
        jedisClusterNode.add(new HostAndPort(IP, 7005));
        jedisClusterNode.add(new HostAndPort(IP, 7006));
        JedisCluster jc = new JedisCluster(jedisClusterNode, config);

        JedisCluster jcd = new JedisCluster(jedisClusterNode);
        jcd.set("name", "zhangsan");
        String value = jcd.get("name");
        System.out.println(value);
    }

    @Test
    public void testSpingJedisCluster(){
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-jedis.xml");
//        JedisCluster jedisCluster = context.getBean(JedisCluster.class);
//        jedisCluster.set("pwd","11");
//        System.out.println(jedisCluster.get("pwd"));
        JedisPool jedisPool = context.getBean(JedisPool.class);
        Jedis jedis = jedisPool.getResource();
        jedis.set("ppp", "bar");
        String name = jedis.get("ppp");
        System.out.println(name);
    }
}
