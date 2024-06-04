package dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import beans.Factory;

public class FactoryDAO {

	private List<Factory> factoryList=new ArrayList<Factory>();
	private String contextPath;
	public FactoryDAO() {loadLFactoriesFromFile();}
	
	public Collection<Factory> GetAll(){
		return factoryList;
	}
	private void loadLFactoriesFromFile() {
		
    }
}
