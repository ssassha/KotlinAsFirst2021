@file:Suppress("UNUSED_PARAMETER")

package lesson2.task1

import lesson1.task1.discriminant
import kotlin.math.*

// Урок 2: ветвления (здесь), логический тип (см. 2.2).
// Максимальное количество баллов = 6
// Рекомендуемое количество баллов = 5
// Вместе с предыдущими уроками = 9/12

/**
 * Пример
 *
 * Найти число корней квадратного уравнения ax^2 + bx + c = 0
 */
fun quadraticRootNumber(a: Double, b: Double, c: Double): Int {
    val discriminant = discriminant(a, b, c)
    return when {
        discriminant > 0.0 -> 2
        discriminant == 0.0 -> 1
        else -> 0
    }
}

/**
 * Пример
 *
 * Получить строковую нотацию для оценки по пятибалльной системе
 */
fun gradeNotation(grade: Int): String = when (grade) {
    5 -> "отлично"
    4 -> "хорошо"
    3 -> "удовлетворительно"
    2 -> "неудовлетворительно"
    else -> "несуществующая оценка $grade"
}

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    val y3 = max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -sqrt(y3)           // 7
}

/**
 * Простая (2 балла)
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int): String {
    if (age / 10 == 1 || age / 10 == 11) return "$age лет"
    return when (age % 10) {
        0, 5, 6, 7, 8, 9 -> "$age лет"
        2, 3, 4 -> "$age года"
        1 -> "$age год"
        else -> "Несуществующий возраст $age"
    }
}

/**
 * Простая (2 балла)
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(
    t1: Double, v1: Double,
    t2: Double, v2: Double,
    t3: Double, v3: Double
): Double {
    val s = (t1 * v1 + t2 * v2 + t3 * v3) / 2
    val sFirst = t1 * v1
    val sSecond = t2 * v2
    return when {
        s <= sFirst -> s / v1
        s > sFirst && s <= sFirst + sSecond -> (s - sFirst) / v2 + t1
        else -> (s - sFirst - sSecond) / v3 + t1 + t2
    }
}

/**
 * Простая (2 балла)
 *
 * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 * Считать, что ладьи не могут загораживать друг друга
 */
fun whichRookThreatens(
    kingX: Int, kingY: Int,
    rookX1: Int, rookY1: Int,
    rookX2: Int, rookY2: Int
): Int =
    when {
        (kingX == rookX2 || kingY == rookY2) && (kingX == rookX1 || kingY == rookY1)  -> 3
        kingX == rookX1 || kingY == rookY1 -> 1
        kingX == rookX2 || kingY == rookY2 -> 2
        else -> 0
    }

/**
 * Простая (2 балла)
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 * Считать, что ладья и слон не могут загораживать друг друга.
 */
fun rookOrBishopThreatens(
    kingX: Int, kingY: Int,
    rookX: Int, rookY: Int,
    bishopX: Int, bishopY: Int
): Int =
    when {
        (kingX == rookX || kingY == rookY) &&
                (kingX - kingY == bishopX - bishopY ||
                        kingX + kingY == bishopX + bishopY) -> 3
        kingX == rookX || kingY == rookY -> 1
        kingX - kingY == bishopX - bishopY || kingX + kingY == bishopX + bishopY -> 2
        else -> 0
    }
/**
 * Простая (2 балла)
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun triangleKind(a: Double, b: Double, c: Double): Int {
    val sideMax = maxOf(a, b, c)
    val sideMin = minOf(a, b, c)
    val sideMid = when {
        (a == sideMax && b == sideMin) || (a == sideMin && b == sideMax) -> c
        (a == sideMax && c == sideMin) || (a == sideMin && c == sideMax) -> b
        else -> a
    }
    return if (a + b > c && a + c > b && b + c > a) when {
        sideMax.pow(2) < sideMid.pow(2) + sideMin.pow(2) -> 0
        sideMax.pow(2) == sideMid.pow(2) + sideMin.pow(2) -> 1
        sideMax.pow(2) > sideMid.pow(2) + sideMin.pow(2) -> 2
        else -> -1
    }
    else -1
}

/**
 * Средняя (3 балла)
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int =
    when {
        a <= c && c <= b && b <= d -> abs(b - c)
        c <= a && a <= b && b <= d -> abs(b - a)
        c <= a && a <= d && d <= b -> abs(d - a)
        a <= c && c <= d && d <= b -> abs(d - c)
        else -> -1
    }