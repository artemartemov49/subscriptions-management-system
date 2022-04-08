package com.artem.subscriptionsmanagementsystem.mapper.item;

import com.artem.subscriptionsmanagementsystem.database.entity.Item;
import com.artem.subscriptionsmanagementsystem.dto.item.ItemReadDto;
import com.artem.subscriptionsmanagementsystem.mapper.Mapper;

public class ItemReadMapper implements Mapper<Item, ItemReadDto> {

    @Override
    public ItemReadDto map(Item object) {
        return new ItemReadDto(object.getId(), object.getName());
    }
}
