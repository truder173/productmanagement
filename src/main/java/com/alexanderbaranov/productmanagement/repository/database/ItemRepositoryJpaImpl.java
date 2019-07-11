package com.alexanderbaranov.productmanagement.repository.database;

import com.alexanderbaranov.productmanagement.model.Item;
import com.alexanderbaranov.productmanagement.repository.CrudItemRepository;
import com.alexanderbaranov.productmanagement.repository.ItemRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Profile("!jpa")
@Repository
public class ItemRepositoryJpaImpl implements ItemRepository {

    private CrudItemRepository crudItemRepository;

    public ItemRepositoryJpaImpl(CrudItemRepository crudItemRepository) {
        this.crudItemRepository = crudItemRepository;
    }

    @Override
    public List<Item> findAll() {
        return crudItemRepository.findAll();
    }

    @Transactional
    @Override
    public Item findById(Long id) {
        return crudItemRepository.getOne(id);
    }

    @Override
    public List<Item> findByType(String type) {
        return crudItemRepository.findByType(type);
    }

    @Override
    public Item save(Item item) {
        return crudItemRepository.save(item);
    }

    @Override
    public void deleteById(Long id) {
        crudItemRepository.deleteById(id);

    }


    @Override
    public Item update(Item item) {
        crudItemRepository.deleteById(item.getId());
        return crudItemRepository.save(item);
    }
}