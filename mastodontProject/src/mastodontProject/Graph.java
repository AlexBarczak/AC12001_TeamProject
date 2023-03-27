package mastodontProject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

/**
 * Class to hold graph data structure with Users as vertices
 * 
 * @author Group 1
 * Version 1
 */

public class Graph implements Serializable{
	
	// Maps user to hashset of other users
    private Map<User, HashSet<User>> adjacencyList;

    /**
     * Default constructor of graph initialising empty graph structure
     */
    public Graph() 
    {
    	//initialise input streams
    			File f = new File("mastodont.txt");
    			FileInputStream fis = null;
    			ObjectInputStream ois = null;
    			//check file exists
    			if(f.exists() && !f.isDirectory()) 
    			{ 
    				try 
    				{
    					fis = new FileInputStream("mastodont.txt");
    					ois = new ObjectInputStream(fis);
    					// read in serialized graph from file on disk
    					adjacencyList = (Map<User, HashSet<User>>) ois.readObject();
    				} 
    				catch (IOException e) 
    				{
    				     System.out.println("Something went wrong :(");
    				}
    				catch (ClassNotFoundException e)
    				{
    					 System.out.println("Something went wrong :(");
    				}
    				finally {
    					if (ois != null)
    					{
    		            	try 
    		            	{
    		                	ois.close();
    		            	}
    		            	catch (IOException e)
    		            	{
    		                	System.out.println("Sorry, there has been a problem closing the file");
    		            	}
    		        	}
    				}
    			}
    			else {
    				//create empty graph if file doesn't exist to load from
    	            adjacencyList = new HashMap<User, HashSet<User>>();
    	        }
    }

    /**
     * Add a user as a vertex of the graph
     * 
     * @param u User being added to graph
     */
    public void addVertex(User u) 
    {
    	// assuming check already done that it's not a duplicate of another user
        adjacencyList.put(u, new HashSet<>());
    }

    /**
     * Add an edge between two vertices
     * 
     * @param source User edge is being added to/from
     * @param destination Other User edge is being added to/from
     * 
     * @return True if edge is added between specified vertices, false otherwise
     */
    public boolean addEdge(User source, User destination) 
    {
    	boolean edgeAdded = false;
    	// Making sure the graph contains the users, just to be safe.
    	// If this is already done elsewhere just chuck this and all others like it further on
        if (!adjacencyList.containsKey(source)|| !adjacencyList.containsKey(destination))
        {
        	return edgeAdded;
        }
        else if(source.equals(destination))
        {
        	return edgeAdded;
        }
        // Making sure the users aren't already friends
        else if(!adjacencyList.get(source).contains(destination))
        {
        	adjacencyList.get(source).add(destination);
            adjacencyList.get(destination).add(source);
            edgeAdded = true;
        }
        return edgeAdded;
        
    }

    /**
     * Get the set of all users in the graph
     * 
     * @return The set of all users in the graph
     */
    public HashSet<User> getUsers()
    {
    	HashSet<User> set = new HashSet<>(adjacencyList.keySet());
    	return set;
    }
    
    /**
     * Get a set of all vertices adjacent to a selected vertex
     * 
     * @param u User who's adjacent vertices are wanted
     * 
     * @return All vertices adjacent to specified vertex, empty if no connections
     */
    public HashSet<User> getAdjVertices(User u) 
    {
    	// Make sure graph contains vertex
    	if (!adjacencyList.containsKey(u)) 
		{
			return new HashSet<>();
		}
        return (HashSet<User>) adjacencyList.get(u);
    }

    /**
     * Remove a vertex from the graph if it exists
     * 
     * @param u User being removed from graph
     * 
     * @return True if user successfully removed from graph, false otherwise
     */
	public boolean removeVertex(User u) 
	{
		boolean vertexRemoved = false;
		
		// abort if vertex doesn't exist, idk if needed, just being safe
		if (!adjacencyList.containsKey(u)) 
		{
			return vertexRemoved;
		}
		Set<User> adjacentVertices = adjacencyList.get(u);
		for (User adjacentVertex : adjacentVertices) 
		{
			adjacencyList.get(adjacentVertex).remove(u);
		}

		//remove user from graph
		adjacencyList.remove(u);
		return vertexRemoved=true;
	}
	
	/**
	 * Remove the edge between two users if it exists
	 * 
	 * @param source User edge is being removed from
	 * @param destination Other User edge is being removed from
	 * 
	 * @return True if edge has been removed between users, false otherwise
	 */
	public boolean removeEdge(User source, User destination) 
	{
		boolean edgeRemoved = false;
		// Just making sure users exist in graph, idk if needed, just being safe
		if (!adjacencyList.containsKey(source) || !adjacencyList.containsKey(destination)) 
		{
			return edgeRemoved;
		}
		// Check users are connected
		else if(adjacencyList.get(source).contains(destination))
		{
			// remove edge between users
			adjacencyList.get(source).remove(destination);
			adjacencyList.get(destination).remove(source);
			return edgeRemoved=true;
		}
		return edgeRemoved;
	}
	
	/**
	 * Get vertices adjacent to vertices uer is adjacent to i.e friends of friends
	 * 
	 * @param u user we want adjacent to adjacent of
	 * 
	 * @return Set of all vertices adjacent to adjacent vertices of u, empty if none
	 */
	public HashSet<User> getAdjToAdj(User u)
	{
		HashSet<User> friends = getAdjVertices(u);
		// create empty set to add friends of friends to
		HashSet<User> friendsOfFriends = new HashSet<>();
		Iterator<User> it = friends.iterator(); 
 		while(it.hasNext()) {
 			User user = it.next();
 			if(getAdjVertices(user)!=null) 
 			{
 				friendsOfFriends.addAll(getAdjVertices(user));
 			}
		}
 		return friendsOfFriends;
	}
	
	/**
	 * Gets the set of the union of u1 and u2 adjacents
	 * 
	 * @param u1 
	 * @param u2
	 * 
	 * @return set of connections both u1 and u2
	 */
	public HashSet<User> getMutuals(User u1, User u2)
	{
		HashSet<User> u1Friends = getAdjVertices(u1);
		HashSet<User> u2Friends = getAdjVertices(u2);
		HashSet<User> mutuals = new HashSet<>();
		mutuals.addAll(u1Friends);
		mutuals.retainAll(u2Friends);
		return mutuals;
	}
	
	/**
	 * Gets the set of u2 friends minus u1 friends 
	 * i.e All friends u2 has that u1 doesnt
	 * 
	 * NOTE: u1 will be in this list if they are friends with u2
	 * 
	 * @param u1 user we want unique connections for
	 * @param u2 user we are comparing to for unique connections
	 * 
	 * @return set of all friends u2 has that u1 doesnt
	 */
	public HashSet<User> getUniques(User u1, User u2)
	{
		HashSet<User> u1Friends = getAdjVertices(u1);
		HashSet<User> u2Friends = getAdjVertices(u2);
		HashSet<User> uniques = new HashSet<>();
		uniques.addAll(u2Friends);
		uniques.removeAll(u1Friends);
		return uniques;
	}
	
	/**
	 * Save the graph to a file on disk
	 */
	public void saveGraph() {
		
		//initialise output streams
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		
		try 
		{
			 fos = new FileOutputStream("mastodont.txt");
             oos = new ObjectOutputStream(fos); 
             //write graph to file on disk
             oos.writeObject(adjacencyList);
        } 
		catch (IOException e) 
		{
			System.out.println("Something went wrong :(");
        }
		finally
        {
           if (fos != null)
           {
        	   try 
        	   	{
        		   fos.close();
        	   	} 
        	   catch (IOException e) 
        	   {
        		   System.out.println("Something went wrong :(");
        	   }
           }
        }
	}
}

