import java.text.SimpleDateFormat
import java.util.*

fun main() {
    val timestamp = "2023-03-09T08:00:05.000Z"

    // Define input and output date formats
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    val outputFormat = SimpleDateFormat("dd MM yyyy", Locale.getDefault())

    // Parse the input timestamp to a Date object
    val date = inputFormat.parse(timestamp)

    // Format the Date object to the desired output format
    val formattedDate = outputFormat.format(date)

    // Output the formatted date
    println(formattedDate)
}