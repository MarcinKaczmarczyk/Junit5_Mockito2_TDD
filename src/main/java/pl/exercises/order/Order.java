package pl.exercises.order;

import pl.exercises.Meal;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private OrderStatus orderStatus;
    private List<Meal> meals=new ArrayList<>();

    public void addMealToOrder(Meal meal){
        this.meals.add(meal);
    }
    public void removeMealFromOrder(Meal meal){
    this.meals.remove(meal);
    }
    public List<Meal> getMeals() {
        return meals;
    }

    public void changeOrderStatus(OrderStatus orderStatus){
        this.orderStatus=orderStatus;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    void cancel(){
        this.meals.clear();
    }

    @Override
    public String toString() {
        return "Order{" +
                "meals=" + meals +
                '}';
    }
}
