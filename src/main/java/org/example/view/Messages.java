package org.example.view;

public enum Messages {

    STARTMESSAGE ("""
            Добро пожаловать!
            
            %s
            
            Вам доступны следующие команды:
            
            Создать запись - введите "C"
            Просмотреть запись - "V"
            Изменить запись - "U"
            Удалить запись - "D" 
            """),
    QUESTIONOFNUMBER ("""
            Введите порядковый номер записи!
            """),

    QUESTIONOFTITLE ("""
            Введите заголовок Вашей записи!
            """),

    QUESTIONOFTEXT ("""
            Введите текст записи!
            """),
    OUTPUTMESSAGEALLTASKS ("""
            Ваши сохраненные записи:
            
            %s
            """),
    OUTPUTMESSAGEUPDATETASKS ("""
            Какую запись вы желаете изменить?
            Введите порядковый номер записи!
            
            %s
            """),
    OUTPUTMESSAGEDELETETASKS ("""
            Какую запись вы желаете удалить?
            Введите порядковый номер записи!
            
            %s
            """),
    OUTPUTTASK("""
            Порядковый номер записи - %d
            
            Дата создания записи - %s
            
            Заголовок - %s
            
            Текст записи - %s
            """);


    String value;

    Messages(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
