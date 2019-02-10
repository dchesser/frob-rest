package edu.ggc.itec4860.rest;

/**
 * A tidbit of data: a name with an ID.
 * <hr>
 * The assignment requirements say that our named class needs
 * getters and setters. I vehemently disagree with this notion.
 * Granted my reasoning is more of a subjective thing and I'll
 * take this doc-comment to explain why.
 * POJOs (Plain Old Java Objects) are an especially heinous case
 * of senselessly inserting getters and setters.
 * Students are often taught to do so for "improved safety".
 * Safety from what? It's no different from using just a public
 * attribute since context will still dictate what happens there.
 *
 * In an ideal object-oriented system, your data would be objects,
 * being passed messages by means of methods.
 * With a similar ideal, this these attributes do not need to be
 * exposed either as these methods operate on them as necessary,
 * transforming the interaction from a procedural style to an
 * object-oriented style.
 * Unfortunately, this is not a perfect world.
 * Even less so in an undergrad's education where students
 * struggle learning the ropes of not OOP, but programming and
 * computing in general.
 *
 * A POJO, on the other hand, is equivalent to a C data structure.
 * More often than not, they merely serve as a container of data.
 * Sometimes you'll find a method to get a computed value.
 * A good example of showing this may be a Rectangle class.
 * Sure, you'll have a public `width` and `height` attribute, but then
 * you can have a `getArea()` method to calculate the area of that
 * `Rectangle` instance.
 * Otherwise, if there's computation or a side effect involved, just
 * leave it a public attribute.
 *
 * tl;dr POJOs should just have public attributes.
 * Anything else should aim to be private and not even expose the
 * attributes outside of public non-getter/setter methods.
 * Despite my ideals, I'm still including them in this assignment
 * anyway out of fear of lost points for not doing as the paper said.
 */
public class Name
{
    /** ID of a Name */
    public int id;

    /** Whatever a Name is called; its name. */
    public String name;

    /**
     * Create a new, "uninstantiated" Name.
     * Its defaults aren't ones you'd want in real code either.
     */
    public Name()
    {
        this.id = -1;           // -1 meaning "you really should change it".
        this.name = "";         // No name associated.
    }

    /**
     * Create a new name.
     */
    public Name(int id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public int getID() { return this.id; }
    public String getName() { return this.name; }

    // I like fluent interfaces.
    // https://en.wikipedia.org/wiki/Fluent_interface

    public Name setID(int id)
    {
        this.id = id;
        return this;
    }

    public Name setName(String name)
    {
        this.name = name;
        return this;
    }
}
