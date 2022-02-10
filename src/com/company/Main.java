package com.company;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    final static int lowLevel = 0;
    private static int highLeve =25;
    public static void checkFloor(int floor, int lvlLow, int lvlHi) throws FloorException {
        if(floor < lvlLow) {
            throw new FloorLowException("the floor value is too small");
        } else if (floor > lvlHi) {
            throw new FloorLowException("the floor value is too high");
        }
    }

    public static void main(String[] args) throws FloorException{
        int choice = -1;
        Queue <Integer> queue = new PriorityQueue<>();
        Scanner scanner = new Scanner(System.in);
        while (!(choice == 0)) {
            String choiceFloor = scanner.nextLine();
            try {
                choice = Integer.parseInt(choiceFloor);
                checkFloor(choice, lowLevel, highLeve);
                queue.add(choice);
            } catch (FloorLowException FloorException) {
                System.out.println("Слишком маленькое значение, номер этажа не долшен быть меньше: " + lowLevel);
            } catch (FloorHighException FloorException) {
                System.out.println("Слишком высокое значение, номер этажа не долшен быть больше: " + highLeve);
            } catch (NumberFormatException R) {
                System.out.println("Доспускается вводить только цифровые значения в диапазоне от: " + lowLevel + " до: " + highLeve);
            }

        }
        System.out.println(queue);
        //TODO Упорядочить очередь и
        // доделать расчёт задания со *
        // возможно стоит поменять класс очереди
        // на друго в которую автоматически упорядочиваются значеия
    }
}
