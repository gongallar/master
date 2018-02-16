package org.ebrick.system.controller;

import java.util.List;

import org.ebrick.system.dao.BrickDao;
import org.ebrick.system.model.Brick;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BrickController {

	@Autowired
	private BrickDao brickDAO;

	@ResponseBody
	@RequestMapping(value = "/getBricks", method = RequestMethod.GET)
	public List<Brick> getBricks() {
		List<Brick> bricks = brickDAO.getBricks();
		return bricks;
	}

	@RequestMapping(value = "/brick/{brickId}", method = RequestMethod.GET)
	public Brick getBrick(@PathVariable("brickId") int brickId) {
		return brickDAO.getBrick(brickId);
	}

	@RequestMapping(value = "/brick", method = RequestMethod.POST)
	@ResponseBody
	public Brick addBrick(@RequestBody Brick brickId) {

		System.out.println("(Service Side) Creating Brick: " + brickId.getId());

		return brickDAO.addBrick(brickId);
	}

	@ResponseBody
	@RequestMapping(value = "/brick", method = RequestMethod.PUT)
	public Brick updateBrick(@RequestBody Brick brick) {

		System.out.println("(Service Side) Editing Brick: " + brick.getId());

		return brickDAO.updateBrick(brick);
	}
    @ResponseBody
	@RequestMapping(value = "/brick/{brickId}", method = RequestMethod.DELETE)
    public void deleteBrick(@PathVariable("brickId") int brickId) {
 
        System.out.println("(Service Side) Deleting Brick: " + brickId);
 
        brickDAO.deleteBrick(brickId);
    }
}
