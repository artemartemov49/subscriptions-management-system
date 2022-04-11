package com.artem.subscriptionsmanagementsystem.mapper.item;

import com.artem.subscriptionsmanagementsystem.database.entity.Item;
import com.artem.subscriptionsmanagementsystem.dto.item.ItemCreateEditDto;
import com.artem.subscriptionsmanagementsystem.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class ItemCreateEditMapper implements Mapper<ItemCreateEditDto, Item> {

    @Override
    public Item map(ItemCreateEditDto object) {
        var item = new Item();
        copy(object, item);

        return item;
    }

    @Override
    public Item map(ItemCreateEditDto fromObject, Item toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    private void copy(ItemCreateEditDto object, Item item) {

        item.setName(object.getName());
    }

}
