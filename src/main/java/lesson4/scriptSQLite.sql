/* 1.Создание таблиц */

DROP TABLE IF EXISTS movies;

CREATE TABLE IF NOT EXISTS movies (
    id         INTEGER       PRIMARY KEY AUTOINCREMENT,
    movie_name VARCHAR (255) NOT NULL,
    duration   INTEGER       NOT NULL
                             CHECK (duration IN (60, 90, 120) )
);

INSERT INTO movies (movie_name, duration) VALUES ('Movie1', 60), ('Movie2', 90), ('Movie3', 120), ('Movie4', 60),
                                                 ('Movie5', 90);

SELECT * FROM movies;


DROP TABLE IF EXISTS showtimes;

CREATE TABLE IF NOT EXISTS showtimes (
    id         INTEGER        PRIMARY KEY AUTOINCREMENT,
    movie_id   INTEGER        REFERENCES movies (id) ON DELETE CASCADE,
    start_time TIMESTAMP      NOT NULL,
    price      INTEGER        NOT NULL
);

INSERT INTO showtimes (movie_id, start_time, price) VALUES (5, '2023-03-29 10:00:00', 100),
                                                           (4, '2023-03-29 10:30:00', 200),
                                                           (3, '2023-03-29 13:00:00', 300),
                                                           (2, '2023-03-29 14:30:00', 400),
                                                           (1, '2023-03-29 15:00:00', 500),
                                                           (5, '2023-03-29 16:00:00', 400);

SELECT * FROM showtimes;


DROP TABLE IF EXISTS tickets;

CREATE TABLE IF NOT EXISTS tickets (
    id          INTEGER PRIMARY KEY AUTOINCREMENT,
    showtime_id INTEGER REFERENCES showtimes (id) ON DELETE CASCADE
);

INSERT INTO tickets (showtime_id) VALUES (1), (1), (2), (3), (3), (3), (3), (3), (4), (5), (5), (6), (6);

SELECT * FROM tickets;


/* 2.Ошибки в расписании (фильмы накладываются друг на друга), отсортированные по возрастанию времени.
Выводить надо колонки «фильм 1», «время начала», «длительность», «фильм 2», «время начала», «длительность» */

SELECT m1.movie_name AS [фильм 1],
       s1.start_time AS [время начала],
       m1.duration AS длительность,
       m2.movie_name AS [фильм 2],
       s2.start_time AS [время начала],
       m2.duration AS длительность
FROM showtimes s1
    JOIN
    movies m1 ON s1.movie_id = m1.id
    JOIN
    showtimes s2 ON s1.start_time < s2.start_time AND
    (s1.start_time + m1.duration) > s2.start_time
    JOIN
    movies m2 ON s2.movie_id = m2.id
WHERE s1.start_time <> s2.start_time
ORDER BY s1.start_time;


/* 3.Перерывы 30 минут и более между фильмами — выводить по уменьшению длительности перерыва.
    Колонки «фильм 1», «время начала», «длительность», «время начала второго фильма», «длительность перерыва» */

SELECT
    m1.movie_name as "фильм 1",
    s1.start_time as "время начала",
    m1.duration as "длительность",
    s2.start_time as "время начала второго фильма",
    (s2.start_time - (s1.start_time + m1.duration))/60 as "длительность перерыва (мин)"
    FROM
        showtimes s1
        JOIN
    movies m1
    ON
            s1.movie_id = m1.id
        JOIN
    showtimes s2
    ON
                s1.start_time < s2.start_time
            AND s2.start_time - (s1.start_time + m1.duration) >= '30 minutes'
    AND s1.id < s2.id
    AND s1.id = (SELECT MAX(id) FROM showtimes WHERE id < s2.id)
ORDER BY
    "длительность перерыва (мин)" DESC;


/* 4.Список фильмов, для каждого — с указанием общего числа посетителей за все время, среднего числа зрителей за сеанс
  и общей суммы сборов по каждому фильму (отсортировать по убыванию прибыли). Внизу таблицы должна быть строчка «итого»,
  содержащая данные по всем фильмам сразу */

CREATE TEMPORARY TABLE IF NOT EXISTS temp_movie_stats AS
SELECT
    m.movie_name,
    COUNT(id) AS total_visitors,
    ROUND(AVG(visitors_per_showtime), 2) AS average_visitors_per_showtime,
    SUM(revenue) AS total_revenue
FROM movies m
         JOIN (
    SELECT
        s.movie_id,
        s.price * COUNT(t.id) AS revenue,
        COUNT(t.id) AS visitors_per_showtime
    FROM showtimes s
             LEFT JOIN tickets t ON s.id = t.showtime_id
    GROUP BY s.movie_id, s.id
) AS s ON m.id = s.movie_id
GROUP BY m.movie_name
ORDER BY total_revenue DESC;

SELECT movie_name, total_visitors, average_visitors_per_showtime, total_revenue
FROM temp_movie_stats
UNION
SELECT 'итого', SUM(total_visitors), ROUND(AVG(average_visitors_per_showtime), 2), SUM(total_revenue)
FROM temp_movie_stats;


/* 5.Число посетителей и кассовые сборы, сгруппированные по времени начала фильма: с 9 до 15, с 15 до 18, с 18 до 21,
  с 21 до 00:00 (сколько посетителей пришло с 9 до 15 часов и т.д.) */

SELECT
    CASE
        WHEN start_time BETWEEN '2023-03-29 09:00:00' AND '2023-03-29 15:00:00' THEN 'с 9 до 15'
        WHEN start_time BETWEEN '2023-03-29 15:00:01' AND '2023-03-29 18:00:00' THEN 'с 15 до 18'
        WHEN start_time BETWEEN '2023-03-29 18:00:01' AND '2023-03-29 21:00:00' THEN 'с 18 до 21'
        WHEN start_time BETWEEN '2023-03-29 21:00:01' AND '2023-03-30 00:00:00' THEN 'с 21 до 00'
        END AS interval,
  COUNT(DISTINCT t.id) AS visitors,
  SUM(s.price) AS revenue
FROM showtimes s
    JOIN tickets t ON s.id = t.showtime_id
GROUP BY interval;