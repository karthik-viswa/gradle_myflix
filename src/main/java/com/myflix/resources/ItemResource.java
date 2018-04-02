package com.myflix.resources;

import com.myflix.entities.Item;
import com.myflix.repositories.ItemRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by Karthik on 26/09/2016.
 */
@Path("items")
public class ItemResource
{
    @Path("/hello")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String sayhello()
    {
        return "hello";
    }

    @Path("/item/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Item getItemById(@PathParam("id") int id)
    {
        ItemRepository repository = ItemRepository.getInstance();
        return repository.getItem(id).isPresent()? repository.getItem(id).get() : null;
    }

    @Path("/all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Item> getItems()
    {
        ItemRepository repository = ItemRepository.getInstance();
        return repository.getItems();
    }

    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Item addItem(Item item)
    {
        ItemRepository repository = ItemRepository.getInstance();
        return repository.addItem(item);
    }
}
