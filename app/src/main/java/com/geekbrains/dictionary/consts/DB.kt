package com.geekbrains.dictionary.consts

object DB {
    // база данных
    const val VERSION = 1
    const val NAME = "app.db"

    // таблицы
    const val TABLE_SEARCH_HISTORY = "search_history"
    const val TABLE_SEARCH_DATA = "search_data"
    const val TABLE_MEANING = "meaning"
    const val TABLE_FAVORITE = "favorite"

    // столбцы
    const val SEARCH_HISTORY_ID = "search_history_id"
    const val WORD = "word"
    const val DATE = "date"
    const val SEARCH_DATA_ID = "search_data_id"
    const val FINED_TEXT = "fined_text"
    const val MEANING_ID = "meaning_id"
    const val PART_OF_SPEECH_CODE = "part_of_speech_code"
    const val TRANSCRIPTION = "transcription"
    const val IMAGE_URL = "image_rl"
    const val TEXT = "text"
    const val NOTE = "note"
}