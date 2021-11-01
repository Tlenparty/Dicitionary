package com.geekbrains.dictionary.data.entities

import com.google.gson.annotations.SerializedName

data class  SearchData(
    @SerializedName("text")
    val finedText: String,

    @SerializedName("meanings")
    val translates: List<Meaning>
)


/*
{
    "id": 170881,
    "text": "Allow me to help you?",
    "meanings": [
    {
        "id": 244585,
        "partOfSpeechCode": "ph",
        "translation": {
        "text": "Разрешите Вам помочь?",
        "note": ""
    },
        "previewUrl": "//d2zkmv5t5kao9.cloudfront.net/images/57714d9b13720608e6e3b08e58e7d523.jpeg?w=96&h=72",
        "imageUrl": "//d2zkmv5t5kao9.cloudfront.net/images/57714d9b13720608e6e3b08e58e7d523.jpeg?w=640&h=480",
        "transcription": "əˈlaʊ miː tuː hɛlp juː",
        "soundUrl": "//d2fmfepycn0xw0.cloudfront.net?gender=male&accent=british&text=%C9%99%CB%88la%CA%8A+mi%CB%90+tu%CB%90+h%C9%9Blp+ju%CB%90&transcription=1"
    }
    ]
},
{
    "id": 95416,
    "text": "Hudson River",
    "meanings": [
    {
        "id": 137770,
        "partOfSpeechCode": "n",
        "translation": {
        "text": "Гудзон ",
        "note": null
    },
        "previewUrl": "//d2zkmv5t5kao9.cloudfront.net/images/cce5fc29481a142d05e56288b1bb202f.jpeg?w=96&h=72",
        "imageUrl": "//d2zkmv5t5kao9.cloudfront.net/images/cce5fc29481a142d05e56288b1bb202f.jpeg?w=640&h=480",
        "transcription": "ˈhʌds(ə)n ˈrɪvə",
        "soundUrl": "//d2fmfepycn0xw0.cloudfront.net?gender=male&accent=british&text=Hudson+River"
    }
    ]
},
*/
