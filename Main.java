package com.company;
import java.util.Scanner;

public class Main {



    public static void main(String[] args) throws Exception

    //Метод мэйн. создаем объект с помощью библиотеки сканнер. Просим пользователя ввести данные. получаем текстровую строку.
            //Выводим результат
    {

        Scanner sc = new Scanner(System.in);
        System.out.println("Введите через пробел два числа и мат действие: ");
        String phrase1 = sc.nextLine();
        System.out.println(process(phrase1));
    }


    public static String process(String input) throws Exception
            //Объявляем метод, создаем объект класса Конвертер который обрабатывает введенные данные
            //для каждой математической операции пытаемся найти опеатор, находим и вырезаем первое и вторрое введенные числа
            //обьявляем исключения, возващаем результат в виде стринга(это необходимо для вывода римских цифрр.
            // вычисляем результат
            //пользуясь классом Конветерр преобразуем результат в строку
    {
        Converter converter = new Converter();

        Integer plusPosition = input.indexOf("+");

        if (plusPosition > -1)
        {
            String firsthalf = input.substring(0,plusPosition).trim();

            String secondhalf = input.substring(plusPosition + 1).trim();

            Integer x = converter.stringToInt(firsthalf);
            Integer y = converter.stringToInt(secondhalf);

            if (x>10 || y>10)
            {
                throw new Exception("Too big number");
            }

            return converter.intToString(x+y);

        }



        Integer minusPosition = input.indexOf("-");

        if (minusPosition > -1)
        {
            String firsthalf = input.substring(0, minusPosition).trim();

            Integer x = converter.stringToInt(firsthalf);
            String secondhalf = input.substring(minusPosition + 1).trim();
            Integer y = converter.stringToInt(secondhalf);


            if (x>10 || y>10)
            {
                throw new Exception("Too big number");
            }

            if (x-y < 0)
            {
                throw new Exception("Negative is forbidden");
            }

            return converter.intToString(x-y);

        }



        Integer deleniePosition = input.indexOf("/");

        if (deleniePosition > -1)
        {
            String firsthalf = input.substring(0, deleniePosition).trim();

            Integer x = converter.stringToInt(firsthalf);

            String secondhalf = input.substring(deleniePosition + 1).trim();

            Integer y = converter.stringToInt(secondhalf);

            if (x>10 || y>10 || y==0)
            {
                throw new Exception("Too big number or null or division by zero");
            }

            return converter.intToString( x / y);

        }



        Integer umnozhPosition = input.indexOf("*");

        if (umnozhPosition > -1)
        {
            String firsthalf = input.substring(0, umnozhPosition).trim();

            Integer x = converter.stringToInt(firsthalf);

            String secondhalf = input.substring(umnozhPosition + 1).trim();

            Integer y = converter.stringToInt(secondhalf);

            if (x>10 || y>10 || y==0)
            {
                throw new Exception("Too big number or null");
            }

            return converter.intToString( x * y);

        }

        return "Cannot calculate";


    }
}


