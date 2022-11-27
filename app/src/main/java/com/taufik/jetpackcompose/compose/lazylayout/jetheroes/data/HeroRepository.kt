package com.taufik.jetpackcompose.compose.lazylayout.jetheroes.data

import com.taufik.jetpackcompose.compose.lazylayout.jetheroes.model.Hero
import com.taufik.jetpackcompose.compose.lazylayout.jetheroes.model.HeroesData

class HeroRepository {
    fun getHeroes(): List<Hero> {
        return HeroesData.heroes
    }

    fun searchHeroes(query: String): List<Hero> {
        return HeroesData.heroes.filter {
            it.name.contains(query, ignoreCase = true)
        }
    }
}