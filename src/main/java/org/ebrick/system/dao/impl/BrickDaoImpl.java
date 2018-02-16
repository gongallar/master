package org.ebrick.system.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ebrick.system.dao.BrickDao;
import org.ebrick.system.model.Brick;
import org.springframework.stereotype.Repository;

@Repository
public class BrickDaoImpl implements BrickDao   {
		
		  private static final Map<Integer,Brick> brickMap =new HashMap<Integer,Brick>();
		  
		    static {
		        initializeBricks();
		    }
		 
		    public Brick getBrick(int brickId) {
		        return brickMap.get(brickId);
		    }
		 
		    public Brick addBrick(Brick brick) {
		        brickMap.put(brick.getId(), brick);
		        return brick;
		    }
		 
		    public Brick updateBrick(Brick brick) {
		        brickMap.put(brick.getId(), brick);
		        return brick;
		    }
		 
		    public void deleteBrick(int brickId) {
		        brickMap.remove(brickId);
		    }
		 
		    
		    private static void initializeBricks() {
		        Brick brick1 = new Brick(101,"clay",10,0,50,50);
		        Brick brick2 = new Brick(102, "cement", 20,0,50,50);
		 
		        brickMap.put(brick1.getId(), brick1);
		        brickMap.put(brick2.getId(), brick2);
		    }
		    
		    public List<Brick> getBricks() {
		        Collection<Brick> c = brickMap.values();
		        List<Brick> list = new ArrayList<Brick>();
		        list.addAll(c);
		        return list;
		    }

			@Override
			public Brick getBrick(String bricktype) {
			     return brickMap.get(bricktype);
			}


}
