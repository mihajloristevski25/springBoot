package com.example.spirngbootjpa.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spirngbootjpa.dao.AlienRepo;
import com.example.spirngbootjpa.model.Alien;

@RestController
public class AlienController 
{	
	
	@Autowired
	AlienRepo repo;
	
	@RequestMapping("/")
	public String home()
	{
		return "home.jsp";
	}
	
//	@RequestMapping("/addAlien")
//	public String addAlien(Alien alien) {
//		repo.save(alien);
//		return "home.jsp";
//	}
	
//	@RequestMapping("/getAlien")
//	public ModelAndView getAlien(@RequestParam int aid) {
//		
//		ModelAndView mv = new ModelAndView("showAlien.jsp");
//		Alien alien = repo.findById(aid).orElse(new Alien());
//		
////		System.out.println(repo.findByTech("Java"));
////		System.out.println(repo.findByAidGreaterThan(102));
////		System.out.println(repo.findByTechSorted("Java"));
////		
//		mv.addObject(alien);
//		return mv;
//	}
	
//	@RequestMapping("/updateAlien")
//	public String updateAlien(@RequestParam int aid , Alien alien) {
//		repo.findById(aid).orElse(new Alien());
//		repo.save(alien);
//		return "home.jsp";
//	}
//	
//	@RequestMapping("/deleteAlien")
//	public String updateAlien(@RequestParam int aid) {
//		repo.deleteById(aid);
//		return "home.jsp";
//	}
	
	@GetMapping(path = "/aliens")
	public List<Alien> getAliens()
	{
		return repo.findAll();
	}
	
	@RequestMapping("/alien/{aid}")
	public Optional<Alien> getAlien(@PathVariable("aid")int aid)
	{
		return repo.findById(aid);
	}
	
	@PostMapping("/alien")
	public Alien addAlien(Alien alien)
	{
		repo.save(alien);
		return alien;
	}
	
	@DeleteMapping("/alien/{aid}")
	public String deleteAlien(@PathVariable int aid) 
	{
		Alien a = repo.getOne(aid);
		
		repo.delete(a);
		
		return "deleted";
	}
	
	@PutMapping(path = "/alien", consumes= {"application/json"})
	public Alien saveOrUpdateAlien(@RequestBody Alien alien) {
		repo.save(alien);
		return alien;
	}
	
}
