package com.example.swiggato.repositories;

import com.example.swiggato.enums.MenuCategory;
import com.example.swiggato.models.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodItemRepository extends JpaRepository<FoodItem,Integer> {

    String categoryMostOrdered = "select m.category from food_item f inner join menu_item m on f.menu_item_id=m.id where order_id is not null group by m.category order by count(m.category) desc  limit 1";

    String categoryMostOrderedHQL = "select m.category from FoodItem f inner join MenuItem m on f.menuItem.id = m.id where f.order.id is not null group by m.category order by count(m.category) desc  limit 1";

    @Query(value = categoryMostOrderedHQL)
    public MenuCategory getCategoryMostOrdered();
}
