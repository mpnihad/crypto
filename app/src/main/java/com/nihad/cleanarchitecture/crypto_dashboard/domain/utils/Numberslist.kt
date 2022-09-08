
fun main(){
    val solution:Solution= Solution()
    println(solution.romanToInt("MCMXCIV"))
    var numbers=listOf(1,2,3,4,5)
    numbers.getNumberToWord (::toWord).map {
        println(it)
    }

    numbers.getNumberToWord (::toWord2).map {
        println(it)
    }


}

fun toWord( i:Int)= when (i) {
    1->"ONE"
    2->"TWO"
    3->"THREE"
    4->"FOUR"
    5->"FIVE"
    else ->"-"
}
fun toWord2( i:Int)= when (i) {
    1->"onnu"
    2->"randu"
    3->"moonu"
    4->"nalu"
    5->"anju"
    else ->"-"
}

fun List<Int>.getNumberToWord( c:(Int)->String):List<String>{

    val listOfChar:MutableList<String> = mutableListOf()
    this.map {
        listOfChar.add( c(it))
    }

    return listOfChar



}
class Solution {

    fun romanToInt(s: String): Int {

        var previousChar="";
        var totalSum=0;
        if(s.length in 1..15)
        {
            s.forEach {

                //I             1
                //V             5
                //X             10
                //             50
                //             100
                //M             1000

                when (it.toString()){

                    "I" -> {
                        totalSum += 1
                    }
                    "V" -> {

                        if(previousChar=="I"){
                            totalSum=totalSum+4-1
                        }
                        else{
                            totalSum+=5
                        }

                    }
                    "X" -> {
                        if(previousChar=="I"){
                            totalSum=totalSum+9-1
                        }
                        else{
                            totalSum+=10
                        }
                    }
                    "L" -> {
                        if(previousChar=="X"){
                            totalSum=totalSum+40-10
                        }
                        else{
                            totalSum+=50
                        }
                    }
                    "C" -> {
                        if(previousChar=="X"){
                            totalSum=totalSum+90-10
                        }
                        else{
                            totalSum+=100
                        }
                    }
                    "D" -> {
                        if(previousChar=="C"){
                            totalSum=totalSum+400-100
                        }
                        else{
                            totalSum+=500
                        }
                    }
                    "M" -> {
                        if(previousChar=="C"){
                            totalSum=totalSum+900-100
                        }
                        else{
                            totalSum+=1000
                        }
                    }
                }
                previousChar=it.toString()

            }
        }
        return totalSum
    }
}