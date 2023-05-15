package com.dp.a360quiz.data.database.default_data

import android.content.Context
import com.dp.a360quiz.di.module.IoDispatcher
import com.google.gson.Gson
import com.google.gson.JsonObject
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import javax.inject.Inject

private const val VERSION = "version"

class DefaultQuestionsProviderImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val gson: Gson,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : DefaultQuestionsProvider {

    override suspend fun getNewQuestions(lastSavedVersion: Int): DefaultQuestions? =
        withContext(ioDispatcher) {
            val inputStream = questions.byteInputStream() // context.assets.open("questions.json")
            val jsonString = inputStream.bufferedReader().use(BufferedReader::readText)
            val jsonObject = gson.fromJson(jsonString, JsonObject::class.java)
            val version = jsonObject.getAsJsonPrimitive(VERSION).asInt
            return@withContext if (version > lastSavedVersion)
                gson.fromJson(jsonObject, DefaultQuestions::class.java)
            else null
        }


}

val questions = """
    {
      "version": 2,
      "questions": [
        {
          "id": 1,
          "type": "four_answers",
          "category": "Geography",
          "difficulty": 1,
          "question": "What is the capital city of Australia?",
          "answers": [
            {
              "answer": "Sydney",
              "is_correct": false
            },
            {
              "answer": "Melbourne",
              "is_correct": false
            },
            {
              "answer": "Canberra",
              "is_correct": true
            },
            {
              "answer": "Perth",
              "is_correct": false
            }
          ]
        },
        {
          "id": 2,
          "type": "four_answers",
          "category": "History",
          "difficulty": 1,
          "question": "Who was the first president of the United States?",
          "answers": [
            {
              "answer": "George Washington",
              "is_correct": true
            },
            {
              "answer": "Thomas Jefferson",
              "is_correct": false
            },
            {
              "answer": "Abraham Lincoln",
              "is_correct": false
            },
            {
              "answer": "Franklin D. Roosevelt",
              "is_correct": false
            }
          ]
        },
        {
          "id": 3,
          "type": "four_answers",
          "category": "Literature",
          "difficulty": 1,
          "question": "Who wrote the novel 'To Kill a Mockingbird'?",
          "answers": [
            {
              "answer": "Harper Lee",
              "is_correct": true
            },
            {
              "answer": "F. Scott Fitzgerald",
              "is_correct": false
            },
            {
              "answer": "Ernest Hemingway",
              "is_correct": false
            },
            {
              "answer": "Mark Twain",
              "is_correct": false
            }
          ]
        },
        {
          "id": 4,
          "type": "four_answers",
          "category": "Science",
          "difficulty": 1,
          "question": "What is the chemical symbol for gold?",
          "answers": [
            {
              "answer": "Ag",
              "is_correct": false
            },
            {
              "answer": "Au",
              "is_correct": true
            },
            {
              "answer": "Cu",
              "is_correct": false
            },
            {
              "answer": "Pb",
              "is_correct": false
            }
          ]
        },
        {
          "id": 5,
          "type": "four_answers",
          "category": "Art",
          "difficulty": 1,
          "question": "Who painted the Mona Lisa?",
          "answers": [
            {
              "answer": "Leonardo da Vinci",
              "is_correct": true
            },
            {
              "answer": "Michelangelo",
              "is_correct": false
            },
            {
              "answer": "Raphael",
              "is_correct": false
            },
            {
              "answer": "Titian",
              "is_correct": false
            }
          ]
        },
        {
          "id": 6,
          "type": "four_answers",
          "category": "Geography",
          "difficulty": 1,
          "question": "What is the largest ocean in the world?",
          "answers": [
            {
              "answer": "Atlantic Ocean",
              "is_correct": false
            },
            {
              "answer": "Pacific Ocean",
              "is_correct": true
            },
            {
              "answer": "Indian Ocean",
              "is_correct": false
            },
            {
              "answer": "Arctic Ocean",
              "is_correct": false
            }
          ]
        },
        {
          "id": 7,
          "type": "four_answers",
          "category": "History",
          "difficulty": 1,
          "question": "When did World War II end?",
          "answers": [
            {
              "answer": "1945",
              "is_correct": true
            },
            {
              "answer": "1939",
              "is_correct": false
            },
            {
              "answer": "1941",
              "is_correct": false
            },
            {
              "answer": "1947",
              "is_correct": false
            }
          ]
        },
        {
          "id": 8,
          "type": "four_answers",
          "category": "Literature",
          "difficulty": 1,
          "question": "Who wrote the Harry Potter series?",
          "answers": [
            {
              "answer": "J.K. Rowling",
              "is_correct": true
            },
            {
              "answer": "Stephenie Meyer",
              "is_correct": false
            },
            {
              "answer": "Suzanne Collins",
              "is_correct": false
            },
            {
              "answer": "Rick Riordan",
              "is_correct": false
            }
          ]
        },
        {
          "id": 9,
          "type": "four_answers",
          "category": "Science",
          "difficulty": 1,
          "question": "What is the chemical symbol for Iron?",
          "answers": [
            {
              "answer": "Fe",
              "is_correct": true
            },
            {
              "answer": "Co",
              "is_correct": false
            },
            {
              "answer": "Co",
              "is_correct": false
            },
            {
              "answer": "Ni",
              "is_correct": false
            }
          ]
        },
        {
          "id": 10,
          "type": "four_answers",
          "category": "History",
          "difficulty": 2,
          "question": "Who was the first president of the United States?",
          "answers": [
            {
              "answer": "George Washington",
              "is_correct": true
            },
            {
              "answer": "John Adams",
              "is_correct": false
            },
            {
              "answer": "Thomas Jefferson",
              "is_correct": false
            },
            {
              "answer": "James Madison",
              "is_correct": false
            }
          ]
        },
        {
          "id": 11,
          "type": "four_answers",
          "category": "Science",
          "difficulty": 2,
          "question": "What is the largest planet in our solar system?",
          "answers": [
            {
              "answer": "Jupiter",
              "is_correct": true
            },
            {
              "answer": "Saturn",
              "is_correct": false
            },
            {
              "answer": "Uranus",
              "is_correct": false
            },
            {
              "answer": "Neptune",
              "is_correct": false
            }
          ]
        },
        {
          "id": 12,
          "type": "four_answers",
          "category": "Geography",
          "difficulty": 2,
          "question": "What is the capital of France?",
          "answers": [
            {
              "answer": "Paris",
              "is_correct": true
            },
            {
              "answer": "Marseille",
              "is_correct": false
            },
            {
              "answer": "Lyon",
              "is_correct": false
            },
            {
              "answer": "Toulouse",
              "is_correct": false
            }
          ]
        },
        {
          "id": 13,
          "type": "four_answers",
          "category": "Literature",
          "difficulty": 2,
          "question": "Who wrote the book 'To Kill a Mockingbird'?",
          "answers": [
            {
              "answer": "Harper Lee",
              "is_correct": true
            },
            {
              "answer": "F. Scott Fitzgerald",
              "is_correct": false
            },
            {
              "answer": "Ernest Hemingway",
              "is_correct": false
            },
            {
              "answer": "Mark Twain",
              "is_correct": false
            }
          ]
        },
        {
          "id": 14,
          "type": "four_answers",
          "category": "History",
          "difficulty": 3,
          "question": "What was the first successful permanent photographic process?",
          "answers": [
            {
              "answer": "Daguerreotype",
              "is_correct": true
            },
            {
              "answer": "Calotype",
              "is_correct": false
            },
            {
              "answer": "Ambrotype",
              "is_correct": false
            },
            {
              "answer": "Tintype",
              "is_correct": false
            }
          ]
        },
        {
          "id": 15,
          "type": "four_answers",
          "category": "Geography",
          "difficulty": 3,
          "question": "What is the largest island in the world by area?",
          "answers": [
            {
              "answer": "Greenland",
              "is_correct": true
            },
            {
              "answer": "New Guinea",
              "is_correct": false
            },
            {
              "answer": "Borneo",
              "is_correct": false
            },
            {
              "answer": "Madagascar",
              "is_correct": false
            }
          ]
        },
        {
          "id": 16,
          "type": "four_answers",
          "category": "Science",
          "difficulty": 3,
          "question": "What element is produced when a neutron is added to a nucleus of hydrogen-2?",
          "answers": [
            {
              "answer": "Helium-3",
              "is_correct": true
            },
            {
              "answer": "Deuterium",
              "is_correct": false
            },
            {
              "answer": "Tritium",
              "is_correct": false
            },
            {
              "answer": "Hydrogen-3",
              "is_correct": false
            }
          ]
        },
        {
          "id": 17,
          "type": "four_answers",
          "category": "Literature",
          "difficulty": 3,
          "question": "Who wrote the novel 'To Kill a Mockingbird'?",
          "answers": [
            {
              "answer": "Harper Lee",
              "is_correct": true
            },
            {
              "answer": "Mark Twain",
              "is_correct": false
            },
            {
              "answer": "F. Scott Fitzgerald",
              "is_correct": false
            },
            {
              "answer": "Ernest Hemingway",
              "is_correct": false
            }
          ]
        },
        {
          "id": 18,
          "type": "four_answers",
          "category": "Art",
          "difficulty": 3,
          "question": "Who painted the famous artwork 'The Starry Night'?",
          "answers": [
            {
              "answer": "Vincent van Gogh",
              "is_correct": true
            },
            {
              "answer": "Pablo Picasso",
              "is_correct": false
            },
            {
              "answer": "Leonardo da Vinci",
              "is_correct": false
            },
            {
              "answer": "Rembrandt",
              "is_correct": false
            }
          ]
        },
        {
          "id": 19,
          "type": "four_answers",
          "category": "History",
          "difficulty": 3,
          "question": "Who was the first president of the United States?",
          "answers": [
            {
              "answer": "George Washington",
              "is_correct": true
            },
            {
              "answer": "Thomas Jefferson",
              "is_correct": false
            },
            {
              "answer": "Abraham Lincoln",
              "is_correct": false
            },
            {
              "answer": "Franklin D. Roosevelt",
              "is_correct": false
            }
          ]
        },
        {
          "id": 20,
          "type": "four_answers",
          "category": "Science",
          "difficulty": 3,
          "question": "What planet is referred to as the 'Red Planet'?",
          "answers": [
            {
              "answer": "Mars",
              "is_correct": true
            },
            {
              "answer": "Jupiter",
              "is_correct": false
            },
            {
              "answer": "Venus",
              "is_correct": false
            },
            {
              "answer": "Saturn",
              "is_correct": false
            }
          ]
        },
        {
          "id": 21,
          "type": "four_answers",
          "category": "Literature",
          "difficulty": 3,
          "question": "Who wrote the novel 'One Hundred Years of Solitude'?",
          "answers": [
            {
              "answer": "Gabriel Garcia Marquez",
              "is_correct": true
            },
            {
              "answer": "Ernest Hemingway",
              "is_correct": false
            },
            {
              "answer": "F. Scott Fitzgerald",
              "is_correct": false
            },
            {
              "answer": "John Steinbeck",
              "is_correct": false
            }
          ]
        },
        {
          "id": 22,
          "type": "four_answers",
          "category": "History",
          "difficulty": 3,
          "question": "What was the name of the Roman empire's first permanent military base in England?",
          "answers": [
            {
              "answer": "Hadrian's Wall",
              "is_correct": false
            },
            {
              "answer": "The Antonine Wall",
              "is_correct": false
            },
            {
              "answer": "The Caerleon Fortress",
              "is_correct": false
            },
            {
              "answer": "The Fortress of Inchtuthil",
              "is_correct": true
            }
          ]
        },
        {
          "id": 23,
          "type": "four_answers",
          "category": "Biology",
          "difficulty": 3,
          "question": "What is the name of the enzyme that breaks down lactose in the human digestive system?",
          "answers": [
            {
              "answer": "Lactase",
              "is_correct": true
            },
            {
              "answer": "Amylase",
              "is_correct": false
            },
            {
              "answer": "Protease",
              "is_correct": false
            },
            {
              "answer": "Lipase",
              "is_correct": false
            }
          ]
        },
        {
          "id": 24,
          "type": "four_answers",
          "category": "Music",
          "difficulty": 3,
          "question": "Which classical composer wrote the opera 'The Barber of Seville'?",
          "answers": [
            {
              "answer": "Wolfgang Amadeus Mozart",
              "is_correct": false
            },
            {
              "answer": "Ludwig van Beethoven",
              "is_correct": false
            },
            {
              "answer": "Gioachino Rossini",
              "is_correct": true
            },
            {
              "answer": "Frédéric Chopin",
              "is_correct": false
            }
          ]
        },
        {
          "id": 25,
          "type": "four_answers",
          "difficulty": 3,
          "category": "Geography",
          "question": "What is the name of the largest desert in the world?",
          "answers": [
            {
              "answer": "Sahara Desert",
              "is_correct": true
            },
            {
              "answer": "Gobi Desert",
              "is_correct": false
            },
            {
              "answer": "Arabian Desert",
              "is_correct": false
            },
            {
              "answer": "Kalahari Desert",
              "is_correct": false
            }
          ]
        },
        {
          "id": 26,
          "type": "four_answers",
          "category": "History",
          "difficulty": 3,
          "question": "Which year did the first Apple iPhone go on sale?",
          "answers": [
            {
              "answer": "2007",
              "is_correct": true
            },
            {
              "answer": "2001",
              "is_correct": false
            },
            {
              "answer": "2005",
              "is_correct": false
            },
            {
              "answer": "2015",
              "is_correct": false
            }
          ]
        },
        {
          "id": 27,
          "type": "four_answers",
          "category": "Geography",
          "difficulty": 3,
          "question": "Where the Madagascar island located",
          "answers": [
            {
              "answer": "Africa",
              "is_correct": true
            },
            {
              "answer": "Europe",
              "is_correct": false
            },
            {
              "answer": "Australia",
              "is_correct": false
            },
            {
              "answer": "America",
              "is_correct": false
            }
          ]
        },
        {
          "id": 28,
          "type": "four_answers",
          "category": "History",
          "difficulty": 3,
          "question": "In what year did the first man walk on the moon?",
          "answers": [
            {
              "answer": "1969",
              "is_correct": true
            },
            {
              "answer": "1961",
              "is_correct": false
            },
            {
              "answer": "1965",
              "is_correct": false
            },
            {
              "answer": "1967",
              "is_correct": false
            }
          ]
        },
        {
          "id": 29,
          "type": "four_answers",
          "category": "History",
          "difficulty": 3,
          "question": "What is the formula for water?",
          "answers": [
            {
              "answer": "H2O",
              "is_correct": true
            },
            {
              "answer": "O2H",
              "is_correct": false
            },
            {
              "answer": "OH2",
              "is_correct": false
            },
            {
              "answer": "H20",
              "is_correct": false
            }
          ]
        },
        {
          "id": 30,
          "type": "four_answers",
          "category": "Geography",
          "difficulty": 3,
          "question": "What is the smallest country in the world by land area?",
          "answers": [
            {
              "answer": "Vatican City",
              "is_correct": true
            },
            {
              "answer": "Monaco",
              "is_correct": false
            },
            {
              "answer": "San Marino",
              "is_correct": false
            },
            {
              "answer": "Nauru",
              "is_correct": false
            }
          ]
        },
        {
          "id": 31,
          "type": "four_answers",
          "category": "Geography",
          "difficulty": 3,
          "question": "What is the name of the highest mountain in the solar system?",
          "answers": [
            {
              "answer": "Olympus Mons",
              "is_correct": true
            },
            {
              "answer": "Everest",
              "is_correct": false
            },
            {
              "answer": "K2",
              "is_correct": false
            },
            {
              "answer": "Mount Kilimanjaro",
              "is_correct": false
            }
          ]
        },
        {
          "id": 32,
          "type": "four_answers",
          "category": "Science",
          "difficulty": 3,
          "question": "What is the unit of electric charge?",
          "answers": [
            {
              "answer": "Coulomb",
              "is_correct": true
            },
            {
              "answer": "Volt",
              "is_correct": false
            },
            {
              "answer": "Ampere",
              "is_correct": false
            },
            {
              "answer": "Ohm",
              "is_correct": false
            }
          ]
        },
        {
          "id": 33,
          "type": "four_answers",
          "category": "Science",
          "difficulty": 3,
          "question": "What is the formula for water?",
          "answers": [
            {
              "answer": "H2O",
              "is_correct": true
            },
            {
              "answer": "O2H",
              "is_correct": false
            },
            {
              "answer": "OH2",
              "is_correct": false
            },
            {
              "answer": "H20",
              "is_correct": false
            }
          ]
        },
        {
          "id": 34,
          "type": "four_answers",
          "category": "Geography",
          "difficulty": 3,
          "question": "What is the smallest country in the world by land area?",
          "answers": [
            {
              "answer": "Vatican City",
              "is_correct": true
            },
            {
              "answer": "Monaco",
              "is_correct": false
            },
            {
              "answer": "San Marino",
              "is_correct": false
            },
            {
              "answer": "Nauru",
              "is_correct": false
            }
          ]
        },
        {
          "id": 35,
          "type": "four_answers",
          "category": "Geography",
          "difficulty": 3,
          "question": "What is the name of the highest mountain in the solar system?",
          "answers": [
            {
              "answer": "Olympus Mons",
              "is_correct": true
            },
            {
              "answer": "Everest",
              "is_correct": false
            },
            {
              "answer": "K2",
              "is_correct": false
            },
            {
              "answer": "Mount Kilimanjaro",
              "is_correct": false
            }
          ]
        },
        {
          "id": 36,
          "type": "four_answers",
          "category": "Geography",
          "difficulty": 3,
          "question": "What is the unit of electric charge?",
          "answers": [
            {
              "answer": "Coulomb",
              "is_correct": true
            },
            {
              "answer": "Volt",
              "is_correct": false
            },
            {
              "answer": "Ampere",
              "is_correct": false
            },
            {
              "answer": "Ohm",
              "is_correct": false
            }
          ]
        },
        {
          "id": 37,
          "type": "four_answers",
          "category": "History",
          "difficulty": 3,
          "question": "What was the outcome of the Battle of Waterloo?",
          "answers": [
            {
              "answer": "French victory",
              "is_correct": false
            },
            {
              "answer": "Allied victory",
              "is_correct": true
            },
            {
              "answer": "Draw",
              "is_correct": false
            },
            {
              "answer": "French victory, later overturned",
              "is_correct": false
            }
          ]
        },
        {
          "id": 38,
          "type": "four_answers",
          "category": "Geography",
          "difficulty": 3,
          "question": "What is the highest mountain peak in North America?",
          "answers": [
            {
              "answer": "Mount Denali",
              "is_correct": true
            },
            {
              "answer": "Mount Rainier",
              "is_correct": false
            },
            {
              "answer": "Mount Whitney",
              "is_correct": false
            },
            {
              "answer": "Mount Logan",
              "is_correct": false
            }
          ]
        },
        {
          "id": 39,
          "type": "four_answers",
          "category": "Science",
          "difficulty": 3,
          "question": "What is the largest cell in the human body?",
          "answers": [
            {
              "answer": "Red blood cell",
              "is_correct": false
            },
            {
              "answer": "Egg cell",
              "is_correct": true
            },
            {
              "answer": "White blood cell",
              "is_correct": false
            },
            {
              "answer": "Nerve cell",
              "is_correct": false
            }
          ]
        },
        {
          "id": 40,
          "type": "four_answers",
          "category": "Literature",
          "difficulty": 3,
          "question": "Who wrote the novel 'A Tale of Two Cities'?",
          "answers": [
            {
              "answer": "Jane Austen",
              "is_correct": false
            },
            {
              "answer": "Charles Dickens",
              "is_correct": true
            },
            {
              "answer": "Emily Bronte",
              "is_correct": false
            },
            {
              "answer": "Mary Shelley",
              "is_correct": false
            }
          ]
        },
        {
          "id": 41,
          "type": "four_answers",
          "category": "History",
          "difficulty": 3,
          "question": "What was the name of the first moon landing mission by NASA?",
          "answers": [
            {
              "answer": "Apollo 8",
              "is_correct": false
            },
            {
              "answer": "Robert 8",
              "is_correct": false
            },
            {
              "answer": "Apollon 8",
              "is_correct": true
            },
            {
              "answer": "Apple 8",
              "is_correct": false
            }
          ]
        },
        {
          "id": 42,
          "type": "four_answers",
          "category": "History",
          "difficulty": 3,
          "question": "What was the name of the first moon landing mission by NASA?",
          "answers": [
            {
              "answer": "Apollo 8",
              "is_correct": false
            },
            {
              "answer": "Robert 8",
              "is_correct": false
            },
            {
              "answer": "Apollon 8",
              "is_correct": true
            },
            {
              "answer": "Apple 8",
              "is_correct": false
            }
          ]
        },
        {
          "id": 43,
          "type": "four_answers",
          "category": "History",
          "difficulty": 3,
          "question": "What was the first country to adopt Christianity as its state religion?",
          "answers": [
            {
              "answer": "Ethiopia",
              "is_correct": false
            },
            {
              "answer": "Armenia",
              "is_correct": true
            },
            {
              "answer": "Egypt",
              "is_correct": false
            },
            {
              "answer": "Greece",
              "is_correct": false
            }
          ]
        },
        {
          "id": 44,
          "type": "four_answers",
          "category": "Geography",
          "difficulty": 3,
          "question": "What is the longest river in South America?",
          "answers": [
            {
              "answer": "Parana",
              "is_correct": false
            },
            {
              "answer": "Orinoco",
              "is_correct": false
            },
            {
              "answer": "Amazon",
              "is_correct": true
            },
            {
              "answer": "Ucayali",
              "is_correct": false
            }
          ]
        },
        {
          "id": 45,
          "type": "four_answers",
          "category": "Science",
          "difficulty": 3,
          "question": "Which planet in our solar system has the most number of moons?",
          "answers": [
            {
              "answer": "Jupiter",
              "is_correct": true
            },
            {
              "answer": "Mars",
              "is_correct": false
            },
            {
              "answer": "Neptune",
              "is_correct": false
            },
            {
              "answer": "Saturn",
              "is_correct": false
            }
          ]
        },
        {
          "id": 46,
          "type": "four_answers",
          "category": "History",
          "difficulty": 3,
          "question": "What was the first country to issue paper money?",
          "answers": [
            {
              "answer": "China",
              "is_correct": true
            },
            {
              "answer": "Japan",
              "is_correct": false
            },
            {
              "answer": "Korea",
              "is_correct": false
            },
            {
              "answer": "Vietnam",
              "is_correct": false
            }
          ]
        },
        {
          "id": 47,
          "type": "four_answers",
          "category": "Art",
          "difficulty": 3,
          "question": "Which famous artist painted the Persistence of Memory?",
          "answers": [
            {
              "answer": "Vincent van Gogh",
              "is_correct": false
            },
            {
              "answer": "Pablo Picasso",
              "is_correct": false
            },
            {
              "answer": "Salvador Dali",
              "is_correct": true
            },
            {
              "answer": "Henri Matisse",
              "is_correct": false
            }
          ]
        },
        {
          "id": 48,
          "type": "four_answers",
          "category": "Geography",
          "difficulty": 3,
          "question": "Which country is located between France and Germany?",
          "answers": [
            {
              "answer": "Austria",
              "is_correct": false
            },
            {
              "answer": "Switzerland",
              "is_correct": true
            },
            {
              "answer": "Luxembourg",
              "is_correct": false
            },
            {
              "answer": "Netherlands",
              "is_correct": false
            }
          ]
        },
        {
          "id": 49,
          "type": "four_answers",
          "category": "History",
          "difficulty": 3,
          "question": "When did the Cuban Missile Crisis occur?",
          "answers": [
            {
              "answer": "1962",
              "is_correct": true
            },
            {
              "answer": "1958",
              "is_correct": false
            },
            {
              "answer": "1968",
              "is_correct": false
            },
            {
              "answer": "1950",
              "is_correct": false
            }
          ]
        },
        {
          "id": 50,
          "type": "four_answers",
          "category": "Literature",
          "difficulty": 3,
          "question": "Who wrote the novel 'Wuthering Heights'?",
          "answers": [
            {
              "answer": "Emily Bronte",
              "is_correct": true
            },
            {
              "answer": "Jane Austen",
              "is_correct": false
            },
            {
              "answer": "Charlotte Bronte",
              "is_correct": false
            },
            {
              "answer": "Mary Shelley",
              "is_correct": false
            }
          ]
        },
        {
          "id": 51,
          "type": "four_answers",
          "category": "Biology",
          "difficulty": 3,
          "question": "What is the process by which plants make their food?",
          "answers": [
            {
              "answer": "Respiration",
              "is_correct": false
            },
            {
              "answer": "Metabolism",
              "is_correct": false
            },
            {
              "answer": "Photosynthesis",
              "is_correct": true
            },
            {
              "answer": "Metabolism",
              "is_correct": false
            }
          ]
        },
        {
          "id": 52,
          "type": "four_answers",
          "category": "Art",
          "difficulty": 3,
          "question": "What is the name of the most famous sculpture by Michelangelo?",
          "answers": [
            {
              "answer": "David",
              "is_correct": true
            },
            {
              "answer": "The Pieta",
              "is_correct": false
            },
            {
              "answer": "The Thinker",
              "is_correct": false
            },
            {
              "answer": "The Kiss",
              "is_correct": false
            }
          ]
        },
        {
          "id": 53,
          "type": "four_answers",
          "category": "Common",
          "difficulty": 1,
          "question": "Як звати першого космонавта, що побував у космосі?",
          "answers": [
            {
              "answer": "Юрій Гагарін",
              "is_correct": true
            },
            {
              "answer": "Ніл Армстронг",
              "is_correct": false
            },
            {
              "answer": "Едвін Олдрін",
              "is_correct": false
            },
            {
              "answer": "Людовік Штерн",
              "is_correct": false
            }
          ]
        },
        {
          "id": 54,
          "type": "four_answers",
          "category": "Common",
          "difficulty": 1,
          "question": "В якому році відбулася Французька революція?",
          "answers": [
            {
              "answer": "1789 ",
              "is_correct": true
            },
            {
              "answer": "1812",
              "is_correct": false
            },
            {
              "answer": "1917",
              "is_correct": false
            },
            {
              "answer": "1956",
              "is_correct": false
            }
          ]
        },
        {
          "id": 55,
          "type": "four_answers",
          "category": "Common",
          "difficulty": 1,
          "question": "Як називається річка, на берегах якої знаходиться Київ?",
          "answers": [
            {
              "answer": "Дніпро",
              "is_correct": true
            },
            {
              "answer": "Дністер",
              "is_correct": false
            },
            {
              "answer": "Дніпровські протоки",
              "is_correct": false
            },
            {
              "answer": "Псіл",
              "is_correct": false
            }
          ]
        },
        {
          "id": 56,
          "type": "four_answers",
          "category": "Common",
          "difficulty": 1,
          "question": "Який газ складає найбільшу частку атмосфери Землі?",
          "answers": [
            {
              "answer": "Азот",
              "is_correct": true
            },
            {
              "answer": "Оксиген",
              "is_correct": false
            },
            {
              "answer": "Вуглекислий газ",
              "is_correct": false
            },
            {
              "answer": "Аргон",
              "is_correct": false
            }
          ]
        },
        {
          "id": 57,
          "type": "four_answers",
          "category": "Common",
          "difficulty": 1,
          "question": "Який материк має найбільшу кількість населення?",
          "answers": [
            {
              "answer": "Азія",
              "is_correct": true
            },
            {
              "answer": "Європа",
              "is_correct": false
            },
            {
              "answer": "Північна Америка",
              "is_correct": false
            },
            {
              "answer": "Південна Америка",
              "is_correct": false
            }
          ]
        },
        {
          "id": 58,
          "type": "four_answers",
          "category": "Common",
          "difficulty": 1,
          "question": "Яке хімічне позначення має елемент з атомним номером 6?",
          "answers": [
            {
              "answer": "C",
              "is_correct": true
            },
            {
              "answer": "H",
              "is_correct": false
            },
            {
              "answer": "O",
              "is_correct": false
            },
            {
              "answer": "Fe",
              "is_correct": false
            }
          ]
        },
        {
          "id": 59,
          "type": "four_answers",
          "category": "Common",
          "difficulty": 1,
          "question": "В якому місті знаходиться Білацерківський національний аграрний університет?",
          "answers": [
            {
              "answer": "Біла Церква",
              "is_correct": true
            },
            {
              "answer": "Київ",
              "is_correct": false
            },
            {
              "answer": "Харків",
              "is_correct": false
            },
            {
              "answer": "Одеса",
              "is_correct": false
            }
          ]
        },
        {
          "id": 60,
          "type": "four_answers",
          "category": "Common",
          "difficulty": 1,
          "question": "Як називається головний міст Угорщини?",
          "answers": [
            {
              "answer": "Будапешт",
              "is_correct": true
            },
            {
              "answer": "Белград",
              "is_correct": false
            },
            {
              "answer": "Бухарест",
              "is_correct": false
            },
            {
              "answer": "Краків",
              "is_correct": false
            }
          ]
        },
        {
          "id": 61,
          "type": "four_answers",
          "category": "Common",
          "difficulty": 1,
          "question": "Який відсоток води складає Земну поверхню?",
          "answers": [
            {
              "answer": "Приблизно 70% ",
              "is_correct": true
            },
            {
              "answer": "Приблизно 50%",
              "is_correct": false
            },
            {
              "answer": "Приблизно 30%",
              "is_correct": false
            },
            {
              "answer": "Приблизно 10%",
              "is_correct": false
            }
          ]
        },
        {
          "id": 62,
          "type": "four_answers",
          "category": "Common",
          "difficulty": 1,
          "question": "Яка столиця Італії?",
          "answers": [
            {
              "answer": "Париж",
              "is_correct": false
            },
            {
              "answer": "Мадрид",
              "is_correct": false
            },
            {
              "answer": "Рим",
              "is_correct": true
            },
            {
              "answer": "Лондон",
              "is_correct": false
            }
          ]
        },
        {
          "id": 63,
          "type": "four_answers",
          "category": "Common",
          "difficulty": 1,
          "question": "Хто автор Війни і миру?",
          "answers": [
            {
              "answer": "Федір Достоєвський",
              "is_correct": false
            },
            {
              "answer": "Михайло Булгаков",
              "is_correct": false
            },
            {
              "answer": "Лев Толстой",
              "is_correct": true
            },
            {
              "answer": "Іван Тургенєв",
              "is_correct": false
            }
          ]
        },
        {
          "id": 64,
          "type": "four_answers",
          "category": "Common",
          "difficulty": 1,
          "question": "У якому році відбулася Французька революція?",
          "answers": [
            {
              "answer": "1789",
              "is_correct": true
            },
            {
              "answer": "1848",
              "is_correct": false
            },
            {
              "answer": "1917",
              "is_correct": false
            },
            {
              "answer": "1956",
              "is_correct": false
            }
          ]
        },
        {
          "id": 65,
          "type": "four_answers",
          "category": "Common",
          "difficulty": 1,
          "question": "Який газ найпоширеніший у атмосфері Землі?\n",
          "answers": [
            {
              "answer": "Кисень",
              "is_correct": false
            },
            {
              "answer": "Нітроген",
              "is_correct": true
            },
            {
              "answer": "Вуглекислий газ",
              "is_correct": false
            },
            {
              "answer": "Водень",
              "is_correct": false
            }
          ]
        },
        {
          "id": 66,
          "type": "four_answers",
          "category": "Common",
          "difficulty": 1,
          "question": "Хто з перелічених не є режисером?\n",
          "answers": [
            {
              "answer": "Крістофер Нолан",
              "is_correct": false
            },
            {
              "answer": "Стівен Спілберг",
              "is_correct": false
            },
            {
              "answer": "Емма Ватсон",
              "is_correct": true
            },
            {
              "answer": "Квентін Тарантіно",
              "is_correct": false
            }
          ]
        },
        {
          "id": 67,
          "type": "four_answers",
          "category": "Common",
          "difficulty": 1,
          "question": "Яка найбільша планета в Сонячній системі?\n",
          "answers": [
            {
              "answer": "Юпітер",
              "is_correct": true
            },
            {
              "answer": "Сатурн",
              "is_correct": false
            },
            {
              "answer": "Марс",
              "is_correct": false
            },
            {
              "answer": "Венера",
              "is_correct": false
            }
          ]
        },
        {
          "id": 68,
          "type": "four_answers",
          "category": "Common",
          "difficulty": 1,
          "question": "Який метал є найдорожчим у світі?",
          "answers": [
            {
              "answer": "Золото",
              "is_correct": false
            },
            {
              "answer": "Платина",
              "is_correct": false
            },
            {
              "answer": "Ртуть",
              "is_correct": false
            },
            {
              "answer": "Родій",
              "is_correct": true
            }
          ]
        },
        {
          "id": 69,
          "type": "four_answers",
          "category": "Common",
          "difficulty": 1,
          "question": "Хто з перелічених не є керівником держави?",
          "answers": [
            {
              "answer": "Дональд Трамп",
              "is_correct": true
            },
            {
              "answer": "Володимир Путін",
              "is_correct": false
            },
            {
              "answer": "Ангела Меркель",
              "is_correct": false
            },
            {
              "answer": "Еммануель Макрон",
              "is_correct": false
            }
          ]
        },
        {
          "id": 70,
          "type": "four_answers",
          "category": "Common",
          "difficulty": 1,
          "question": "Який мовний регіон найбільший у світі за кількістю носіїв?\n",
          "answers": [
            {
              "answer": "Англомовний",
              "is_correct": false
            },
            {
              "answer": "Китайськомовний",
              "is_correct": true
            },
            {
              "answer": "Іспаномовний",
              "is_correct": false
            },
            {
              "answer": "Франкомовний",
              "is_correct": false
            }
          ]
        },
        {
          "id": 71,
          "type": "four_answers",
          "category": "Geography",
          "difficulty": 2,
          "question": "Яка країна найбільша за територією?",
          "answers": [
            {
              "answer": "Китай",
              "is_correct": false
            },
            {
              "answer": "Росія",
              "is_correct": true
            },
            {
              "answer": "Канада",
              "is_correct": false
            },
            {
              "answer": "США",
              "is_correct": false
            }
          ]
        },
        {
          "id": 72,
          "type": "four_answers",
          "category": "History",
          "difficulty": 1,
          "question": "У якому році сталася Чорнобильська катастрофа?",
          "answers": [
            {
              "answer": "1985",
              "is_correct": false
            },
            {
              "answer": "1986",
              "is_correct": true
            },
            {
              "answer": "1987",
              "is_correct": false
            },
            {
              "answer": "1988",
              "is_correct": false
            }
          ]
        },
        {
          "id": 73,
          "type": "four_answers",
          "category": "Science",
          "difficulty": 3,
          "question": "Що таке елемент?",
          "answers": [
            {
              "answer": "Атом з однаковою кількістю протонів і електронів",
              "is_correct": true
            },
            {
              "answer": "Атом з різною кількістю протонів і електронів",
              "is_correct": false
            },
            {
              "answer": "Змішання двох атомів",
              "is_correct": false
            },
            {
              "answer": "Зв'язок двох атомів",
              "is_correct": false
            }
          ]
        },
        {
          "id": 74,
          "type": "four_answers",
          "category": "Sports",
          "difficulty": 2,
          "question": "Яка з цих команд не грає в Національній баскетбольній асоціації США?",
          "answers": [
            {
              "answer": "Golden State Warriors",
              "is_correct": false
            },
            {
              "answer": "Boston Celtics",
              "is_correct": false
            },
            {
              "answer": "Miami Heat",
              "is_correct": false
            },
            {
              "answer": "Toronto Raptors",
              "is_correct": true
            }
          ]
        }
      ]
    }
""".trimIndent()