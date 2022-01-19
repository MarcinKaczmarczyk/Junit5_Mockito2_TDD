package pl.exercises;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pl.exercises.extension.IAExceptionIgnoreExtension;
import pl.exercises.order.Order;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class MealTest {

    @Test
    void shouldReturnDiscountedPrice() {
        //given
        Meal meal = new Meal(35);

        //when
        int discountedPrice = meal.getDiscountedPrice(7);

        //then
        assertEquals(28, discountedPrice);
        //hamcrest
//        assertThat(discountedPrice,equalTo(28));
        //assertJ
//        assertThat(discountedPrice).isEqualTo(28);
    }

    @Test
    void referencesToTheSameObjectShouldBeEquals() {

        //given
        Meal meal = new Meal(32);
        Meal meal2 = meal;
        //then
        assertSame(meal, meal2);
        //hamcrest
//        assertThat(meal,sameInstance(meal2));
        //assertJ
//        assertThat(meal).isSameAs(meal2);
    }

    @Test
    void referencesToTheDifferentObjectShouldNotBeEquals() {

        //given
        Meal meal = new Meal(32);
        Meal meal2 = new Meal(32);
        //then
        assertNotSame(meal, meal2);
        //hamcrest
//        assertThat(meal,not(sameInstance(meal2)));
        //assertJ
//        assertThat(meal).isNotSameAs(meal2);
    }

    @Test
    void twoMealShouldBeEqualWhenPriceAndNameAreTheSame() {
        //given
        Meal meal1 = new Meal(10, "pizza");
        Meal meal2 = new Meal(10, "pizza");

        //then
        assertEquals(meal1, meal2);
    }

    @Test
    void exceptionShouldBeThrowIfDiscountIsHigherThenThePrice() {
        //given
        Meal meal = new Meal(8, "soup");

        //when
        //then
        assertThrows(IllegalArgumentException.class, () -> meal.getDiscountedPrice(40));
    }

    @ParameterizedTest
    @ValueSource(ints = {5, 10, 15, 18})
    void mealPricesShouldBeLowerThan20(int price) {
        assertThat(price, lessThan(20));
    }

    @ExtendWith(IAExceptionIgnoreExtension.class)
    @ParameterizedTest
    @ValueSource(ints = {5, 1, 6, 8})
    void mealPricesShouldBeLowerThan10(int price) {
        if (price > 5) {
            throw new IllegalArgumentException();
        }
        assertThat(price, lessThan(10));
    }

    @TestFactory
    Collection<DynamicTest> dynamicTestCollectionExample() {
        return Arrays.asList(DynamicTest.dynamicTest("dynamic test 1",
                        () -> assertEquals(4, 2 * 2)),
                DynamicTest.dynamicTest("dynamic test 2",
                        () -> assertThat(5, lessThan(6))));
    }

    @Tag("fries")
    @TestFactory
    Collection<DynamicTest> calculateMealPrices() {
        Order order = new Order();
        order.addMealToOrder(new Meal(10, "hamburger", 2));
        order.addMealToOrder(new Meal(5, "sandwich", 12));
        order.addMealToOrder(new Meal(3, "fries", 3));

        Collection<DynamicTest> dynamicTests = new ArrayList<>();

        for (int i = 0; i < order.getMeals().size(); i++) {
            int price = order.getMeals().get(i).getPrice();
            int quantity = order.getMeals().get(i).getQuantity();

            Executable executable = () -> {
                assertThat(calculatePrice(price, quantity), lessThan(62));
            };
            String name = "Test name: " + i;
            DynamicTest dynamicTest=DynamicTest.dynamicTest(name, executable);
            dynamicTests.add(dynamicTest);
        }
        System.out.println("obecnytt");
        return dynamicTests;
    }

    @Test
    void testMealSumPrice(){

        //given
        Meal meal = mock(Meal.class);

        given(meal.getPrice()).willReturn(15);
        given(meal.getQuantity()).willReturn(3);
        given(meal.sumPrice()).willCallRealMethod();

        //when
        int result = meal.sumPrice();

        //then
        assertThat(result, equalTo(45));
    }

    private int calculatePrice(int price, int quantity) {
        return price * quantity;
    }



}