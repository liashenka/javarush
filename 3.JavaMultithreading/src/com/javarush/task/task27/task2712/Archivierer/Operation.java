package com.javarush.task.task31.task3110;

public enum Operation {
    CREATE(), //создать архив
    ADD(), //добавить файл в архив
    REMOVE(), //удалить файл из архива
    EXTRACT(), //Извлечь содержимое архива
    CONTENT(), //Просмотреть содержимое архива
    EXIT()  //выйти из программы
}
