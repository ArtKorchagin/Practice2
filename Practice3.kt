import java.util.*
open class person(var name: String, var birthYear: Int) {
    var age:Int = Calendar.getInstance().get(Calendar.YEAR) - birthYear
}

class student(name:String, birthYear: Int, var averageGrade: Float, var extramural: Boolean = false):person(name, birthYear)

class lecturer(name:String, birthYear: Int, var degree: String, var experienceFrom: Int):person(name, birthYear)


fun MutableList<person>.sortByAge():List<person>
{
    return this.sortedByDescending { it.age }
}


fun MutableList<person>.sortByNameStudents():List<student>
{
    return this.filter{ it is student }.sortedByDescending{ it.name } as List<student>
}


fun MutableList<person>.sortByAverageGrade(exceptExtramural : Boolean):List<student>
{
    return if (!exceptExtramural)
    		(this.filter{ it is student } as List<student>).sortedByDescending{ it.averageGrade }
    		else (this.filter{ it is student } as List<student>).filter{ it.extramural }.sortedByDescending{ it.averageGrade }
}
 
fun printPerson(info: List<person>): Unit
{
    info.forEach {
    	println("Name: ${it.name}; Age: ${it.age}")
	}
} 

fun printStudent(info: List<student>): Unit
{
    info.forEach {
    	println("Name: ${it.name}; Age: ${it.age}; averageGrade: ${it.averageGrade}; extramural: ${it.extramural}")
	}
}
  
fun main() {
    var info = mutableListOf<person>()
    info.add(student("Balyaikin Ilya", 2000, 4.9f))
    info.add(student("Karpov Michael", 2000, 4.2f, true))
    info.add(student("Korzhov Alex", 2001, 3.4f))
    info.add(student("Tremaskin Kirill", 1999, 4.0f))
    info.add(student("Barabishkin Dmitry", 2003, 3.8f, true))
    info.add(lecturer("Koshkin Igor", 1975, "PhD in Mathematics", 1993))
    info.add(lecturer("Petrov Peter", 1978, "PhD in Mathematics", 1991))
    info.add(lecturer("Pushkin Alex", 1970, "PhD in Mathematics", 1989))
    info.add(lecturer("Ivanov Ivan", 1972, "PhD in Physics", 1993))
    info.add(lecturer("Ilya Ilyich", 1964, "PhD in Physics", 1984))
    
    println("Исходные данные:")
    printPerson(info)
    println("Сортировка по возрасту")
    printPerson(info.sortByAge())
    println("Сортировка студентов по имени")
    printStudent(info.sortByNameStudents())
    println("Сортировка по средней оценке")
    printStudent(info.sortByAverageGrade(true))
}