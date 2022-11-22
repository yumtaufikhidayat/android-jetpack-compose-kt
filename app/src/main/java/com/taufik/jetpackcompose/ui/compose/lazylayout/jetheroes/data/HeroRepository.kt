package com.taufik.jetpackcompose.ui.compose.lazylayout.jetheroes.data

import com.taufik.jetpackcompose.ui.compose.lazylayout.jetheroes.model.Hero
import com.taufik.jetpackcompose.ui.compose.lazylayout.jetheroes.model.HeroesData

class HeroRepository {
    fun getHeroes(): List<Hero> {
        return HeroesData.heroes
    }
}