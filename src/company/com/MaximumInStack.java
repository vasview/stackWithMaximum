package company.com;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class MaximumInStack {
    //основной стек для хранения данных
    public static Stack<Integer> mainStack = new Stack<>();
    //дополнительный стек для хранения максимальных значений
    public static Stack<Integer> maxNumberStack = new Stack<>();

    public static void main(String[] args) {
        //получаем к-во запросов пользователя
        Integer requestNumber = getRequestNumber();

        while (requestNumber > 0) {
            stackManipulation();
            requestNumber -= 1;
        }

    }

    //Метод для получения к-во запросов
    public static Integer getRequestNumber() {
        Scanner input = new Scanner(System.in);
        System.out.print("Введите к-во запросов : ");
        Integer number = input.nextInt();
        return number;
    }

    //метод для обработки запросов пользователя в стеке
    public static void stackManipulation() {
        //объявляем переменные для хранения значения числа, введеного пользователем
        Integer userNumber;
        //объявляем переменную для получения строки запроса.
        Scanner input = new Scanner(System.in);
        //объявляем массив для временного хранения строки ввода
        Queue<String> commandList = new LinkedList<>();
        //объявляем переменную для получения строки ввода
        String userRequest;

        System.out.print("Введите запрос: ");
        userRequest = input.nextLine();

        //заполняем очередь введенных команд пользователя
        for(String s : userRequest.split(" ")) {
            commandList.add(s);
        }

        //объявляем текущий максимум
        Integer currentMaximum;

        //выполняем требуемое действие пользователя в зависимости от введенной команды
        switch (commandList.poll()) {
            case "push":
                userNumber = Integer.parseInt(commandList.poll());
                //Если стек пустой кладем число в оба стека
                if(mainStack.empty()) {
                    mainStack.push(userNumber);
                    maxNumberStack.push(userNumber);
                } else {
                    //если стек не пустой проверяем число с текущим максимумом
                    //если число больше чем максимум кладем его в оба стека
                    //если число меньше чем максимум, максимум кладем в спомогательный стек, а число в основной
                    mainStack.push(userNumber);
                    currentMaximum = maxNumberStack.peek();
                    if (userNumber > currentMaximum) {
                        maxNumberStack.push(userNumber);
                    } else {
                        maxNumberStack.push(currentMaximum);
                    }
                }
                break;
            case "pop":
                //удаляем число из основного и вспомогателных стеков
                if (!mainStack.empty()) {
                    mainStack.pop();
                    maxNumberStack.pop();
                }
                break;
            case "max":
                //показываем число с верхушки всмопогательного стека, т.к. это есть текущий максимум
                //в основном стеке
                if(!maxNumberStack.empty()) {
                    System.out.println("Текущй максимум: " + maxNumberStack.peek());
                } else {
                    System.out.println("Стек пустой :( ");
                }
        }
    }
}
