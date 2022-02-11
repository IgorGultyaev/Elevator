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
            } catch (IllegalStateException R) {
                System.out.println("Лифт сломался нажмите: 0 ");
            }
        }
        queue.poll();
        int waitDoorsInSeconds = 10;
        int waitMoveInSeconds = 5;
        int totalSeconds = 0;
        int previousFloor = queue.peek();
        int currentFloor = queue.peek();
        System.out.println("Последовательность движения лифта");
        do {
            previousFloor = queue.peek();
            queue.poll();
            if (!queue.isEmpty()) {
                currentFloor = queue.peek();
                System.out.println("Этаж :" + previousFloor + " Следующий этаж: " + currentFloor);
            } else System.out.println("Этаж :" + previousFloor);

            totalSeconds += Math.abs(currentFloor - previousFloor) * waitMoveInSeconds;
            totalSeconds += waitDoorsInSeconds;
        } while (!queue.isEmpty());
        System.out.println("Время затраченное лифтом на маршрут =: " + totalSeconds + " с.");
    }
}
