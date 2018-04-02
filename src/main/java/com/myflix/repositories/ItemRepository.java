package com.myflix.repositories;

import com.myflix.entities.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Karthik on 26/09/2016.
 */
public class ItemRepository
{
    private List<Item> items;

    private static ItemRepository instance;

    public static ItemRepository getInstance()
    {
        if(instance == null)
        {
            instance = new ItemRepository();
        }

        return instance;
    }

    private ItemRepository()
    {
        Item item1 = new Item();
        item1.setId(1);
        item1.setName("Breaking Bad");

        Item item2 = new Item();
        item2.setId(2);
        item2.setName("Prison Break");

        Item item3 = new Item();
        item3.setId(3);
        item3.setName("House of Cards");

        items = new ArrayList<Item>();

        items.add(item1);
        items.add(item2);
        items.add(item3);
    }

    public Optional<Item> getItem(int id)
    {
        return items.stream()
                    .filter(item -> item.getId() == id)
                    .findFirst();
    }

    public List<Item> getItems()
    {
        return items;
    }

    public Item addItem(Item newItem)
    {
        if(newItem == null || newItem.getName() == null)
        {
            return null;
        }

        if(items.stream()
                .filter(item -> item.getName().equalsIgnoreCase(newItem.getName()))
                .findFirst().isPresent())
        {
            return null;
        }

        if(newItem.getId() == 0)
        {
            newItem.setId(99); //TODO set id to next highest id.
        }

        return items.add(newItem)? newItem : null;
    }
}
