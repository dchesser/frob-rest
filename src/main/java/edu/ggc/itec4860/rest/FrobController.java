package edu.ggc.itec4860.rest;

import java.util.List;
import java.util.Arrays;
import java.util.Optional;
import org.springframework.web.bind.annotation.*;

@RestController
public class FrobController
{
    private List<Name> names = Arrays.asList(new Name(1, "Jimothy"),
                                             new Name(2, "Erasmus"),
                                             new Name(3, "Hamnett"));

    /**
     * Show all the Names in the system.
     *
     * @return a list of Names with their ID.
     */
    @RequestMapping(value="/names", method=RequestMethod.GET)
    public List<Name> getNames()
    {
        return this.names;
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
        // java.util.Stream ~❤️
        return names.stream()
            .filter(n -> n.id == id)
            .findFirst()
            .orElse(new Name()).name;
        // Like, I'm not sure how we should fail gracefully here.
    }
}
