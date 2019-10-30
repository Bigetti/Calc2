package com.company;

public class Converter
    //Создаем класс Конветер, объявляем пееменные в соостветсвии с типом введенных данных, Аабские цифры, Римские или неизвестный тип
{

    private static final int NUMBER_TYPE_ARABIC = 1;
    private static final int NUMBER_TYPE_ROMAN = 2;
    private static final int NUMBER_TYPE_UNKNOWN = 0;
    private int PreviousNumberType = NUMBER_TYPE_UNKNOWN;


    public static int convertRomToInt(String romnum) throws NumberFormatException
            //Объявляем функцию конвертации Римских цифр в число с помощью функции LetterToNumber подставляем соответствующие цифы и вычисляем число
    {
        int Value = 0;
        int PreviousNumber = 0;


        for (int i = 0; i < romnum.length(); i++)
        {
            char ch = romnum.charAt( i );

            int number = letterToNumber( ch );



            if ( number == -1)
            {
                throw new NumberFormatException("Invalid format");
            }

            if (PreviousNumber < number && PreviousNumber == 1)
            {
                Value = Value - 2*PreviousNumber;    // для исключений, когда меньшая(I) стоит перед большей(V или X) вычитаем ее дважды
            }
            if (PreviousNumber < number && PreviousNumber > 1)
            {
                throw new NumberFormatException("Invalid format");
            }

            Value += number;  //добавляем очередную цифру к числу
            PreviousNumber = number;
        }

        return Value;
    }

    private static int letterToNumber(char letter)
            //Объявляем функцию соответствия Римской цифры Арабской
    {

        switch (letter) {
            case 'I':  return 1;
            case 'V':  return 5;

            case 'X':  return 10;
            case 'L':  return 50;
            case 'C':  return 100;
            default:   return -1;
        }
    }

    public int stringToInt(String input) throws Exception

            //Эта функция преобразует строку в число, провеяя что фомат стрроки тот же что и для первого числа(Римский или Арабский для обоих чисел)
            //Пытаемся преобразовать встроенной функцией, если не получается, значит на входе РРимские цифрры, то вызываем функцию ConvertRomToInt
    {
        int a = 0;

        int CurrentNumberType = NUMBER_TYPE_UNKNOWN;

        try {
            a = Integer.parseInt(input);
            CurrentNumberType = NUMBER_TYPE_ARABIC;
        }
        catch(Exception e)

        {
            a = convertRomToInt(input);
            CurrentNumberType = NUMBER_TYPE_ROMAN;

        }


        if (PreviousNumberType != NUMBER_TYPE_UNKNOWN)
        {
            if (PreviousNumberType != CurrentNumberType)
            {
                throw new Exception("First numberType is different from second!!!!");
            }
        }
        else
        {
            PreviousNumberType = CurrentNumberType;
        }
        return a;

    }


    public String intToString(int b)
            //С помощью этой функции, преобразуем число в стринг
            // если на входе были Арабские числа, то просто перреводим инт в стринг встоенной функцией,
            //во втором варианте генерирруем строку с римскими цифрами из числа.
    {
        String Result = "";
        if (PreviousNumberType == NUMBER_TYPE_ARABIC)
            {
             Result = String.valueOf(b);
            }
        else
            {
                String letters[]  = { "C", "L", "X", "IX", "V", "IV", "I"};
                int numbers[]  = {100, 50, 10, 9, 5, 4, 1};

                for (int i=0; i < numbers.length; i++)
                {
                    int Howmany = b / numbers[i];  //получаем целый результат без остатка

                    b -= Howmany * numbers[i];

                    while (Howmany>0)
                    {
                        Result = Result + letters[i];    //добавляем символ или символы в конец строки
                        Howmany -- ;
                    }

                }
            }
        return Result;
    }

}

