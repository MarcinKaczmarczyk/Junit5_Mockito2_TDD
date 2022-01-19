package pl.exercises.zadania;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UnitTest {
    //given
    Coordinates coordinates;
    Unit unit;

    @BeforeEach
    void createReferencesForTestingObject() {
        coordinates = new Coordinates(50, 50);
        unit = new Unit(coordinates, 100, 1000);
    }

    @Test
    void moveShouldThrowExceptionWhenCoordinatesSumIsHigherThenCurrentFuel() {
        //when
        //then
        assertThrows(IllegalStateException.class, () -> unit.move(51, 50));
    }

    @Test
    void unitShouldLoseFuelWhenMove() {
        //given
        Unit unitTest = new Unit(coordinates, 100, 1000);
        //when
        unitTest.move(30, 30);
        //then
        assertThat(unit.getFuel(), greaterThan(unitTest.getFuel()));
    }

    @Test
    void unitShouldChangeCoordinatesWhenMove() {
        //given
        //when
        Coordinates coordinatesTest = unit.move(5, 5);
        //then
        assertThat(coordinatesTest,equalTo(new Coordinates(55,55)));
    }
    @Test
    void fuelingShouldNotExceedMaxFuelLimit(){
        //given
        //when
        unit.tankUp();
        //then
        assertThat(unit.getFuel(),is(100));
    }
    @Test
    void loadCargoShouldThrowExceptionWhenCargoWeightIsHigherThenMaxCargoWeight(){
        //given
        Cargo cargo=new Cargo("c1",500);
        Cargo cargo2=new Cargo("c2",501);
        //when
        unit.loadCargo(cargo);
        //then
        assertThrows(IllegalStateException.class,()->unit.loadCargo(cargo2));
    }
    @Test
    void unloadAllCargoShouldResetCurrentCargoWeight(){
        //given
        Cargo cargo=new Cargo("c1",100);
        Cargo cargo2=new Cargo("c2",300);
        //when
        unit.loadCargo(cargo);
        unit.loadCargo(cargo2);
        unit.unloadAllCargo();
        //then
        assertThat(unit.getLoad(),is(0));

    }

}