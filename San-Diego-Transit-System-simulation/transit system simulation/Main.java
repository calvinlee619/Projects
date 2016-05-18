package edu.cmu.cs.cs214.hw2;

import java.io.IOException;
import java.util.*;

public class Main {
	private static final String TRANSIT_FILE = "src/main/resources/all_stop_times.txt";
    private static final int MAX_WAIT = 1200; // don't make rider wait more than
					      // 20 minutes
    
	public static void main(String[] arg) throws IOException{
	  RoutePlannerBuilder builder = new RealBuilder(); // Change this to construct your own
	    // RouteBuilder implementation
      RoutePlanner planner = builder.build(TRANSIT_FILE, MAX_WAIT);     
      
      List<Stop> matchingStops1 = planner
  		    .findStopsBySubstring("FORBES AVE OPP MOREWOOD AVE (CARNEGIE MELLON)");
      
      Stop stop1 = matchingStops1.get(0);
      
      List<Stop> matchingStops2 = planner
    		    .findStopsBySubstring("FORBES AVE AT PEEBLES ST");
      
      Stop stop2 = matchingStops2.get(0);
      
	  Itinerary itinerary = planner.computeRoute(stop1, stop2, 26580);
	  System.out.println(itinerary.getInstructions());
	}
}
