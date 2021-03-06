package edu.ggc.itec4860.rest;

import java.util.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class FrobController
{
    private static int id = 3;
    private static HashMap<Integer, Name> names;
    static {
	names = new HashMap<>();
	names.put(1, new Name(1, "Jimothy"));
	names.put(2, new Name(2, "Erasmus"));
	names.put(3, new Name(3, "Hamnett"));
    };

    /**
     * Show all the Names in the system.
     *
     * @return a list of Names with their ID.
     */
    @RequestMapping(value="/names", method=RequestMethod.GET)
    public List<Name> getNames()
    {
	List<Name> results = new ArrayList<Name>();

	for (Map.Entry<Integer, Name> n : this.names.entrySet()) {
	    results.add(n.getValue());
	}

	return results;
    }

    /**
     * Look up the ID in the list of Names.
     *
     * @param id the ID to look up.
     * @return the name associated with the ID. If not, returns an
     * empty string.
     * @throws NonexistantNameException if the Name does not exist.
     */
    @RequestMapping(value="/names/{id}", method=RequestMethod.GET)
    public String getName(@PathVariable int id)
    {
	Name result = this.names.get(id);

	if (result != null) {
	    return result.name;
	} else {
	    throw new NonexistantNameException(id);
	}
    }

    /**
     * Insert a Name into the system.
     *
     * @param input a Name to insert.
     * @return the Name input.
     */
    @RequestMapping(value="/sendName", method=RequestMethod.POST)
    public Name createName(@RequestBody Name input)
    {
	Name tempName = new Name(++id, input.name);
	this.names.put(id, tempName);
	return tempName;
    }

    /**
     * Add Names into the system.
     *
     * @param input the list of Names to insert.
     * @return the Names inserted.
     */
    @RequestMapping(value="/sendNames", method=RequestMethod.POST)
    public List<Name> createNames(@RequestBody List<Name> input)
    {
	List<Name> added = new ArrayList<Name>(input.size());

	for (Name n : input) {
	    added.add(createName(n));
	}

	return added;
    }

    /**
     * Change a Name if it exists in the system already.
     *
     * @param input the Name to make a change to and its result.
     * @return the Name.
     * @throws NonexistantNameException if the Name does not exist.
     */
    @RequestMapping(value="/names", method=RequestMethod.PUT)
    public Name updateName(@RequestBody Name input)
    {
	// Will be NULL if this ID already has a mapping.
	Name result = this.names.replace(input.id, input);

	if (result == null) {
	    throw new NonexistantNameException(input.id);
	}

	return input;
   }

    /**
     * Remove a Name from the system if it exists.
     *
     * @param id the ID of the Name to remove.
     * @throws NonexistantNameException if the Name does not exist.
     */
    @RequestMapping(value="/names/{id}", method=RequestMethod.DELETE)
    public void removeName(@PathVariable int id)
    {
	Name result = this.names.remove(id);

	if (result == null) {
	    throw new NonexistantNameException(id);
	}
    }
}
