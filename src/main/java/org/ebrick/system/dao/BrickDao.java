package org.ebrick.system.dao;

import java.util.List;

import org.ebrick.system.model.Brick;

public interface BrickDao {
	
	public List<Brick> getBricks() ;

	public Brick getBrick(int brickId);

	public Brick getBrick(String bricktype);
	
	public Brick addBrick(Brick brickId);

	public Brick updateBrick(Brick brickId);

	public void deleteBrick(int brickId);

}
