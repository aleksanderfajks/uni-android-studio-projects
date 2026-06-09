package com.example.foodapp

data class MenuItem(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val category: String
)

object MenuData {
    val pizzas = listOf(
        MenuItem(1, "Margherita", "Klasyczna pizza z sosem pomidorowym i mozzarellą", 25.0, "Pizza"),
        MenuItem(2, "Pepperoni", "Pikantne salami pepperoni, ser i sos pomidorowy", 32.0, "Pizza"),
        MenuItem(3, "Hawajska", "Szynka, soczysty ananas i ser", 30.0, "Pizza"),
        MenuItem(4, "Wegetariańska", "Papryka, pieczarki, kukurydza i oliwki", 28.0, "Pizza"),
        MenuItem(5, "Quattro Formaggi", "Cztery rodzaje sera: mozzarella, parmezan, gorgonzola i cheddar", 35.0, "Pizza")
    )

    val burgers = listOf(
        MenuItem(6, "Classic Burger", "Wołowina, sałata, pomidor, cebula i sos klasyczny", 22.0, "Burger"),
        MenuItem(7, "Cheese Burger", "Wołowina, podwójny cheddar, ogórek kiszony i ketchup", 24.0, "Burger"),
        MenuItem(8, "Bacon Burger", "Wołowina, chrupiący bekon, ser i sos BBQ", 28.0, "Burger"),
        MenuItem(9, "Hot Spicy", "Wołowina, papryczki jalapeno, ostry sos i nachos", 27.0, "Burger"),
        MenuItem(10, "Veggie Burger", "Kotlet z ciecierzycy, awokado, kiełki i sos jogurtowy", 25.0, "Burger")
    )

    val drinks = listOf(
        MenuItem(11, "Coca-Cola", "Orzeźwiający napój gazowany 0.5l", 7.0, "Napój"),
        MenuItem(12, "Sok Pomarańczowy", "Świeżo wyciskany sok z pomarańczy 0.3l", 12.0, "Napój"),
        MenuItem(13, "Woda Mineralna", "Woda niegazowana z cytryną 0.5l", 5.0, "Napój"),
        MenuItem(14, "Herbata Mrożona", "Domowa ice-tea z miętą i brzoskwinią", 10.0, "Napój"),
        MenuItem(15, "Kawa Latte", "Kremowa kawa z dużą ilością spienionego mleka", 14.0, "Napój")
    )
}
