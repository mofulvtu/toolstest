package com.itcast.test;

import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import cn.itcast.commons.CommonUtils;
import cn.itcast.jdbc.JdbcUtils;
import cn.itcast.jdbc.TxQueryRunner;

/**
 * TxQueryRunner是QueryRunner的子类！(依赖commons-dbutils.jar)
 * 用法与QueryRunner相似！支持事务！底层使用了JdbcUtils来获取连接！
 * 
 * 简化jdbc的操作 
 * QueryRunner的三个方法： 
 * ①update() --> insert、update、delete 
 * ②query() -->select 
 * ③batch() --> 批处理
 * 
 * @author 刚刚
 */
public class TxQueryRunnerTest {
	/**
	 * 测试update()方法，用来执行insert、update、delete
	 */
	@Test
	public void testUpdate() throws Exception {
		String sql = "insert into person(pid,pname,age,sex) values(?,?,?,?)";
		Object[] params = { 1, "p1", 12, "男" };

		QueryRunner qr = new TxQueryRunner();
		qr.update(sql, params);// 执行sql,也不提供连接，它内部会使用JdbcUtils来获取连接
	}

	/**
	 * 使用事务
	 */
	@Test
	public void testUpdate2() throws Exception {
		try {
			JdbcUtils.beginTransaction();// 开启事务
			String sql = "insert into person(pid,pname,age,sex) values(?,?,?,?)";
			QueryRunner qr = new TxQueryRunner();

			Object[] params = { 4, "p2", 22, "女" };
			qr.update(sql, params);// 执行sql
			// if(false){
			// throw new Exception();
			// }
			params = new Object[] { 5, "p3", 10, "男" };
			qr.update(sql, params);// 执行sql
			JdbcUtils.commitTransaction();// 提交事务
		} catch (Exception e) {
			JdbcUtils.rollbackTransaction();// 回滚事务
		}
	}

	/**
	 * 测试查询方法 我们知道JDBC查询的结果是ResultSet 而QueryRunner查询的结果是通过ResultSet映射后的数据
	 * >QueryRunner第一部是执行select,得到ResultSet >吧ResultSet转换成其他类型 通过转换结果：
	 * >javaBean： 把结果集封装到javaBean中 >Map：把结果集封装到Map中 >把结果集封装到Object中(结果集是单行单列)
	 */
	/*
	 * 单行结果集映射到javaBean中
	 */
	@Test
	public void testQuery1() throws Exception {
		String sql = "select * from person where pid = ?";
		QueryRunner qr = new TxQueryRunner();
		/*
		 * 第二个参数类型为ResultSetHandler,它是一个接口，表示映射的结果类型。 
		 * BeanHandler -->它是ResultSetHandler的实现类， 
		 * 它的作用是把结果集封装到Person对象中
		 */
		Person p = qr.query(sql, new BeanHandler<Person>(Person.class), 1);
		System.out.println(p);//Person [pid=1, pname=p1, age=12, sex=男]
	}

	/**
	 * 使用BeanListHandler 把多行结果集映射到List<Bean>，即多个JavaBean对象
	 * 一行结果集记录对应一个JavaBean对象，多行就对应List<Bean>
	 */
	@Test
	public void testQuery2() throws Exception {
		String sql = "select * from person";
		QueryRunner qr = new TxQueryRunner();
		/*
		 * 第二个参数类型为ResultSetHandler,它是一个接口，表示映射的结果类型。 
		 * BeanListHandler -->它是ResultSetHandler的实现类， 
		 * 它的作用是把结果集封装到List<Person>对象中
		 */
		List<Person> list = qr.query(sql, new BeanListHandler<Person>(
				Person.class));
		System.out.println(list);
		//[Person [pid=1, pname=p1, age=12, sex=男], Person [pid=5, pname=p3, age=10, sex=男]]
	}

	/**
	 * 使用MapHandler，把单行结果集封装到Map对象中
	 */
	@Test
	public void testQuery3() throws Exception {
		String sql = "select * from person where pid = ?";
		QueryRunner qr = new TxQueryRunner();
		/*
		 * 第二个参数类型为ResultSetHandler,它是一个接口，表示映射的结果类型。 
		 * MapHandler -->它是ResultSetHandler的实现类，
		 * 它的作用是把结果集封装到Map<String,Object>对象中
		 */
		Map<String, Object> map = qr.query(sql, new MapHandler(), 1);
		System.out.println(map);// {pname=p1, sex=男, pid=1, age=12}
	}
	
	/**
	 *使用MapListHandler，把多行结果集封装到List<Map> 中 ，即多个Map
	 *一行对应一个Map,多行对应List<Map>
	 */
	@Test
	public void testQuery4() throws Exception {
		String sql = "select * from person";
		QueryRunner qr = new TxQueryRunner();
		/*
		 * 第二个参数类型为ResultSetHandler,它是一个接口，表示映射的结果类型。 
		 * MapListHandler -->它是ResultSetHandler的实现类，
		 * 它的作用是把结果集封装到List<Map>对象中
		 */
		List<Map<String, Object>> mapList = qr.query(sql, new MapListHandler());
		System.out.println(mapList);
		// [{pname=p1, sex=男, pid=1, age=12}, {pname=p2, sex=女, pid=2, age=22}]
	}
	
	/**
	 * 使用ScalarHandler，把单行单列的结果集封装到Object中
	 */
	@Test
	public void testQuery5() throws Exception {
		String sql = "select count(*) from person";//结果集是单行单列
		QueryRunner qr = new TxQueryRunner();
		/*
		 * 我们知道select count(*)结果一定是整数
		 * >Integer
		 * >Long
		 * >BigInteger
		 * 
		 * 不同驱动，结果不同！
		 * 无论是哪种类型，都是Number类型！强转成Number不会出错！
		 */
		Object obj = qr.query(sql, new ScalarHandler());
		Number number = (Number) obj;
		int sum = number.intValue();
		Long sum1 = number.longValue();
		System.out.println(sum);//5
		System.out.println(sum1);//5
	}
	
	/**
	 * 一行结果中包含了两张表的列
	 * 使用MapHandler处理
	 * 1.把结果集封装到map中
	 * 2.使用map生成Person对象
	 * 3.使用map生成address对象
	 * 4.把两个实体对象建立关系 
	 */
	@Test
	public void testQuery6() throws Exception {
		String sql = "SELECT * FROM person p, address a WHERE p.pid=a.aid AND p.pid=?";//结果集是单行单列
		QueryRunner qr = new TxQueryRunner();
		/* 
		 * 1.得到Map
		 */
		Map<String, Object> map = qr.query(sql, new MapHandler(),1);
		/*
		 * 2.把Map中部分数据封装到Person中
		 */
		Person p = CommonUtils.toBean(map, Person.class);
		/*
		 * 3.把Map中部分数据封装到Address中
		 */
		Address addr = CommonUtils.toBean(map, Address.class);
		/*
		 * 4.建立两个实体的关系
		 */
		p.setAddress(addr);
		
		System.out.println(p);
		
	}
	
	

}
