package com.chuangyou.xianni.sql.dao;
import java.util.List;

import com.chuangyou.xianni.entity.email.Email;

public interface EmailDao {

//	/**
//	 * 获取一个对象
//	 * 
//	 * @param id
//	 * @return
//	 */
//	public Email get(long privateId);
	/**
	 * 插入
	 * @param email
	 * @return
	 */
	public int add(Email email);

	/**
	 * 删除
	 * @param privateId
	 * @return
	 */
	public boolean delete(long privateId);

	/**
	 * 更新
	 * @param email
	 * @return
	 */
	public boolean update(Email email);
	
	
	/**
	 *  获取用户邮件 
	 * @param roleId
	 * @return
	 */
	public List<Email> getAll(long roleId);
}
