package pl.exercises.zadania;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoordinatesTest {

    @Test
    void constructorShouldThrowExceptionWhenCoordinatesIsHigherThen100(){
        //given
        //when
        //then
        assertThrows(IllegalArgumentException.class,()->new Coordinates(101,100));
    }
    @Test
    void constructorShouldThrowExceptionWhenCoordinatesIsLowerThen0(){
        //given
        //when
        //then
        assertThrows(IllegalArgumentException.class,()->new Coordinates(-1,0));
    }
    @Test
    void copyShouldReturnObjectWithSameAttributes(){
        //given
        Coordinates coordinates = new Coordinates(0, 0);
        //when
        Coordinates copy=Coordinates.copy(coordinates,0,0);
        //then
        assertEquals(coordinates,copy);
        assertNotSame(coordinates,copy);
    }
    @Test
    void copyShouldAddParametersToAttributes(){
        //given
        Coordinates coordinates = new Coordinates(10, 11);
        //when
        Coordinates copy=Coordinates.copy(new Coordinates(0,0),10,11);
        //then
        assertEquals(coordinates,copy);

    }

}