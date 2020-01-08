package com.example.demo.dao;

import com.example.demo.dto.Clientinfo;
import java.util.List;
import java.util.Map;

public interface IClientinfoDAO {
	public List<Clientinfo> queryClientinfoBean(Clientinfo clientinfo);
	
	public List<Clientinfo> queryClientinfoBeanByProductCode(Clientinfo clientinfo);

	public List<Clientinfo> queryClientinfo(Map<String, Object> map);

}