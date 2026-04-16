
data class User(
    val id: Int,
    val name: String,
    val bio: String,
    val profileImage: Int = 0,
    val isFrequentlyContacted: Boolean = false
)




val demoUsers = listOf(
    User(1, "Amit", "Available", isFrequentlyContacted = true),
    User(2, "Anita", "Busy"),
    User(2, "Anita", "Busy"),
    User(2, "Anita", "Busy"),
    User(3, "Biren", "At work"),
    User(4, "Chetan", "Driving", isFrequentlyContacted = true),
    User(5, "Chirag", "In a meeting"),
    User(6, "Disha", "Offline"),
    User(7, "Darshan", "Hello there!"),
    User(8, "Eva", "Working on a project"),
    User(9, "Gaurav", "Good morning"),
    User(10, "Harsh", "Available"),
    User(11, "Isha", "Online"),
    User(12, "Jatin", "Call me"),
    User(13, "Karan", "Not available"),
    User(14, "Lina", "Gym time"),
    User(15, "Mehul", "Hey!"),
    User(16, "Nirali", "Sleeping"),
    User(17, "Om", "Available"),
    User(18, "Pooja", "Traveling"),
    User(19, "Qasim", "On break"),
    User(20, "Riya", "Studying"),
    User(21, "Sagar", "Let’s talk"),
    User(22, "Tina", "Working"),
    User(23, "Uma", "At office"),
    User(24, "Vijay", "Chilling"),
    User(25, "Wasim", "In class"),
    User(26, "Xavier", "Online"),
    User(27, "Yash", "Working out"),
    User(28, "Zara", "Good evening"),
    User(29, "123 Group", "Special chat"),
    User(30, "@Friends", "Group chat")
)


data class GroupedUsers(
    val header: String,
    val users: List<User>
)


fun groupUsers(users: List<User>): List<GroupedUsers> {

    val result = mutableListOf<GroupedUsers>()

    // Frequently Contacted
    val frequent = users.filter { it.isFrequentlyContacted }
    if (frequent.isNotEmpty()) {
        result.add(GroupedUsers("Frequently Contacted", frequent))
    }

    // A–Z groups
    ('A'..'Z').forEach { letter ->
        val list = users.filter {
            it.name.first().uppercaseChar() == letter
        }
        if (list.isNotEmpty()) {
            result.add(GroupedUsers(letter.toString(), list))
        }
    }

    // # group for numbers or special characters
    val hashGroup = users.filter {
        !it.name.first().isLetter()
    }
    if (hashGroup.isNotEmpty()) {
        result.add(GroupedUsers("#", hashGroup))
    }

    return result
}

