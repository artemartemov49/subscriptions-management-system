package com.artem.subscriptionsmanagementsystem.mapper.price;

import com.artem.subscriptionsmanagementsystem.database.entity.Duration;
import com.artem.subscriptionsmanagementsystem.database.entity.Item;
import com.artem.subscriptionsmanagementsystem.database.entity.Price;
import com.artem.subscriptionsmanagementsystem.database.repository.DurationRepository;
import com.artem.subscriptionsmanagementsystem.database.repository.ItemRepository;
import com.artem.subscriptionsmanagementsystem.dto.price.PriceCreateEditDto;
import com.artem.subscriptionsmanagementsystem.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PriceCreateEditMapper implements Mapper<PriceCreateEditDto, Price> {

    private final ItemRepository itemRepository;
    private final DurationRepository durationRepository;

    @Override
    public Price map(PriceCreateEditDto object) {
        var price = new Price();
        copy(object, price);

        return price;
    }

    @Override
    public Price map(PriceCreateEditDto fromObject, Price toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    void copy(PriceCreateEditDto object, Price price) {
        var item = getItem(object);
        var duration = getDuration(object);

        price.setItem(item);
        price.setAmount(object.getAmount());
        price.setDuration(duration);
    }

    private Item getItem(PriceCreateEditDto object) {
        return itemRepository.findById(object.getItemId())
            .orElseThrow();
    }

    private Duration getDuration(PriceCreateEditDto object) {
        return durationRepository.findById(object.getDurationId())
            .orElseThrow();
    }
}
