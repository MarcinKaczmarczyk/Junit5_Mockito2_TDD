package pl.exercises.order;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pl.exercises.Meal;
import pl.exercises.extension.BeforeAfterExtension;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@ExtendWith(BeforeAfterExtension.class)
class OrderTest {

    private Order order;

    @BeforeEach
    void initializeOrder(){
        System.out.println("inside @BeforeEach method");
        order=new Order();
    }
    @AfterEach
    void cleanUp(){
        System.out.println("AfterEach");
        order.cancel();
    }

    @Test
    void testAssertToArrayEquals() {
        //given
        int[] ints1={1,2,3};
        int[] ints2={1,2,3};
        //then
        assertArrayEquals(ints1,ints2);
    }

    @Test
    void mealListShouldBeEmptyAfterCreationOfOrder() {
        //given

        System.out.println(order.getMeals().size());
        //then
        assertThat(order.getMeals(),empty());
        assertThat(order.getMeals().size(), equalTo(0));
        assertThat(order.getMeals(),hasSize(0));
        MatcherAssert.assertThat(order.getMeals(),emptyCollectionOf(Meal.class));

    }
    @Test
    void addingMealToOrderShouldIncreaseOrderSize(){
        //given
        Meal meal=new Meal(14,"burger");
        Meal meal2=new Meal(11,"sandwich");



        //when
        order.addMealToOrder(meal);

        //then
        assertThat(order.getMeals(),hasSize(1));
        assertThat(order.getMeals(),contains(meal));
        assertThat(order.getMeals(),hasItem(meal));

        assertThat(order.getMeals().get(0).getPrice(),equalTo(14));

    }
    @Test
    void removingMealFromOrderShouldDecreaseOrderSize(){
        //given
        Meal meal=new Meal(14,"burger");


        //when
        order.addMealToOrder(meal);
        order.removeMealFromOrder(meal);

        //then
        assertThat(order.getMeals(),hasSize(0));
        assertThat(order.getMeals(),not(contains(meal)));
    }
    @Test
    void mealsShouldBeInCorrectOrderAfterAddingThemToOrder(){
        //given
        Meal meal=new Meal(14,"burger");
        Meal meal2=new Meal(11,"sandwich");


        //when
        order.addMealToOrder(meal);
        order.addMealToOrder(meal2);

        //then
        assertThat(order.getMeals(),contains(meal,meal2));
        assertThat(order.getMeals(),containsInAnyOrder(meal2,meal));
    }
    @Test
    void testIfTwoMealListsAreTheSame(){
        //given
        Meal meal=new Meal(14,"burger");
        Meal meal2=new Meal(11,"sandwich");
        Meal meal3=new Meal(13,"kebab");

        List<Meal> meals = Arrays.asList(meal,meal2);
        List<Meal> meals2 = Arrays.asList(meal,meal2);

        //then
        assertThat(meals,is(meals2));

    }
}