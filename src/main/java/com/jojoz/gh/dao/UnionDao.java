package com.jojoz.gh.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jojoz.gh.dto.UnionVO;
import com.jojoz.gh.dto.UnionVO1;
import com.jojoz.gh.entity.Union;

public interface UnionDao {

	/**
	 * 保存
	 * @param union
	 * @return
	 */
	public int save(Union union);
	
	/**
	 * 获取union
	 * @param user_id
	 * @return
	 */
	public Union getUnionByUserId(@Param("user_id") String user_id);
	
	/**
	 * 获取union
	 * @param user_id
	 * @return
	 */
	public Union getUnionById(@Param("id") String id);
	
	/**
	 * 更新
	 * @param union
	 * @return
	 */
	public int update(Union union);
	
	public List<UnionVO> getUnionAll();
	
	public List<UnionVO1> query(@Param("words")String words,@Param("state")Integer state,@Param("count")int count,@Param("index") int index,@Param("produceDivision")String produceDivision,@Param("dstart") String dstart,@Param("dend") String dend);
	
	public List<UnionVO1> getUnionForExport();
	
}
