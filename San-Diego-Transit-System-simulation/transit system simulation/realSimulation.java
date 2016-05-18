package edu.cmu.cs.cs214.hw3;

import java.text.DecimalFormat;
import java.util.ArrayList;	
import java.util.List;

import edu.cmu.cs.cs214.hw2.StopTime;

public class realSimulation implements Simulation {
	private ArrayList<Person> personList = new ArrayList<Person>();
	private ArrayList<Person> removePersonList = new ArrayList<Person>();
	private ArrayList<BusInterface> busList = new ArrayList<BusInterface>();
	private ArrayList<BusInterface> removeBusList = new ArrayList<BusInterface>();
	private List<Entity> entityList = new ArrayList<Entity>();
	private ArrayList<Factory> factoryList = new ArrayList<Factory>();
	private int time = 14400;
	private double totalPerson = 0;
	private double personOntime = 0;
	private double totalBus = 0;
	private double busOntime = 0;
	private double bOntimePercent = 1.0;
	private double pOntimePercent = 1.0;

	public realSimulation() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void step() {
	  for (Factory f1: factoryList){
		  f1.step();
	  }
	  
	  if (busList != null){
	    for (BusInterface b1:busList) {
		  b1.step(this);
		  		
	   }
	  }
	  
	  time += 1; 
	  personList.removeAll(removePersonList);
	  busList.removeAll(removeBusList);
	}

	@Override
	public int getTime() {
		return time;
	}

	@Override
	public Iterable<Entity> getEntities() {
		return entityList;
	}

	@Override
	public String getAnalysisResult() {
		DecimalFormat df = new DecimalFormat("#.00");
		
		String str = " bus on time " + df.format(bOntimePercent) +
				" | person on time " + df.format(pOntimePercent);
		return str;
	}

	@Override
	public void insertPerson(Person p1, PersonFactory f1) {
		Person p2 = f1.rondomCreatePerson(0, p1);
		personList.add(p2);
	}

	@Override
	public void insertBus(BusInterface b1) {
		BusInterface b2 = this.setBusSize(0, b1);
		busList.add(b2);
		entityList.add((Entity) b2);
	}

	@Override
	public void removePerson(Person p1, Bus b1) {
		this.totalPerson++;
		int stopIndex = b1.getStopIndex()-1 ;
		
		if (p1.getItinerary().getEndTime() >= 
				(b1.getTravelTime(stopIndex) + b1.getStartTime()))
			this.personOntime++;
		
		this.pOntimePercent = this.personOntime/this.totalPerson;
		this.getAnalysisResult();
		//System.out.println(p1.getItinerary().getEndTime()+ 
			//	" person arrive end %%%%%%%%%%%%%%%%%%%%%%%% " + b1.getTravelTime(stopIndex));
		
		removePersonList.add(p1);
	}
	
	@Override
	public void arriveAt(Bus b1, StopTime s1){
	  for (Person p1:personList) {
		p1.busArrive(b1, s1);
	  }		
	}

	@Override
	public void addFactory(Factory f1) {
		factoryList.add(f1);
		
	}

	@Override
	public void clearAllPerson() {
		personList.clear();
	}

	@Override
	public BusInterface setBusSize(int condition, BusInterface b1) {
	    if (condition == 0 ){	
	       
	       	b1.setLuggageCapacity(getRomdon(1,5));
	       	b1.setPersonCapacity(getRomdon(18,30));
	       	b1.setWheelchiarCapacity(getRomdon(1,2));
	     } else {
			 b1.setLuggageCapacity(getRomdon(4,10));
			 b1.setPersonCapacity(getRomdon(20,60));
			 b1.setWheelchiarCapacity(getRomdon(2,4));
	       }
		return b1;
	}
	
	private int getRomdon(int low, int high){
		return (int)(low + Math.random()*(high-low+1));
	}

	@Override
	public void busArriveDest(BusInterface b1) {
		this.totalBus ++;
		int stopIndex = b1.getStopIndex() - 1;
		
		if (b1.getOriginalEndTime() >= 
				(b1.getTravelTime(stopIndex) + b1.getStartTime()))
			this.busOntime++;
		
		this.bOntimePercent = this.busOntime/this.totalBus;
		this.getAnalysisResult();
		removeBusList.add(b1);
		System.out.println(" bus arrive end " + b1.getOriginalEndTime()
				+ " ************  total bus " + this.totalBus);
	}
	

}
