package com.example.swiggato.repositories;

import com.example.swiggato.enums.MenuCategory;
import com.example.swiggato.models.MenuItem;
import com.example.swiggato.models.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<MenuItem,Integer> {

    List<MenuItem>  findByVegAndRestaurant(boolean veg, Restaurant restaurant);
    List<MenuItem> findByCategory(MenuCategory menuCategory);

    List<MenuItem> findByCategoryAndRestaurantAndPriceGreaterThan(MenuCategory menuCategory, Restaurant restaurant, double price);
// get cheapest 5 food items of a particular restaurant
    List<MenuItem> findTop5ByRestaurantOrderByPrice(Restaurant restaurant);
    // get costliest 5 food items of a particular restaurant
    List<MenuItem> findTop5ByRestaurantOrderByPriceDesc(Restaurant restaurant);
    // get costliest 5 food items of a particular category -> name of dish and restaurant which serve the dish
    List<MenuItem> findTop5ByCategoryOrderByPriceDesc(MenuCategory menuCategory);

    String resMaxItemsAndOpen = "select r from MenuItem m inner join Restaurant r on m.restaurant.id=r.id where r.opened=true group by r.id order by count(r.id) desc";

    @Query(value = resMaxItemsAndOpen)
    public List<Restaurant> getRestaurantWithMaxItemsAndOpen();

}
