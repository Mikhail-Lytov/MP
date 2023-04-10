# Лабораторная работа №4

**Задача**: Реализовать ассоциативный массив(контейнер ***hash map***) на основание hash-таблицы с разрешением коллизий по методу цепочик
# Рассматриваемы вопросы:
- хеш функции и хеш-таблицы
- методы разрешений колизий
- авторизационные оценки сложности

# Указания по выполнению
- лабораторная работа состоит из двух файлов: hash-map.h - описание класса hash мар, прототипы методов, реализация
  методов;
  test-hash-map.cpp - тестирование и проверка возможностей класса;
- класс hash ар содержит три шаблонных [араметра: тип ключа К; тип значения V; тип функции хеширования (по умолчанию стандартная функция используемого языка);
  - каждый элемент контейнера - пара ключ-значение;
  - для хранения элементов воспользоваться стандартными контейнерами
  динамический массив и связный список;
- класс hash_тар содержит следующие поля: 
 
  вектор со списками элементов; число элементов;
 
  число списков;
  
  вещественный коэффициент - максимальный уровень загруженности (по умолчанию равен 2.0);
- класе hash_тар должен содержать следующие методы:

  конструктор;

  удаление всех элементов;

  добавление пары ключ-значение;

  оператор[] или соотв. метод - получает ключ, возвращает ссылку на значение;
  удаление элемента по ключу;

  получение числа элементов; 

  получение и изменение коэффициента загруженности