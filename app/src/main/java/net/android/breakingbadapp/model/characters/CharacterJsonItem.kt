package net.android.breakingbadapp.model.characters

data class CharacterJsonItem(
    val appearance: List<Int>,
    val better_call_saul_appearance: List<Any>,
    val birthday: String,
    val category: String,
    val char_id: Int,
    val img: String,
    val name: String,
    val nickname: String,
    val occupation: List<String>,
    val portrayed: String,
    val status: String
)