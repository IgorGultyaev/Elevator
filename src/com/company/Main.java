package com.company;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    final static int lowLevel = 0;
    private static final int highLeve = 25;

    public static void checkFloor(int floor, int lvlLow, int lvlHi, Queue<Integer> queue) throws FloorException {
        if (floor < lvlLow) {
            throw new FloorLowException("the floor value is too small");
        } else if (floor > lvlHi) {
            throw new FloorLowException("the floor value is too high");
        }
        for (Integer queueCheck : queue
        ) {
            if (floor == queueCheck) {
                throw new DoubleChoiceException("the floor has already been selected");
            }
        }
    }

    public static void main(String[] args) throws FloorException {
        int choice = -1;
        Queue<Integer> queue = new PriorityQueue<>();
        Scanner scanner = new Scanner(System.in);
        while (!(choice == 0)) {
            System.out.println("Введите этаж с 1 по 25 и нажмите Enter, что бы завершить введите 0");
            String choiceFloor = scanner.nextLine();
            try {
                choice = Integer.parseInt(choiceFloor);
                checkFloor(choice, lowLevel, highLeve, queue);
                queue.add(choice);
            } catch (FloorLowException FloorException) {
                System.out.println("Слишком маленькое значение, номер этажа не долшен быть меньше: " + lowLevel);
            } catch (FloorHighException FloorException) {
                System.out.println("Слишком высокое значение, номер этажа не долшен быть больше: " + highLeve);
            } catch (NumberFormatException R) {
                System.out.println("Доспускается вводить только цифровые значения в диапазоне от: " + lowLevel + " до: " + highLeve);
            } catch (IllegalStateException R) {
                System.out.println("Лифт сломался нажмите: 0 ");
            } catch (DoubleChoiceException R) {
                System.out.println("Номер этаже уже введен, введите этаж с 1 по 25 за исключением:");
                System.out.println(queue);
            }
        }
        queue.poll();
        int waitDoorsInSeconds = 10;
        int waitMoveInSeconds = 5;
        int totalSeconds = 0;
        int previousFloor = 0;
        int currentFloor = 0;
        System.out.println("Последовательность движения лифта");
        while (!queue.isEmpty()) {
            previousFloor = queue.peek();
            queue.poll();
            if (!queue.isEmpty()) {
                currentFloor = queue.peek();
                System.out.println("Этаж :" + previousFloor + " Следующий этаж: " + currentFloor);
            } else System.out.println("Этаж :" + previousFloor);

            totalSeconds += Math.abs(currentFloor - previousFloor) * waitMoveInSeconds;
            totalSeconds += waitDoorsInSeconds;
        }
        System.out.println("Время затраченное лифтом на маршрут =: " + totalSeconds + " с.");
    }
}
