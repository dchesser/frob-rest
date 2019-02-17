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
     */
    @RequestMapping(value="/names/{id}", method=RequestMethod.GET)
    public String getName(@PathVariable int id)
    {
	Name result = this.names.get(id);

	if (result != null) {
	    throw new NonexistantNameException(id);
	} else {
	    return result.name;
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
	// get() only returns a value's previous value (even null).
	// Like what is up with null being a side effect rather than a
	// dedicated type? Even Kotlin and Crystal do that right.
	this.names.put(input.id, input);
	return input;
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
	for (Name n : input) {
	    this.names.put(n.id, n);
	}

	return input;
    }
}
