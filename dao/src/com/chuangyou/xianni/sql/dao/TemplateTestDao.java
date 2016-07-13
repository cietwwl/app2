package com.chuangyou.xianni.sql.dao;

import java.util.List;

import com.chuangyou.xianni.entity.test.TemplateTest;

/**
 * DAO测试
 */
public interface TemplateTestDao {
	/**
	 * 获取一个对象
	 * 
	 * @param id
	 * @return
	 */
	public TemplateTest get(int id);

	public boolean add(TemplateTest test);

	public boolean delete(int id);

	public boolean updata(TemplateTest test);

	/**
	 * 获取所有
	 * 
	 * @return
	 */
	public List<TemplateTest> getList(List<Integer> ids);
	public List<TemplateTest> getAll();

	/**
	 * 批量保存
	 * 
	 * @return
	 */
	public boolean batchSave(List<TemplateTest> list);

	/**
	 * 更新某个属性
	 * 
	 * @param id
	 * @param name
	 * @return
	 */

	public boolean updateName(int id, String name);
}
